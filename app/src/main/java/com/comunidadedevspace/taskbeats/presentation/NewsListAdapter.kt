package com.comunidadedevspace.taskbeats.presentation

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.News

class NewsListAdapter(): ListAdapter<News, NewsListViewHolder>(NewsListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val views = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_news,parent,false)

        return NewsListViewHolder(views)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
            val news = getItem(position)
        holder.bind(news)
    }

    companion object : DiffUtil.ItemCallback<News>() {

        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.imgUrl == newItem.imgUrl
        }
    }


}


class  NewsListViewHolder(
        private val view: View
) : RecyclerView.ViewHolder(view) {
    private val tvTitle = view.findViewById<TextView>(R.id.tv_news)
    private val ivNews = view.findViewById<ImageView>(R.id.iv_news)

    fun bind(

        news: News
    ) {
        tvTitle.text = news.title
        ivNews.load(news.imgUrl){
            transformations(RoundedCornersTransformation(12f))
        }

    }
}



