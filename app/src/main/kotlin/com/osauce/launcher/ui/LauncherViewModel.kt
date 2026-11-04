package com.osauce.launcher.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.osauce.launcher.core.designsystem.BackgroundVibrancy
import com.osauce.launcher.core.designsystem.CellShapeMode
import com.osauce.launcher.core.designsystem.InteractiveBackgroundConfig
import com.osauce.launcher.core.designsystem.OSauceThemeEngine
import com.osauce.launcher.core.designsystem.ThemeMod
import com.osauce.launcher.data.local.OSauceDatabaseHelper
import com.osauce.launcher.data.local.SphereLocalSource
import com.osauce.launcher.data.local.TodoLocalSource
import com.osauce.launcher.data.model.AppItem
import com.osauce.launcher.data.model.TodoItem
import com.osauce.launcher.data.repository.AppRepository
import com.osauce.launcher.data.repository.SphereRepository
import com.osauce.launcher.data.repository.TodoRepository
import com.osauce.launcher.data.settings.SettingsPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LauncherViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = SettingsPreferences(application)
    val themeEngine = OSauceThemeEngine(application)
    private val appRepository = AppRepository(application)

    private val dbHelper = OSauceDatabaseHelper.getInstance(application)
    private val sphereRepository = SphereRepository(SphereLocalSource(dbHelper))
    private val todoRepository = TodoRepository(TodoLocalSource(dbHelper))

    private val _uiState = MutableStateFlow(
        LauncherUiState(
            currentScreen = "home",
            showSeconds = prefs.showSeconds,
            calmMessage = prefs.customCalmMessage,
            activeTheme = themeEngine.activeTheme.value,
            availableThemes = themeEngine.getAllThemes(),
            gridDensity = prefs.gridDensity,
            backgroundConfig = buildBackgroundConfig(
                theme = themeEngine.activeTheme.value,
                vibrancyName = prefs.backgroundVibrancy,
                cellShapeName = prefs.cellShapeMode,
                density = prefs.gridDensity,
                touch = prefs.touchParticlesEnabled,
                drag = prefs.dragParticlesEnabled
            )
        )
    )
    val uiState: StateFlow<LauncherUiState> = _uiState.asStateFlow()

    init {
        themeEngine.activeTheme.onEach { active ->
            _uiState.update { current ->
                current.copy(
                    activeTheme = active,
                    availableThemes = themeEngine.getAllThemes(),
                    backgroundConfig = buildBackgroundConfig(
                        theme = active,
                        vibrancyName = prefs.backgroundVibrancy,
                        cellShapeName = prefs.cellShapeMode,
                        density = current.gridDensity,
                        touch = current.backgroundConfig.touchBurstEnabled,
                        drag = current.backgroundConfig.dragTrailEnabled
                    )
                )
            }
        }.launchIn(viewModelScope)

        themeEngine.customThemes.onEach {
            _uiState.update { current ->
                current.copy(availableThemes = themeEngine.getAllThemes())
            }
        }.launchIn(viewModelScope)

        // Observe spheres from repository
        sphereRepository.spheres.onEach { spheresList ->
            _uiState.update { current ->
                val selectedId = current.hubState.selectedSphereId
                val validSelectedId = if (selectedId != null && spheresList.any { it.id == selectedId }) {
                    selectedId
                } else {
                    null
                }
                current.copy(
                    hubState = current.hubState.copy(
                        spheres = spheresList,
                        selectedSphereId = validSelectedId
                    )
                )
            }
        }.launchIn(viewModelScope)

        // Observe todos from repository
        todoRepository.todos.onEach { todosList ->
            _uiState.update { current ->
                current.copy(
                    hubState = current.hubState.copy(todos = todosList)
                )
            }
        }.launchIn(viewModelScope)

        // Load initial data
        viewModelScope.launch {
            sphereRepository.loadSpheres()
            todoRepository.loadTodos()
        }

        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            _uiState.update { it.copy(isAppsLoading = true) }
            val apps = appRepository.getInstalledApps()
            _uiState.update { current ->
                current.copy(
                    installedApps = apps,
                    filteredApps = filterApps(apps, current.appSearchQuery),
                    isAppsLoading = false
                )
            }
        }
    }

    private fun filterApps(apps: List<AppItem>, query: String): List<AppItem> {
        val trimmed = query.trim()
        if (trimmed.isBlank()) return apps
        return apps.filter { it.label.contains(trimmed, ignoreCase = true) }
    }

    fun onIntent(intent: LauncherUiIntent) {
        when (intent) {
            is LauncherUiIntent.NavigateTo -> {
                _uiState.update { it.copy(currentScreen = intent.screen) }
            }
            LauncherUiIntent.HandleBackPress -> {
                if (_uiState.value.currentScreen != "home") {
                    _uiState.update { it.copy(currentScreen = "home", appSearchQuery = "") }
                }
            }
            LauncherUiIntent.ToggleSeconds -> {
                val next = !_uiState.value.showSeconds
                prefs.showSeconds = next
                _uiState.update { it.copy(showSeconds = next) }
            }
            is LauncherUiIntent.UpdateCalmMessage -> {
                prefs.customCalmMessage = intent.message
                _uiState.update { it.copy(calmMessage = intent.message) }
            }
            is LauncherUiIntent.UpdateVibrancy -> {
                prefs.backgroundVibrancy = intent.vibrancy.name
                _uiState.update { current ->
                    current.copy(
                        backgroundConfig = current.backgroundConfig.copy(vibrancy = intent.vibrancy)
                    )
                }
            }
            is LauncherUiIntent.UpdateCellShape -> {
                prefs.cellShapeMode = intent.shape.name
                _uiState.update { current ->
                    current.copy(
                        backgroundConfig = current.backgroundConfig.copy(cellShape = intent.shape)
                    )
                }
            }
            is LauncherUiIntent.UpdateGridDensity -> {
                prefs.gridDensity = intent.density
                _uiState.update { current ->
                    current.copy(
                        gridDensity = intent.density,
                        backgroundConfig = current.backgroundConfig.copy(gridDensity = intent.density)
                    )
                }
            }
            is LauncherUiIntent.SetTouchParticles -> {
                prefs.touchParticlesEnabled = intent.enabled
                _uiState.update { current ->
                    current.copy(
                        backgroundConfig = current.backgroundConfig.copy(touchBurstEnabled = intent.enabled)
                    )
                }
            }
            is LauncherUiIntent.SetDragParticles -> {
                prefs.dragParticlesEnabled = intent.enabled
                _uiState.update { current ->
                    current.copy(
                        backgroundConfig = current.backgroundConfig.copy(dragTrailEnabled = intent.enabled)
                    )
                }
            }
            is LauncherUiIntent.SelectTheme -> {
                prefs.cellShapeMode = intent.theme.cellShape.name
                prefs.backgroundVibrancy = intent.theme.vibrancy.name
                themeEngine.applyTheme(intent.theme)
            }
            is LauncherUiIntent.ImportTheme -> {
                themeEngine.importThemeFromJson(intent.jsonStr)
            }
            is LauncherUiIntent.DeleteTheme -> {
                themeEngine.deleteCustomTheme(intent.themeId)
            }
            LauncherUiIntent.LoadInstalledApps -> {
                loadApps()
            }
            is LauncherUiIntent.SearchApps -> {
                _uiState.update { current ->
                    current.copy(
                        appSearchQuery = intent.query,
                        filteredApps = filterApps(current.installedApps, intent.query)
                    )
                }
            }
            is LauncherUiIntent.SelectSphere -> {
                _uiState.update { current ->
                    current.copy(
                        hubState = current.hubState.copy(selectedSphereId = intent.sphereId)
                    )
                }
            }
            is LauncherUiIntent.UpdateHubQuery -> {
                _uiState.update { current ->
                    current.copy(
                        hubState = current.hubState.copy(quickInputText = intent.query)
                    )
                }
            }
            LauncherUiIntent.SubmitHubQuery -> {
                val text = _uiState.value.hubState.quickInputText.trim()
                if (text.isNotBlank()) {
                    val targetSphereId = _uiState.value.hubState.selectedSphereId
                        ?: _uiState.value.hubState.spheres.firstOrNull()?.id
                        ?: "sphere_humans"

                    val newTodo = TodoItem(
                        sphereId = targetSphereId,
                        title = text
                    )

                    viewModelScope.launch {
                        todoRepository.addTodo(newTodo)
                        _uiState.update { current ->
                            current.copy(
                                hubState = current.hubState.copy(quickInputText = "")
                            )
                        }
                    }
                }
            }
            is LauncherUiIntent.ToggleTodo -> {
                viewModelScope.launch {
                    todoRepository.toggleCompleted(intent.todo.id, !intent.todo.isCompleted)
                }
            }
            is LauncherUiIntent.DeleteTodo -> {
                viewModelScope.launch {
                    todoRepository.deleteTodo(intent.todo.id)
                }
            }
        }
    }

    private fun buildBackgroundConfig(
        theme: ThemeMod,
        vibrancyName: String,
        cellShapeName: String,
        density: Int,
        touch: Boolean,
        drag: Boolean
    ): InteractiveBackgroundConfig {
        return InteractiveBackgroundConfig(
            vibrancy = BackgroundVibrancy.fromName(vibrancyName),
            cellShape = CellShapeMode.fromName(cellShapeName),
            gridDensity = density,
            touchBurstEnabled = touch,
            dragTrailEnabled = drag,
            backgroundColor = theme.colors.background,
            primaryColor = theme.colors.primaryAccent,
            secondaryColor = theme.colors.secondaryAccent,
            tertiaryColor = theme.colors.elevated
        )
    }
}
