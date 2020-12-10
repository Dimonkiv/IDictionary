package com.dimonkiv.idictionary.ui.modules.settings

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.dimonkiv.idictionary.R
import com.dimonkiv.idictionary.data.models.FragmentData
import com.dimonkiv.idictionary.ui.modules.MainActivity
import com.dimonkiv.idictionary.utills.FragmentById

class SettingsFragment : Fragment() {

    private lateinit var root: View
    private lateinit var repeatRL: LinearLayout
    private lateinit var notificationSwitch: SwitchCompat

    private val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_settings, container, false)

        initUI()
        initNotification()
        return root
    }

    private fun initNotification() {
        createNotificationChannel(context!!,
                NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
                getString(R.string.app_name), "App notification channel.")
    }

    private fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) {
        // 1
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // 2
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            // 3
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }


    private fun initUI() {
        repeatRL = root.findViewById<LinearLayout>(R.id.repeat_rl).apply {
            setOnClickListener {
                mainActivity.changeFragment(FragmentData(FragmentById.REPEAT_WORD_FRAGMENT))
            }
        }

        notificationSwitch = root.findViewById<SwitchCompat>(R.id.notification_switch).apply {
            setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) showNotification()
            }
        }
    }


    private fun showNotification() {
        val channelID = "${context?.packageName}-${context?.getString(R.string.app_name)}"
        val notificationBuilder = NotificationCompat.Builder(context!!, channelID).apply {
            setSmallIcon(R.drawable.ic_notification) // 3
            setContentTitle("IDictionary") // 4
            setContentText("Друже виділи декілька хвилинок і повтори вивчені слова.") // 5 // 6
            priority = NotificationCompat.PRIORITY_DEFAULT // 7
            setAutoCancel(true) // 8
        }

        NotificationManagerCompat.from(context!!).notify(1001, notificationBuilder.build())
    }
}