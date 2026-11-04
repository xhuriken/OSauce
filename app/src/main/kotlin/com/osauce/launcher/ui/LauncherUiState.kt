package com.osauce.launcher.ui

import androidx.compose.runtime.Immutable
import com.osauce.launcher.core.designsystem.BackgroundVibrancy
import com.osauce.launcher.core.designsystem.CellShapeMode
import com.osauce.launcher.core.designsystem.InteractiveBackgroundConfig
import com.osauce.launcher.core.designsystem.ThemeMod
import com.osauce.launcher.data.model.AppItem
import com.osauce.launcher.data.model.Sphere
import com.osauce.launcher.data.model.TodoItem
import com.osauce.launcher.feature.hub.ActionHubUiState

@Immutable
data class LauncherUiState(
    val currentScreen: String = "home",
    val showSeconds: Boolean = false,
    val calmMessage: String = "",
    val activeTheme: ThemeMod = ThemeMod.Presets[0],
    val availableThemes: List<ThemeMod> = ThemeMod.Presets,
    val gridDensity: Int = 15,
    val backgroundConfig: InteractiveBackgroundConfig = InteractiveBackgroundConfig(),
    val installedApps: List<AppItem> = emptyList(),
    val appSearchQuery: String = "",
    val filteredApps: List<AppItem> = emptyList(),
    val isAppsLoading: Boolean = false,
    // Action Hub state
    val hubState: ActionHubUiState = ActionHubUiState()
)

sealed interface LauncherUiIntent {
    data class NavigateTo(val screen: String) : LauncherUiIntent
    data object ToggleSeconds : LauncherUiIntent
    data class UpdateCalmMessage(val message: String) : LauncherUiIntent
    data class UpdateVibrancy(val vibrancy: BackgroundVibrancy) : LauncherUiIntent
    data class UpdateCellShape(val shape: CellShapeMode) : LauncherUiIntent
    data class UpdateGridDensity(val density: Int) : LauncherUiIntent
    data class SetTouchParticles(val enabled: Boolean) : LauncherUiIntent
    data class SetDragParticles(val enabled: Boolean) : LauncherUiIntent
    data class SelectTheme(val theme: ThemeMod) : LauncherUiIntent
    data class ImportTheme(val jsonStr: String) : LauncherUiIntent
    data class DeleteTheme(val themeId: String) : LauncherUiIntent
    data object LoadInstalledApps : LauncherUiIntent
    data class SearchApps(val query: String) : LauncherUiIntent
    data object HandleBackPress : LauncherUiIntent

    // Action Hub intents
    data class SelectSphere(val sphereId: String?) : LauncherUiIntent
    data class UpdateHubQuery(val query: String) : LauncherUiIntent
    data object SubmitHubQuery : LauncherUiIntent
    data class ToggleTodo(val todo: TodoItem) : LauncherUiIntent
    data class DeleteTodo(val todo: TodoItem) : LauncherUiIntent
    data class CreateSphere(val name: String, val icon: com.osauce.launcher.data.model.SphereIcon, val colorHex: String) : LauncherUiIntent
    data class UpdateSphere(val sphere: Sphere) : LauncherUiIntent
    data class DeleteSphere(val sphereId: String) : LauncherUiIntent
}

sealed interface LauncherUiEffect {
    data class LaunchIntent(val action: String) : LauncherUiEffect
    data class ShowToast(val message: String) : LauncherUiEffect
}
