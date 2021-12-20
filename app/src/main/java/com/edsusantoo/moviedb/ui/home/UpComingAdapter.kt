package com.edsusantoo.moviedb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edsusantoo.moviedb.data.constant.ConstantVariable
import com.edsusantoo.moviedb.data.movie.Result
import com.edsusantoo.moviedb.databinding.ItemImagePosterBinding

class UpComingAdapter(private val list:List<Result>) :
    RecyclerView.Adapter<UpComingAdapter.PopularViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpComingAdapter.PopularViewHolder {
        val view = ItemImagePosterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.binding.let {
            Glide.with(it.root)
                .load(ConstantVariable.BASE_URL_IMAGE+list[position].posterPath)
                .fitCenter()
                .into(it.imgPoster)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PopularViewHolder(val binding : ItemImagePosterBinding) : RecyclerView.ViewHolder(binding.root)
}