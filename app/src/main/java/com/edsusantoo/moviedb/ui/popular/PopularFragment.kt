package com.edsusantoo.moviedb.ui.popular

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.edsusantoo.moviedb.R
import com.edsusantoo.moviedb.data.movie.MovieData
import com.edsusantoo.moviedb.databinding.FragmentPopularBinding
import com.edsusantoo.moviedb.utils.RootUtils
import com.google.gson.Gson

class PopularFragment : Fragment() {

    private var _binding:FragmentPopularBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun instance() = PopularFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        setPopular()
        setSearch()
    }

    private fun setPopular(){
        val data = RootUtils.readJsonAsset(requireContext(),"popular.json")
        val gson = Gson()
        val listPopular = gson.fromJson(data, MovieData::class.java)
        binding.rvPopular.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rvPopular.adapter = PopularAdapter(listPopular.results)
    }

    private fun setSearch(){
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            @SuppressLint("SetTextI18n")
            override fun onQueryTextChange(p0: String?): Boolean {
                if(p0?.isNotEmpty() == true){
                    binding.tvShowingResult.text = "Showing result of '${p0}'"
                }else{
                    binding.tvShowingResult.text = ""
                }
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}