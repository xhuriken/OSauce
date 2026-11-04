package com.osauce.launcher.core.designsystem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.osauce.launcher.data.model.SphereIcon

/**
 * Custom vector icons for spheres and minimalist hub controls.
 * Pure geometric stroke vectors without any emojis or ASCII symbols.
 */
object OSauceIcons {

    val Add = Icons.Rounded.Add
    val Back = Icons.AutoMirrored.Rounded.ArrowBack
    val Close = Icons.Rounded.Close
    val Search = Icons.Rounded.Search
    val Star = Icons.Rounded.Star
    val Check = Icons.Rounded.Check
    val Delete = Icons.Rounded.Delete
    val Schedule = Icons.Rounded.Schedule
    val Tune = Icons.Rounded.Tune

    /**
     * Vector icon for Users / Humans sphere.
     */
    val SphereUsers: ImageVector by lazy {
        ImageVector.Builder(
            name = "SphereUsers",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color.White),
                strokeLineWidth = 1.6f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                // Primary head circle
                moveTo(9f, 7f)
                arcTo(3.5f, 3.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, 9f, 14f)
                arcTo(3.5f, 3.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, 9f, 7f)
                // Primary body arc
                moveTo(3.5f, 20f)
                curveTo(3.5f, 16.5f, 6.2f, 15f, 9f, 15f)
                curveTo(11.8f, 15f, 14.5f, 16.5f, 14.5f, 20f)
                // Secondary person
                moveTo(16f, 7.5f)
                arcTo(2.5f, 2.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16f, 12.5f)
                moveTo(15f, 15.2f)
                curveTo(16.5f, 15.6f, 19.5f, 16.8f, 19.5f, 20f)
            }
        }.build()
    }

    /**
     * Vector icon for Work / Focus sphere.
     */
    val SphereBriefcase: ImageVector by lazy {
        ImageVector.Builder(
            name = "SphereBriefcase",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color.White),
                strokeLineWidth = 1.6f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                // Briefcase outer rectangle
                moveTo(4f, 8f)
                lineTo(20f, 8f)
                lineTo(20f, 19f)
                lineTo(4f, 19f)
                close()
                // Top handle
                moveTo(9f, 8f)
                lineTo(9f, 5.5f)
                lineTo(15f, 5.5f)
                lineTo(15f, 8f)
                // Horizontal partition line
                moveTo(4f, 13f)
                lineTo(20f, 13f)
            }
        }.build()
    }

    /**
     * Vector icon for Vital sphere (Heart pulse line).
     */
    val SpherePulse: ImageVector by lazy {
        ImageVector.Builder(
            name = "SpherePulse",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color.White),
                strokeLineWidth = 1.6f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(3f, 12f)
                lineTo(7f, 12f)
                lineTo(9.5f, 6.5f)
                lineTo(14f, 18.5f)
                lineTo(16.5f, 12f)
                lineTo(21f, 12f)
            }
        }.build()
    }

    /**
     * Vector icon for Leisure sphere (Leaf curve).
     */
    val SphereLeaf: ImageVector by lazy {
        ImageVector.Builder(
            name = "SphereLeaf",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color.White),
                strokeLineWidth = 1.6f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(5f, 19f)
                curveTo(5f, 19f, 6f, 13f, 11f, 8f)
                curveTo(16f, 3f, 20f, 3f, 20f, 3f)
                curveTo(20f, 3f, 20f, 7f, 15f, 12f)
                curveTo(10f, 17f, 5f, 19f, 5f, 19f)
                close()
                moveTo(5f, 19f)
                lineTo(12f, 12f)
            }
        }.build()
    }

    /**
     * Resolver mapping sphere enum to its vector icon.
     */
    fun forSphere(icon: SphereIcon): ImageVector {
        return when (icon) {
            SphereIcon.USERS -> SphereUsers
            SphereIcon.BRIEFCASE -> SphereBriefcase
            SphereIcon.PULSE -> SpherePulse
            SphereIcon.LEAF -> SphereLeaf
            SphereIcon.STAR -> Star
            SphereIcon.SHIELD -> SphereBriefcase
            SphereIcon.TERMINAL -> SphereBriefcase
            SphereIcon.COMPASS -> SphereLeaf
        }
    }
}
