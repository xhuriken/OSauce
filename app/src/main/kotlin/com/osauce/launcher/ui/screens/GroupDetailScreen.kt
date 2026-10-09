package com.osauce.launcher.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osauce.launcher.data.model.ActionGroup
import com.osauce.launcher.data.model.ActionNotification
import com.osauce.launcher.data.model.ActionTodo
import com.osauce.launcher.ui.theme.*

@Composable
fun GroupDetailScreen(
    group: ActionGroup,
    onBack: () -> Unit,
    onConvertToTodo: (ActionNotification) -> Unit,
    onToggleTodo: (ActionTodo) -> Unit,
    onLaunchApp: (String) -> Unit,
    onCloseAndClear: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OledBlack)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // --- En-tete superieur ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "<- Retour",
                color = TextSecondary,
                fontSize = 13.sp,
                modifier = Modifier.clickable { onBack() }
            )

            Text(
                text = "[ Regler ]",
                color = TextSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = group.title,
            color = TextPrimary,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = group.statusText,
            color = if (group.isMuted) TextMuted else SoftAmber,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 2.dp, bottom = 20.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // --- SECTION 1 : CE QUI ATTEND ---
            item {
                Text(
                    text = "CE QUI ATTEND (${group.notifications.size})",
                    color = TextMuted,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            items(group.notifications) { notif ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(CardDark)
                        .border(1.dp, BorderDark, RoundedCornerShape(16.dp))
                        .padding(14.dp)
                ) {
                    Text(
                        text = "${notif.sender} | ${notif.timestamp}",
                        color = TextSecondary,
                        fontSize = 11.sp
                    )
                    Text(
                        text = notif.message,
                        color = TextPrimary,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(CardDarkVariant)
                                .clickable { onLaunchApp(notif.packageName) }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(text = "Repondre", color = TextPrimary, fontSize = 11.sp)
                        }

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(BorderDark)
                                .clickable { onConvertToTodo(notif) }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(text = "Convertir en Todo", color = SlateBlue, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }

            // --- SECTION 2 : MES TACHES DU GROUPE ---
            item {
                Text(
                    text = "MES TACHES DU GROUPE",
                    color = TextMuted,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            items(group.todos) { todo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(CardDark)
                        .clickable { onToggleTodo(todo) }
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = if (todo.isDone) "[x]" else "[ ]",
                            color = if (todo.isDone) SageGreen else TextMuted,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = todo.text,
                            color = if (todo.isDone) TextMuted else TextPrimary,
                            fontSize = 14.sp
                        )
                    }

                    if (todo.actionLabel != null && todo.linkedPackageName != null) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(CardDarkVariant)
                                .clickable { onLaunchApp(todo.linkedPackageName) }
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        ) {
                            Text(
                                text = todo.actionLabel,
                                color = SoftAmber,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            // --- SECTION 3 : OUTILS DU GROUPE ---
            item {
                Text(
                    text = "OUTILS DU GROUPE",
                    color = TextMuted,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    group.appShortcuts.forEach { app ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(CardDark)
                                .border(1.dp, BorderDark, RoundedCornerShape(12.dp))
                                .clickable { onLaunchApp(app) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = app, color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }

        // --- Bouton de Cloture Global ---
        Spacer(modifier = Modifier.height(14.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(CardDarkVariant)
                .border(1.dp, BorderDark, RoundedCornerShape(16.dp))
                .clickable { onCloseAndClear() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "TOUT CLOTURER ET RANGER LE GROUPE",
                color = TextPrimary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
        }
    }
}
