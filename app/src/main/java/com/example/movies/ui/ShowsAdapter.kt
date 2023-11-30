package com.example.movies.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movies.data.Shows
import com.example.movies.databinding.ShowsLayoutBinding

class ShowsAdapter :RecyclerView.Adapter<ShowsAdapter.ShowsViewHolder>() {

     private var shows = mutableListOf<Shows>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ShowsLayoutBinding.inflate(inflater, parent, false)
        return ShowsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShowsViewHolder, position: Int) {
        val show = shows[position]
        holder.bind(show)
    }

    fun setShowsList(show : List<Shows>){
        this.shows = show.toMutableList()
        notifyDataSetChanged()
    }
    override fun getItemCount()= shows.size

    inner class ShowsViewHolder(val binding: ShowsLayoutBinding) : ViewHolder(binding.root){
        fun bind(show : Shows){
            binding.apply {
                Glide.with(binding.root).load(show.image).into(showImage)
                tvShowName.text = show.name
            }
        }
    }
}