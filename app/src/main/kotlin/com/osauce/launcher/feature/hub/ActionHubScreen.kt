package com.osauce.launcher.feature.hub

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osauce.launcher.core.designsystem.LocalThemeColors
import com.osauce.launcher.core.designsystem.OSauceTypography
import com.osauce.launcher.data.model.Sphere
import com.osauce.launcher.data.model.SphereIcon
import com.osauce.launcher.data.model.TodoItem
import com.osauce.launcher.feature.hub.components.EditSphereDialog
import com.osauce.launcher.feature.hub.components.QuickCaptureBar
import com.osauce.launcher.feature.hub.components.SphereSelectorRow
import com.osauce.launcher.feature.hub.components.TodoItemRow

/**
 * Action Hub screen hosting spheres of life and task engine.
 */
@Composable
fun ActionHubScreen(
    state: ActionHubUiState,
    onQueryChange: (String) -> Unit,
    onSubmitQuery: () -> Unit,
    onSelectSphere: (String?) -> Unit,
    onToggleTodo: (TodoItem) -> Unit,
    onDeleteTodo: (TodoItem) -> Unit,
    onCreateSphere: (name: String, icon: SphereIcon, colorHex: String) -> Unit,
    onUpdateSphere: (Sphere) -> Unit,
    onDeleteSphere: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val themeColors = LocalThemeColors.current

    var editingSphere by remember { mutableStateOf<Sphere?>(null) }
    var isCreatingSphere by remember { mutableStateOf(false) }

    // Dialog for creating or modifying a life sphere
    if (isCreatingSphere || editingSphere != null) {
        EditSphereDialog(
            sphere = editingSphere,
            onDismiss = {
                isCreatingSphere = false
                editingSphere = null
            },
            onSave = { name, icon, colorHex ->
                if (editingSphere != null) {
                    onUpdateSphere(
                        editingSphere!!.copy(
                            name = name,
                            icon = icon,
                            colorHex = colorHex
                        )
                    )
                } else {
                    onCreateSphere(name, icon, colorHex)
                }
                isCreatingSphere = false
                editingSphere = null
            },
            onDelete = editingSphere?.takeIf { !it.isDefault }?.let { target ->
                {
                    onDeleteSphere(target.id)
                    editingSphere = null
                }
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 24.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "ACTION HUB",
                    style = OSauceTypography.SecondsClock.copy(
                        fontSize = 11.sp,
                        letterSpacing = 2.sp,
                        color = themeColors.primaryAccent
                    )
                )
                Text(
                    text = "Sphères & Intentions",
                    style = OSauceTypography.FluidClock.copy(
                        fontSize = 24.sp,
                        color = themeColors.textPrimary
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Quick Capture Bar
        QuickCaptureBar(
            query = state.quickInputText,
            onQueryChange = onQueryChange,
            onSubmit = onSubmitQuery
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Spheres Row with Add & Long-press Edit
        SphereSelectorRow(
            spheres = state.spheres,
            selectedSphereId = state.selectedSphereId,
            onSelectSphere = onSelectSphere,
            onAddSphere = { isCreatingSphere = true },
            onEditSphere = { sphere -> editingSphere = sphere }
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Main Content Area
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = themeColors.primaryAccent)
            }
        } else if (state.activeTodos.isEmpty() && state.completedTodos.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Aucune tâche en attente.\nProfitez du calme.",
                    style = OSauceTypography.TileTitle.copy(
                        fontSize = 14.sp,
                        color = themeColors.textSecondary,
                        lineHeight = 20.sp
                    ),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Active Tasks Header
                if (state.activeTodos.isNotEmpty()) {
                    item(key = "header_active") {
                        Text(
                            text = "EN COURS (${state.activeTodos.size})",
                            style = OSauceTypography.SecondsClock.copy(
                                fontSize = 11.sp,
                                letterSpacing = 1.sp,
                                color = themeColors.textSecondary
                            ),
                            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                        )
                    }

                    items(
                        items = state.activeTodos,
                        key = { it.id }
                    ) { todo ->
                        val sphere = state.spheres.firstOrNull { it.id == todo.sphereId }
                        val accentColor = sphere?.let {
                            runCatching { Color(android.graphics.Color.parseColor(it.colorHex)) }.getOrNull()
                        } ?: themeColors.primaryAccent

                        TodoItemRow(
                            todo = todo,
                            accentColor = accentColor,
                            onToggle = { onToggleTodo(todo) },
                            onDelete = { onDeleteTodo(todo) }
                        )
                    }
                }

                // Completed Tasks Header
                if (state.completedTodos.isNotEmpty()) {
                    item(key = "header_completed") {
                        Text(
                            text = "TERMINÉES (${state.completedTodos.size})",
                            style = OSauceTypography.SecondsClock.copy(
                                fontSize = 11.sp,
                                letterSpacing = 1.sp,
                                color = themeColors.textSecondary
                            ),
                            modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                        )
                    }

                    items(
                        items = state.completedTodos,
                        key = { it.id }
                    ) { todo ->
                        val sphere = state.spheres.firstOrNull { it.id == todo.sphereId }
                        val accentColor = sphere?.let {
                            runCatching { Color(android.graphics.Color.parseColor(it.colorHex)) }.getOrNull()
                        } ?: themeColors.primaryAccent

                        TodoItemRow(
                            todo = todo,
                            accentColor = accentColor,
                            onToggle = { onToggleTodo(todo) },
                            onDelete = { onDeleteTodo(todo) }
                        )
                    }
                }
            }
        }
    }
}
