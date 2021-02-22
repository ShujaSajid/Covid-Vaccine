package com.ny.covid_vaccine

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.ny.covid_vaccine.registration.LoginActivity
import com.ny.covid_vaccine.utils.APPConstants

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    val ctTimer = object : CountDownTimer(10*1000L,1L){
        override fun onFinish() {

            runOnUiThread {

                if(isLoggedIn()){
                    // Go to home screen
                }else {
                    //Login screen
                    val intent =
                    startActivity(Intent(this@SplashActivity,LoginActivity::class.java))
                }
            }

        }

        override fun onTick(p0: Long) {
           Log.i("TAG","onTick $p0")
        }

    }


    private fun isLoggedIn(): Boolean {
        return getSharedPreferences(APPConstants.APP_SHARED_PREF, Context.MODE_PRIVATE)
            .getBoolean(APPConstants.User.IS_LOGGED_IN,false)
    }
}