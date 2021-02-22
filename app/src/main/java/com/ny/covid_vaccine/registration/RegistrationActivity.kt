package com.ny.covid_vaccine.registration

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ny.covid_vaccine.R
import com.ny.covid_vaccine.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var mLoginBinding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLoginBinding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(mLoginBinding.root)

        setClickListeners()
    }

    private fun setClickListeners() {
        mLoginBinding.registerBt.setOnClickListener {
            //to validate the fields
            // and finish the activity
            Toast.makeText(this,"Registration Success !",Toast.LENGTH_LONG).show()
            finish()
        }
    }
}