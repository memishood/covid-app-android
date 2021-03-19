package com.covidapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.covidapp.databinding.LayoutNewsBinding
import com.covidapp.model.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    private val items = arrayListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder = NewsHolder(
        LayoutNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val item = items[holder.adapterPosition]
        holder.binding.news = item

        holder.itemView
            .setOnClickListener {
                it.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToNewsDetailFragment(item))
            }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun update(news: List<News>?) {
        news?.let {
            items.clear()
            items.addAll(it)
            notifyDataSetChanged()
        }
    }

    class NewsHolder(val binding: LayoutNewsBinding) : RecyclerView.ViewHolder(binding.root)
}