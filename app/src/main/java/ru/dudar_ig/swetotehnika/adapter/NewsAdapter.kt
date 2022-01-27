package ru.dudar_ig.swetotehnika.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.data.News
import ru.dudar_ig.swetotehnika.data.Tovar


class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    private var newsData = ArrayList<News>()
    var funNewsClick: ((News) -> Unit)? = null

    class NewsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val iv = itemView
        val data : TextView = itemView.findViewById(R.id.date_textView)
        val title: TextView = itemView.findViewById(R.id.title_textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_news_item, parent, false))
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news = newsData[position]
        holder.data.text = news.date
        holder.title.text = news.name
        holder.iv.setOnClickListener {
            funNewsClick?.invoke(news)
        }
    }

    override fun getItemCount(): Int {
        return newsData.size
    }
    // обновление адаптер
    fun updateAdapter(listItems: List<News>){
        newsData.clear()
        newsData.addAll(listItems)
        notifyDataSetChanged()
    }
}