package com.fyaman.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.fyaman.weatherapp.R
import com.fyaman.weatherapp.databinding.FragmentDetailBinding



class DetailFragment : Fragment() {
    private lateinit var navController: NavController
    private var lat: Double = 42.10
    private var lon: Double = 26.14
    private var binding: FragmentDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val _binding = FragmentDetailBinding.bind(view)
        binding = _binding
        println("on view created")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("on create3")
        binding?.buttonWeather?.setOnClickListener(){
            //var latt = binding?.etLat.toString()
            //lonn = binding?.etLat.toString()
            /*navController.navigate(R.id.action_detailFragment_to_mainFragment,Bundle().apply {
                println(latt)
                println(lon)
                putDouble("lat",lat)
                putDouble("lon",lat)
            })

             */
        }
        // Inflate the layout for this fragment
        println("on create4")
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}