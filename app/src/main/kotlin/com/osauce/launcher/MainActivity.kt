package com.osauce.launcher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.osauce.launcher.data.model.ActionGroup
import com.osauce.launcher.data.model.ActionNotification
import com.osauce.launcher.data.model.ActionTodo
import com.osauce.launcher.plugin.PluginManager
import com.osauce.launcher.ui.screens.*
import com.osauce.launcher.ui.theme.OSauceTheme

class MainActivity : ComponentActivity() {

    private val pluginManager = PluginManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OSauceTheme {
                var currentScreen by remember { mutableStateOf(0) } // 0=Silence, 1=Hub, 2=Detail, 3=Tools, 4=Marketplace
                var selectedGroup by remember { mutableStateOf<ActionGroup?>(null) }

                val plugins by pluginManager.marketplacePlugins.collectAsState()

                var groupsState by remember {
                    mutableStateOf(
                        listOf(
                            ActionGroup(
                                id = "humain",
                                title = "Humain & Proches",
                                statusText = "2 nouveaux messages",
                                notifications = listOf(
                                    ActionNotification("1", "Maman", "Tu passes ce soir ?", "11:42", "com.google.android.apps.messaging")
                                ),
                                todos = listOf(
                                    ActionTodo("t1", "Rappeler Lucas avant 20h", false)
                                ),
                                appShortcuts = listOf("TEL", "SMS", "SIGNAL", "CONTACTS")
                            ),
                            ActionGroup(
                                id = "travail",
                                title = "Travail & Etudes",
                                statusText = "En veille jusqu'a 08:00",
                                notifications = listOf(
                                    ActionNotification("2", "M. Dupont", "Verifier section 2 du rapport", "09:15", "ch.protonmail.android")
                                ),
                                todos = listOf(
                                    ActionTodo("t2", "Relire bibliographie", true),
                                    ActionTodo("t3", "Deposer le rapport final avant 17h00", false, "com.android.documentsui", "Ouvrir Fichiers")
                                ),
                                appShortcuts = listOf("MAIL", "AGENDA", "NOTES", "FICHIERS"),
                                isMuted = true
                            ),
                            ActionGroup(
                                id = "quotidien",
                                title = "Quotidien & Vital",
                                statusText = "1 alerte traitee",
                                notifications = listOf(
                                    ActionNotification("3", "SNCF Connect", "Billet TGV 8421 confirme", "Hier", "com.sncf.fusion")
                                ),
                                todos = listOf(
                                    ActionTodo("t4", "Composter billet avant depart", false, "com.sncf.fusion", "Billet")
                                ),
                                appShortcuts = listOf("BANQUE", "SNCF", "PLANS", "HORLOGE")
                            ),
                            ActionGroup(
                                id = "loisirs",
                                title = "Temps Libre & Calme",
                                statusText = "Silence total",
                                notifications = emptyList(),
                                todos = listOf(
                                    ActionTodo("t5", "Ecouter podcast tech", false)
                                ),
                                appShortcuts = listOf("MUSIQUE", "PODCAST", "LIVRE", "RADIO"),
                                isMuted = true
                            )
                        )
                    )
                }

                when (currentScreen) {
                    0 -> SilenceScreen(
                        onOpenHub = { currentScreen = 1 },
                        onOpenTools = { currentScreen = 3 },
                        onOpenMarketplace = { currentScreen = 4 },
                        onEmergencyCall = {
                            val intent = Intent(Intent.ACTION_DIAL)
                            startActivity(intent)
                        },
                        onCamera = {
                            val intent = Intent("android.media.action.IMAGE_CAPTURE")
                            startActivity(intent)
                        }
                    )
                    1 -> HubScreen(
                        groups = groupsState,
                        onSelectGroup = { group ->
                            selectedGroup = group
                            currentScreen = 2
                        },
                        onBackToSilence = { currentScreen = 0 },
                        onOpenMarketplace = { currentScreen = 4 },
                        onOpenTools = { currentScreen = 3 }
                    )
                    2 -> selectedGroup?.let { group ->
                        GroupDetailScreen(
                            group = group,
                            onBack = { currentScreen = 1 },
                            onConvertToTodo = { notif ->
                                val newTodo = ActionTodo(
                                    id = System.currentTimeMillis().toString(),
                                    text = "${notif.sender}: ${notif.message}",
                                    isDone = false,
                                    linkedPackageName = notif.packageName,
                                    actionLabel = "Repondre"
                                )
                                groupsState = groupsState.map { g ->
                                    if (g.id == group.id) {
                                        g.copy(
                                            notifications = g.notifications.filter { it.id != notif.id },
                                            todos = g.todos + newTodo
                                        )
                                    } else g
                                }
                                selectedGroup = groupsState.find { it.id == group.id }
                                Toast.makeText(this, "Alerte transformee en Todo", Toast.LENGTH_SHORT).show()
                            },
                            onToggleTodo = { todo ->
                                groupsState = groupsState.map { g ->
                                    if (g.id == group.id) {
                                        g.copy(
                                            todos = g.todos.map {
                                                if (it.id == todo.id) it.copy(isDone = !it.isDone) else it
                                            }
                                        )
                                    } else g
                                }
                                selectedGroup = groupsState.find { it.id == group.id }
                            },
                            onLaunchApp = { pkg ->
                                launchAppPackage(pkg)
                            },
                            onCloseAndClear = {
                                currentScreen = 1
                                Toast.makeText(this, "Groupe range et archive", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                    3 -> ToolsScreen(
                        onBack = { currentScreen = 0 },
                        onLaunchApp = { pkg ->
                            launchAppPackage(pkg)
                        }
                    )
                    4 -> MarketplaceScreen(
                        plugins = plugins,
                        onToggleInstall = { pluginId ->
                            pluginManager.toggleInstallPlugin(pluginId)
                        },
                        onBack = { currentScreen = 0 }
                    )
                }
            }
        }
    }

    private fun launchAppPackage(packageName: String) {
        val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
        if (launchIntent != null) {
            startActivity(launchIntent)
        } else {
            Toast.makeText(this, "Application non installee ($packageName)", Toast.LENGTH_SHORT).show()
        }
    }
}
