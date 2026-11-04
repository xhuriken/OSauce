package com.osauce.launcher

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.osauce.launcher.core.designsystem.BackgroundVibrancy
import com.osauce.launcher.core.designsystem.CellShapeMode
import com.osauce.launcher.core.designsystem.FluidBackground
import com.osauce.launcher.core.designsystem.OSauceTheme
import com.osauce.launcher.core.util.launchSafe
import com.osauce.launcher.feature.drawer.AppDrawerScreen
import com.osauce.launcher.feature.home.HomeScreen
import com.osauce.launcher.feature.home.rememberHomeState
import com.osauce.launcher.feature.settings.SettingsScreen
import com.osauce.launcher.ui.LauncherUiIntent
import com.osauce.launcher.ui.LauncherViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: LauncherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            OSauceTheme(themeMod = uiState.activeTheme) {
                BackHandler(enabled = uiState.currentScreen != "home") {
                    viewModel.onIntent(LauncherUiIntent.HandleBackPress)
                }

                FluidBackground(config = uiState.backgroundConfig) {
                    AnimatedContent(
                        targetState = uiState.currentScreen,
                        transitionSpec = {
                            if (targetState == "settings" || targetState == "apps") {
                                (slideInVertically(tween(320, easing = FastOutSlowInEasing)) { it / 3 } + fadeIn(tween(250)))
                                    .togetherWith(slideOutVertically(tween(260)) { -it / 6 } + fadeOut(tween(200)))
                            } else {
                                (slideInVertically(tween(320, easing = FastOutSlowInEasing)) { -it / 6 } + fadeIn(tween(250)))
                                    .togetherWith(slideOutVertically(tween(260)) { it / 3 } + fadeOut(tween(200)))
                            }
                        },
                        label = "screen_navigation"
                    ) { screen ->
                        when (screen) {
                            "home" -> {
                                val homeState = rememberHomeState(showSeconds = uiState.showSeconds)

                                HomeScreen(
                                    state = homeState,
                                    calmMessage = uiState.calmMessage,
                                    onToggleSeconds = {
                                        viewModel.onIntent(LauncherUiIntent.ToggleSeconds)
                                    },
                                    onDialer = {
                                        launchSafe(Intent(Intent.ACTION_DIAL))
                                    },
                                    onMessages = {
                                        val messagesIntent = Intent(Intent.ACTION_MAIN).apply {
                                            addCategory(Intent.CATEGORY_APP_MESSAGING)
                                        }
                                        launchSafe(messagesIntent)
                                    },
                                    onCamera = {
                                        launchSafe(Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA))
                                    },
                                    onOpenApps = {
                                        viewModel.onIntent(LauncherUiIntent.NavigateTo("apps"))
                                    },
                                    onOpenDigest = {
                                        // Notification digest (planned)
                                    },
                                    onOpenSettings = {
                                        viewModel.onIntent(LauncherUiIntent.NavigateTo("settings"))
                                    }
                                )
                            }

                            "apps" -> {
                                AppDrawerScreen(
                                    apps = uiState.filteredApps,
                                    searchQuery = uiState.appSearchQuery,
                                    isLoading = uiState.isAppsLoading,
                                    onSearchQueryChange = { query ->
                                        viewModel.onIntent(LauncherUiIntent.SearchApps(query))
                                    },
                                    onLaunchApp = { app ->
                                        val launchIntent = packageManager.getLaunchIntentForPackage(app.packageName)
                                        if (launchIntent != null) {
                                            launchSafe(launchIntent)
                                        }
                                    },
                                    onBack = {
                                        viewModel.onIntent(LauncherUiIntent.HandleBackPress)
                                    }
                                )
                            }

                            "settings" -> {
                                SettingsScreen(
                                    showSeconds = uiState.showSeconds,
                                    calmMessage = uiState.calmMessage,
                                    bgVibrancy = uiState.backgroundConfig.vibrancy.name,
                                    cellShape = uiState.backgroundConfig.cellShape.name,
                                    gridDensity = uiState.gridDensity,
                                    touchParticles = uiState.backgroundConfig.touchBurstEnabled,
                                    dragParticles = uiState.backgroundConfig.dragTrailEnabled,
                                    activeTheme = uiState.activeTheme,
                                    availableThemes = uiState.availableThemes,
                                    onSelectTheme = { theme ->
                                        viewModel.onIntent(LauncherUiIntent.SelectTheme(theme))
                                    },
                                    onImportTheme = { jsonStr ->
                                        viewModel.onIntent(LauncherUiIntent.ImportTheme(jsonStr))
                                    },
                                    onToggleSeconds = {
                                        viewModel.onIntent(LauncherUiIntent.ToggleSeconds)
                                    },
                                    onSaveCalmMessage = { newMsg ->
                                        viewModel.onIntent(LauncherUiIntent.UpdateCalmMessage(newMsg))
                                    },
                                    onChangeVibrancy = { newVibrancy ->
                                        viewModel.onIntent(
                                            LauncherUiIntent.UpdateVibrancy(
                                                BackgroundVibrancy.fromName(newVibrancy)
                                            )
                                        )
                                    },
                                    onChangeCellShape = { newShape ->
                                        viewModel.onIntent(
                                            LauncherUiIntent.UpdateCellShape(
                                                CellShapeMode.fromName(newShape)
                                            )
                                        )
                                    },
                                    onChangeGridDensity = { newDensity ->
                                        viewModel.onIntent(LauncherUiIntent.UpdateGridDensity(newDensity))
                                    },
                                    onToggleTouchParticles = { enabled ->
                                        viewModel.onIntent(LauncherUiIntent.SetTouchParticles(enabled))
                                    },
                                    onToggleDragParticles = { enabled ->
                                        viewModel.onIntent(LauncherUiIntent.SetDragParticles(enabled))
                                    },
                                    onOpenDefaultHomeSettings = {
                                        launchSafe(Intent(Settings.ACTION_HOME_SETTINGS))
                                    },
                                    onBack = {
                                        viewModel.onIntent(LauncherUiIntent.HandleBackPress)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
