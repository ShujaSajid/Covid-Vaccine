package com.ny.covid_vaccine.utils

import android.content.Context
import android.content.SharedPreferences
import com.ny.covid_vaccine.CovidVaccineApplication

object Store {


    val sharedPref:SharedPreferences

    init {

        sharedPref = CovidVaccineApplication.getCovidApplicationContext().getSharedPreferences(APPConstants.APP_SHARED_PREF,Context.MODE_PRIVATE)
    }


    public fun getBoolean(key:String, default:Boolean = false): Boolean {
      return sharedPref.getBoolean(key,default)
    }


}

