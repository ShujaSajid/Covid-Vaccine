package com.ny.covid_vaccine

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class CovidVaccineApplication: Application() {

    val CHANNEL_ID = "112233"
    val CHANNEL_NAME = "Covid-vaccine"

    init {

        mInstance = this
    }

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "COVID ALERT"
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }



    companion object{
        private var mInstance:CovidVaccineApplication? = null
        public  fun getCovidApplicationContext(): Context {
            return mInstance!!.applicationContext
        }
    }
}