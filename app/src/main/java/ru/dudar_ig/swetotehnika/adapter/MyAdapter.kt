package ru.dudar_ig.swetotehnika.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ru.dudar_ig.swetotehnika.KatId
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.data.Tovar


class MyAdapter(var listArray: ArrayList<Tovar>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {
//    var listArray = listMain
//    var context = contextM

    class MyHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName : TextView = itemView.findViewById(R.id.textView)
        //val iV = itemView
        //val context: Context = contextV

        fun setData(listBuk: Tovar){
            tvName.text = listBuk.name
            if (KatId.kat==3) {
                val tvPrice : TextView = itemView.findViewById(R.id.tv2)
                tvPrice.text= "${listBuk.price} ₽"
                val tvImage : ImageView = itemView.findViewById(R.id.im2)
                tvImage.load(listBuk.foto)
            }
            if (KatId.kat==4) {
                val tvPrice : TextView = itemView.findViewById(R.id.tv2)
                tvPrice.text= "${listBuk.price} ₽"
                val tvProp : TextView = itemView.findViewById(R.id.tv3)
                tvProp.text= listBuk.prop
                val tvImage : ImageView = itemView.findViewById(R.id.im2)
                tvImage.load(listBuk.foto)
            }

            // обработчик нажатия на строку RecyclerViev
//            itemView.setOnClickListener {
//                if (KatId.kat == 1) {
//                    val intent = Intent(context, MainActivity2::class.java).apply {
//                        putExtra("idd", listBuk.id)
//                        putExtra("idd_name", listBuk.name)
//                    }
//                    context.startActivity(intent)
//                }
//                if (KatId.kat == 2) {
//                    val intent = Intent(context, MainActivity3::class.java).apply {
//                        putExtra("idd", listBuk.id)
//                        putExtra("idd_name", listBuk.name)
//                    }
//                    context.startActivity(intent)
//                }
//                if (KatId.kat == 3) {
//                    val intent = Intent(context, MainActivityOne::class.java).apply {
//                        putExtra("idd", listBuk.id)
//                        putExtra("idd_name", listBuk.name)
//                    }
//                    context.startActivity(intent)
//                }
//            }
        }
    }

    // готовим шаблон для рисования
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context) // спецкласс для надувания rc_shablon.xml
        if (KatId.kat==3) {
            val myInflater = inflater.inflate(R.layout.layout_item_products, parent, false)
            return MyHolder(myInflater)
        }
        if (KatId.kat==4) {
            val myInflater = inflater.inflate(R.layout.layout_one, parent, false)
            return MyHolder(myInflater)
        }
        else
        {
            val myInflater = inflater.inflate(R.layout.layout_item, parent, false)
            return MyHolder(myInflater)
        }
    }
    // количество элементов в списке
    override fun getItemCount(): Int {
        return listArray.size
    }
    // подключает данные с позиции массива к нарисованному шаблону
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(listArray.get(position))
    }
    // обновление адаптер
    fun updateAdapter(listItems: List<Tovar>){
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }
}