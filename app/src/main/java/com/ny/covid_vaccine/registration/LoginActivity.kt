package com.ny.covid_vaccine.registration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ny.covid_vaccine.R
import com.ny.covid_vaccine.databinding.ActivityLoginBinding
import com.ny.covid_vaccine.utils.APPConstants

class LoginActivity : AppCompatActivity() {


    private lateinit var mLoginBinding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mLoginBinding.root)

        setClickListeners()
    }

    private fun setClickListeners(){
        mLoginBinding.signUp.setOnClickListener {
            startActivity(Intent(this,RegistrationActivity::class.java))
        }

        mLoginBinding.Login.setOnClickListener {
            val sped = getSharedPreferences(APPConstants.APP_SHARED_PREF, Context.MODE_PRIVATE)
                .edit()

            sped.putBoolean(APPConstants.User.IS_LOGGED_IN,true)
            sped.apply()


        }
    }
}