package com.osauce.launcher

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.osauce.launcher.ui.screens.SilenceScreen
import com.osauce.launcher.ui.theme.OSauceTheme

/**
 * MainActivity is the front door of our Android application.
 *
 * In Android:
 * - An "Activity" represents a single focused screen/window that the user interacts with.
 * - In AndroidManifest.xml, this Activity is registered with the category "HOME",
 *   which tells Android: "Hey, I can act as the main home screen (launcher) of this phone."
 */
class MainActivity : ComponentActivity() {

    /**
     * onCreate() is the very first method Android calls when launching our Activity.
     * Think of it like the constructor or 'DOMContentLoaded' in web development.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent { } is where Jetpack Compose takes over the Android window.
        // Instead of old XML files, everything inside this block is modern Kotlin UI.
        setContent {
            // Apply our OLED theme (colors, fonts) across the entire app
            OSauceTheme {
                // Display Screen 1: The Silence
                SilenceScreen(
                    onDialer = {
                        // ACTION_DIAL tells Android: "Open whichever phone app the user has to dial a number"
                        val dialerIntent = Intent(Intent.ACTION_DIAL)
                        startActivitySafe(dialerIntent)
                    },
                    onCamera = {
                        // ACTION_STILL_IMAGE_CAMERA tells Android: "Open the default camera app"
                        val cameraIntent = Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA)
                        startActivitySafe(cameraIntent)
                    },
                    onOpenDefaultHomeSettings = {
                        // ACTION_HOME_SETTINGS opens Android's system menu where the user can pick
                        // which app is their default launcher (allows them to easily switch back if needed).
                        val homeSettingsIntent = Intent(Settings.ACTION_HOME_SETTINGS)
                        startActivitySafe(homeSettingsIntent)
                    }
                )
            }
        }
    }

    /**
     * Helper function to launch external applications safely.
     *
     * Why is this needed?
     * If an emulator or weird budget tablet does not have a camera or phone dialer installed,
     * calling startActivity() directly would throw an ActivityNotFoundException and crash the app.
     * The try-catch block makes sure our launcher never crashes.
     */
    private fun startActivitySafe(intent: Intent) {
        try {
            startActivity(intent)
        } catch (_: Exception) {
            // Silently ignore if no app exists to handle this action
        }
    }
}
