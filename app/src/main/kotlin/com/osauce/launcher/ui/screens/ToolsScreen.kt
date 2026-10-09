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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.osauce.launcher.ui.theme.*

data class ToolApp(
    val letter: String,
    val name: String,
    val isSystemNative: Boolean = true,
    val packageName: String
)

@Composable
fun ToolsScreen(
    onBack: () -> Unit,
    onLaunchApp: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val allTools = listOf(
        ToolApp("A", "Agenda", true, "com.google.android.calendar"),
        ToolApp("A", "Appareil photo", true, "com.android.camera"),
        ToolApp("B", "Banque Populaire", false, "fr.banquepopulaire.mobile"),
        ToolApp("C", "Calculatrice", true, "com.android.calculator2"),
        ToolApp("C", "Contacts", true, "com.android.contacts"),
        ToolApp("F", "Fichiers", true, "com.android.documentsui"),
        ToolApp("H", "Horloge", true, "com.android.deskclock"),
        ToolApp("L", "Livre / Lecteur PDF", true, "org.readera"),
        ToolApp("M", "Messagerie Mail", true, "ch.protonmail.android"),
        ToolApp("M", "Musique VLC", true, "org.videolan.vlc"),
        ToolApp("S", "SNCF Connect", false, "com.sncf.fusion"),
        ToolApp("S", "Signal", true, "org.thoughtcrime.securesms")
    )

    val filteredTools = if (searchQuery.isBlank()) {
        allTools
    } else {
        allTools.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OledBlack)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // En-tete
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
                text = "A - Z",
                color = TextMuted,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Les Outils",
            color = TextPrimary,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        // Barre de recherche sobre
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(CardDark)
                .border(1.dp, BorderDark, RoundedCornerShape(14.dp))
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Rechercher une application...",
                color = TextMuted,
                fontSize = 13.sp
            )
        }

        // Liste alphabétique
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredTools) { tool ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(CardDark)
                        .clickable { onLaunchApp(tool.packageName) }
                        .padding(horizontal = 14.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = tool.letter,
                            color = SoftAmber,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.width(24.dp)
                        )
                        Text(
                            text = tool.name,
                            color = TextPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(CardDarkVariant)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = if (tool.isSystemNative) "Natif" else "Securise",
                            color = if (tool.isSystemNative) SageGreen else SlateBlue,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
