package com.edsusantoo.moviedb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.edsusantoo.moviedb.*
import com.edsusantoo.moviedb.databinding.ActivityMainBinding
import com.edsusantoo.moviedb.ui.favorite.FavoriteFragment
import com.edsusantoo.moviedb.ui.home.HomeFragment
import com.edsusantoo.moviedb.ui.marker.MarkerFragment
import com.edsusantoo.moviedb.ui.popular.PopularFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()

    }

    private fun initView(){
        setCurrentFragment(HomeFragment.instance())
        binding.bottomNavigation.setOnItemSelectedListener { menu ->
            when(menu.itemId){
                R.id.home -> setCurrentFragment(HomeFragment.instance())
                R.id.award -> setCurrentFragment(PopularFragment.instance())
                R.id.favorite -> setCurrentFragment(FavoriteFragment.instance())
                R.id.marker -> setCurrentFragment(MarkerFragment.instance())
                else -> setCurrentFragment(HomeFragment.instance())
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomNavigation.setOnItemReselectedListener {  }
    }

    private fun setCurrentFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_container,fragment)
            commit()
        }
    }
}