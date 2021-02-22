package com.ny.covid_vaccine.home.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.ny.covid_vaccine.R
import com.ny.covid_vaccine.databinding.FragmentVaccineRegistrationBinding
import com.ny.covid_vaccine.home.adapter.CenterListAdapter
import com.ny.covid_vaccine.home.adapter.onItemClick
import com.ny.covid_vaccine.utils.APPConstants
import java.lang.Appendable


class VaccineRegistrationFragment : Fragment() {


    private val vaccineCenters = listOf<String>("Hyderabad","Kolkata","Delhi","Chennai","Bangalore")

    private lateinit var fusedLocationClient: FusedLocationProviderClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    var binding:FragmentVaccineRegistrationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVaccineRegistrationBinding.inflate(inflater,container,false)
        return binding!!.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())





        val adapter = CenterListAdapter(vaccineCenters,object :onItemClick{
            override fun onClick(city: String) {
                sendNotification(city)
            }

        })

        binding!!.centerList.adapter = adapter

        getLastKnownLocation()


    }


    private fun getLastKnownLocation(){


        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
           requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION),11)
            return
        }
        lastLocation()

    }

    @SuppressLint("MissingPermission")
    fun lastLocation(){
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                location?.let {
                    //re-arrange the list based on the centers
                }
            }
    }



    private fun sendNotification(cityName :String){
        val builder = NotificationCompat.Builder(requireContext(), APPConstants.User.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("$cityName For Registration Successful !")
            .setContentText("Please follow safety measurements")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            .setAutoCancel(true)

        with(NotificationManagerCompat.from(requireContext())) {
            // unique not required
            notify(123, builder.build())
        }

        val sed = requireActivity().getSharedPreferences(APPConstants.APP_SHARED_PREF, Context.MODE_PRIVATE)
        val dosageCount = sed.getInt(APPConstants.User.DOSAGE_COUNT,0)
        sed.edit()?.putInt(APPConstants.User.DOSAGE_COUNT,dosageCount+1)?.apply()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            lastLocation()
        }
    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VaccineRegistrationFragment()
    }
}