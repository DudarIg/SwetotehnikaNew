package ru.dudar_ig.swetotehnika.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.data.Tovar
import ru.dudar_ig.swetotehnika.database.Product

class CartAdapter( var listProd: ArrayList<Product>):
    RecyclerView.Adapter<CartAdapter.MyHolder>() {

    var funDelClick: ((Product) -> Unit)? = null
    var funEditClick: ((Product) -> Unit)? = null

    class MyHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {

        val nameTv: TextView = itemView.findViewById(R.id.name_tv)
        val priceTv : TextView = itemView.findViewById(R.id.price_tv)
        val countTv : TextView = itemView.findViewById(R.id.count_tv)
        val summaTv : TextView = itemView.findViewById(R.id.summ_tv)
        val editIb: Chip = itemView.findViewById(R.id.edit_ib)
        val delIb: Chip = itemView.findViewById(R.id.del_ib)

        fun setData(product: Product) {
            nameTv.text = product.name
            priceTv.text = product.price
            countTv.text = product.count.toString()
            val summa = product.price.toFloat() * product.count
            summaTv.text = String.format("%.2f", summa)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_cart_product, parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val product = listProd[position]

        holder.setData(product)
        holder.delIb.setOnClickListener {
            funDelClick?.invoke(product)
        }

//        holder.itemView.setOnClickListener {
//            if (holder.viv.isChecked) {
//                holder.viv.isChecked = false
//                ganr.viv = false
//            }
//            else {
//                holder.viv.isChecked = true
//                ganr.viv = true}
//        }

    }

    override fun getItemCount(): Int {
        return listProd.size
    }

    fun updateAdapter(listItems: List<Product>){
        listProd.clear()
        listProd.addAll(listItems)
        notifyDataSetChanged()
    }
}