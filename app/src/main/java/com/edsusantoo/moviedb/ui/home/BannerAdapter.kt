package com.edsusantoo.moviedb.ui.home

import android.transition.Slide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.edsusantoo.moviedb.R
import com.edsusantoo.moviedb.data.dummy.Banner
import com.edsusantoo.moviedb.databinding.ItemBannerBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class BannerAdapter(var mSliderItems:List<Banner>) : SliderViewAdapter<BannerAdapter.SliderAdapterVH>() {

    override fun getCount(): Int {
        return mSliderItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): BannerAdapter.SliderAdapterVH {
        val view = ItemBannerBinding.inflate(LayoutInflater.from(parent?.context),parent,false)
        return SliderAdapterVH(view)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH?, position: Int) {
        viewHolder?.binding?.let {
            Glide.with(it.root)
                .load(mSliderItems[position].url)
                .fitCenter()
                .into(viewHolder.binding.imgPoster)
        };
    }

    inner class SliderAdapterVH(val binding: ItemBannerBinding?) : SliderViewAdapter.ViewHolder(binding?.root)


}