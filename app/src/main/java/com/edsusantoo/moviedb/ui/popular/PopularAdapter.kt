package com.edsusantoo.moviedb.ui.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edsusantoo.moviedb.data.constant.ConstantVariable
import com.edsusantoo.moviedb.data.movie.Result
import com.edsusantoo.moviedb.databinding.ItemPopularBinding

class PopularAdapter(private val list:List<Result>) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapter.PopularViewHolder {
        val view = ItemPopularBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularAdapter.PopularViewHolder, position: Int) {
        holder.binding.let {
            Glide.with(it.root)
                .load(ConstantVariable.BASE_URL_IMAGE+list[position].posterPath)
                .fitCenter()
                .into(it.imgPoster)

            it.tvTitle.text = list[position].title
            it.tvArtist
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PopularViewHolder(val binding:ItemPopularBinding) : RecyclerView.ViewHolder(binding.root)
}