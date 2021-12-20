package com.edsusantoo.moviedb.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edsusantoo.moviedb.data.constant.ConstantVariable
import com.edsusantoo.moviedb.data.movie.Result
import com.edsusantoo.moviedb.databinding.ItemFavoriteBinding

class FavoriteAdapter(private val list:List<Result>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.FavoriteViewHolder {
        val view = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
        holder.binding.let {
            Glide.with(it.root)
                .load(ConstantVariable.BASE_URL_IMAGE+list[position].posterPath)
                .fitCenter()
                .into(it.imgPoster)

            it.tvTitle.text = list[position].title

            val releaseDate = list[position].releaseDate.split("-")
            it.tvYear.text = releaseDate[0]

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class FavoriteViewHolder(val binding:ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)
}