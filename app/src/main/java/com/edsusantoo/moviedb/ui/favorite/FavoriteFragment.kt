package com.edsusantoo.moviedb.ui.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.edsusantoo.moviedb.R
import com.edsusantoo.moviedb.data.movie.MovieData
import com.edsusantoo.moviedb.databinding.FragmentFavoriteBinding
import com.edsusantoo.moviedb.utils.RootUtils
import com.google.gson.Gson

class FavoriteFragment : Fragment() {

    private var _binding :FragmentFavoriteBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun instance() = FavoriteFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        setListFavorite()
        setSearch()
    }

    private fun setListFavorite(){
        val data = RootUtils.readJsonAsset(requireContext(),"upcoming.json")
        val gson = Gson()
        val listUpComing = gson.fromJson(data, MovieData::class.java)
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavorite.adapter = FavoriteAdapter(listUpComing.results)
    }

    private fun setSearch(){
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            @SuppressLint("SetTextI18n")
            override fun onQueryTextChange(p0: String?): Boolean {

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