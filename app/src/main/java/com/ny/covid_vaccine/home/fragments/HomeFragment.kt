package com.ny.covid_vaccine.home.fragments

import android.app.AppComponentFactory
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ny.covid_vaccine.R
import com.ny.covid_vaccine.databinding.FragmentHomeBinding
import com.ny.covid_vaccine.utils.APPConstants


class HomeFragment : Fragment() {


    private var mVaccineRegistration:FragmentHomeBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mVaccineRegistration = FragmentHomeBinding.inflate(inflater,container,false)
        return mVaccineRegistration!!.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setClickListeners()

        val sed = requireActivity().getSharedPreferences(APPConstants.APP_SHARED_PREF,Context.MODE_PRIVATE)
        val dosageCount = sed.getInt(APPConstants.User.DOSAGE_COUNT,0)
        when(dosageCount){
            0 -> mVaccineRegistration!!.register.setText("register 1st vaccine")
            1 -> mVaccineRegistration!!.register.setText("register 1st vaccine")
            2 ->mVaccineRegistration!!.register.setText("register 1st vaccine")
        }

    }

    override fun onResume() {
        super.onResume()
        val sed = requireActivity().getSharedPreferences(APPConstants.APP_SHARED_PREF,Context.MODE_PRIVATE)
        val dosageCount = sed.getInt(APPConstants.User.DOSAGE_COUNT,0)
        when(dosageCount){
            0 -> mVaccineRegistration!!.register.setText("register 1st vaccine")
            1 -> mVaccineRegistration!!.register.setText("register 2nd vaccine")
            2 ->{
                mVaccineRegistration!!.register.setText("Registerations completed")
                mVaccineRegistration!!.register.isClickable = false
            }
        }
    }

    private fun setClickListeners() {
        mVaccineRegistration!!.helpline.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:1900")  //co-vid helpline number
            startActivity(intent)
        }

        mVaccineRegistration!!.register.setOnClickListener {
            //navigate to vaccine registration fragment

            mVaccineRegistration!!.register.findNavController().navigate(R.id.action_homeFragment_to_vaccineRegistrationFragment)


        }


        mVaccineRegistration!!
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment()
    }
}