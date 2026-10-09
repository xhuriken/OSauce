package com.osauce.launcher.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationService : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        if (sbn == null) return

        val packageName = sbn.packageName
        val extras = sbn.notification.extras
        val title = extras.getString("android.title") ?: "Notification"
        val text = extras.getCharSequence("android.text")?.toString() ?: ""

        Log.d("OSauceNotifications", "Alerte interceptee de $packageName : $title - $text")

        // Le tri est effectue deterministiquement vers les groupes d'action :
        // - CATEGORY_MESSAGE -> Groupe Humain & Proches
        // - CATEGORY_TRANSPORT -> Groupe Quotidien & Vital
        // - CATEGORY_EMAIL -> Groupe Travail & Etudes
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        Log.d("OSauceNotifications", "Notification retiree")
    }
}
