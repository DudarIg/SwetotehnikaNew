package ru.dudar_ig.swetotehnika.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import ru.dudar_ig.swetotehnika.KatId
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.data.Tovar


class MyAdapter: RecyclerView.Adapter<MyAdapter.MyHolder>() {

    var listArray = ArrayList<Tovar>()

    var funCatClick: ((Tovar) -> Unit)? = null
    var funListClick: ((Tovar) -> Unit)? = null
    var funProductClick: ((Tovar) -> Unit)? = null
    var funCartButtonClick: ((Tovar) -> Unit)? = null
    lateinit var cat : ImageButton

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv = itemView

        fun setData(listBuk: Tovar){
            val tvName : TextView = iv.findViewById(R.id.textView)
            tvName.text = listBuk.name
            if (KatId.kat==3) {
                val tvPrice : TextView = iv.findViewById(R.id.tv2)
                tvPrice.text= "${listBuk.price} ₽"
                val tvImage : ImageView = iv.findViewById(R.id.im2)
                tvImage.load(listBuk.foto)
            }
            if (KatId.kat==4) {
                val tvPrice : TextView = iv.findViewById(R.id.tv2)
                tvPrice.text= "${listBuk.price} ₽"
                val tvProp : TextView = iv.findViewById(R.id.tv3)
                tvProp.text= listBuk.prop
                val tvImage : ImageView = iv.findViewById(R.id.im2)
                tvImage.load(listBuk.foto)

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context) // спецкласс для надувания rc_shablon.xml

        if (KatId.kat==3) {
            val view = inflater.inflate(R.layout.layout_item_products, parent, false)
            return MyHolder(view)
        }
        if (KatId.kat==4) {
            val view = inflater.inflate(R.layout.layout_one, parent, false)
            return MyHolder(view)
        }
        else
        {
            val view = inflater.inflate(R.layout.layout_item, parent, false)
            return MyHolder(view)
        }
    }
    // количество элементов в списке
    override fun getItemCount(): Int {
        return listArray.size
    }
    // подключает данные с позиции массива к нарисованному шаблону
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(listArray.get(position))

        if (KatId.kat == 4) {
            val vv = listArray[position]
            cat = holder.iv.findViewById(R.id.cart_button)
            cat.setOnClickListener {
                funCartButtonClick?.invoke(vv)
            }
        }

        // обработчик нажатия на строку RecyclerViev
        holder.iv.setOnClickListener {
            if (KatId.kat == 1) {
                funCatClick?.invoke(listArray.get(position))
            }
            if (KatId.kat == 2) {
                funListClick?.invoke(listArray.get(position))
            }
            if (KatId.kat == 3) {
                funProductClick?.invoke(listArray.get(position))
            }

        }


    }
    // обновление адаптер
    fun updateAdapter(listItems: List<Tovar>){
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }
}