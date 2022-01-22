package ru.dudar_ig.swetotehnika.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.data.HomeData


class HomeAdapter(private val homeData: List<HomeData>):
    RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    class HomeHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titleV : TextView = itemView.findViewById(R.id.title_textView)
        //val textV : TextView = itemView.findViewById(R.id.text_textView)
        val photoV: ImageView = itemView.findViewById(R.id.image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_home_item, parent, false))
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val homeData = homeData[position]
        holder.photoV.setImageResource(homeData.foto)
        holder.titleV.text = homeData.title
        //holder.textV.text = homeData.text

//        holder.itemView.setOnClickListener {
//            val intent = OneActivity.newIntent(myContext, cat.name, cat.color, age, cat.date)
//            myContext.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return homeData.size
    }
}