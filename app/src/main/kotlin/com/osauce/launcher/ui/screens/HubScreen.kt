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
import com.osauce.launcher.ui.theme.*

@Composable
fun HubScreen(
    groups: List<ActionGroup>,
    onSelectGroup: (ActionGroup) -> Unit,
    onBackToSilence: () -> Unit,
    onOpenMarketplace: () -> Unit,
    onOpenTools: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OledBlack)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // --- En-tete du Hub ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "<- Retour",
                color = TextSecondary,
                fontSize = 13.sp,
                modifier = Modifier.clickable { onBackToSilence() }
            )

            Text(
                text = "84%",
                color = SageGreen,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Mes Espaces",
                color = TextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(CardDarkVariant)
                    .border(1.dp, BorderDark, CircleShape)
                    .clickable { /* Creer un nouveau groupe */ }
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "+ Groupe",
                    color = SageGreen,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Text(
            text = "2 groupes vous attendent. Le reste se repose.",
            color = TextSecondary,
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 18.dp)
        )

        // --- Liste des Cartes de Groupes (Feed) ---
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(groups) { group ->
                GroupCardItem(
                    group = group,
                    onClick = { onSelectGroup(group) }
                )
            }
        }

        // --- Navigation inferieure sobre ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Boîte a outils",
                color = TextSecondary,
                fontSize = 12.sp,
                modifier = Modifier.clickable { onOpenTools() }
            )

            Text(
                text = "Marketplace Plugins",
                color = SlateBlue,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { onOpenMarketplace() }
            )
        }
    }
}

@Composable
fun GroupCardItem(
    group: ActionGroup,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CardDark)
            .border(1.dp, BorderDark, RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        // Haut de carte : Titre et Statut
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = group.title,
                color = TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = group.statusText,
                color = if (group.isMuted) TextMuted else SoftAmber,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Apercu d'alerte / notification
        if (group.notifications.isNotEmpty()) {
            val notif = group.notifications.first()
            Text(
                text = "${notif.sender} : ${notif.message}",
                color = TextPrimary,
                fontSize = 13.sp,
                maxLines = 1
            )
        }

        // Apercu de todo
        if (group.todos.isNotEmpty()) {
            val todo = group.todos.first()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    text = if (todo.isDone) "[x]" else "[ ]",
                    color = if (todo.isDone) SageGreen else TextMuted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = todo.text,
                    color = if (todo.isDone) TextMuted else TextSecondary,
                    fontSize = 13.sp,
                    maxLines = 1
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Raccourcis d'outils du groupe
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            group.appShortcuts.forEach { appName ->
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(CardDarkVariant)
                        .border(1.dp, BorderDark, CircleShape)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = appName,
                        color = TextSecondary,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
