package com.edsusantoo.moviedb.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.edsusantoo.moviedb.data.dummy.Banner
import com.edsusantoo.moviedb.data.movie.MovieData
import com.edsusantoo.moviedb.databinding.FragmentHomeBinding
import com.edsusantoo.moviedb.utils.RootUtils
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.google.gson.Gson

class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun instance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        setBanner()
        setPopular()
        setUpcoming()
    }

    private fun setBanner(){
        val banner :ArrayList<Banner> = ArrayList()
        banner.add(Banner("https://image.tmdb.org/t/p/original/gespPE81A2RYvMxl9XaVNvIgevS.jpg"))
        banner.add(Banner("https://image.tmdb.org/t/p/original/eENEf62tMXbhyVvdcXlnQz2wcuT.jpg"))
        banner.add(Banner("https://image.tmdb.org/t/p/original/7ajHGIAYNMiIzejy1LJWdPrcAx8.jpg"))

        binding.slider.setSliderAdapter(BannerAdapter(banner))
        binding.slider.setIndicatorAnimation(IndicatorAnimationType.WORM)
    }
    private fun setPopular(){
        val data = RootUtils.readJsonAsset(requireContext(),"popular.json")
        val gson = Gson()
        val listPopular = gson.fromJson(data,MovieData::class.java)
        val adapter = PopularAdapter(listPopular.results)
        binding.rvPopular.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvPopular.adapter = adapter
    }

    private fun setUpcoming(){
        val data = RootUtils.readJsonAsset(requireContext(),"upcoming.json")
        val gson = Gson()
        val listPopular = gson.fromJson(data,MovieData::class.java)
        val adapter = UpComingAdapter(listPopular.results)
        binding.rvComingSoon.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
        binding.rvComingSoon.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}