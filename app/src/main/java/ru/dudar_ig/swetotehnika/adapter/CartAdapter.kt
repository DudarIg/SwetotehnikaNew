package ru.dudar_ig.swetotehnika.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import ru.dudar_ig.swetotehnika.R
import ru.dudar_ig.swetotehnika.database.Product
import ru.dudar_ig.swetotehnika.ui.MainActivity
import java.lang.RuntimeException
import kotlin.coroutines.coroutineContext

class CartAdapter( var listProd: ArrayList<Product>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    var funDelClick: ((Product) -> Unit)? = null
    var funEditClick: ((Product) -> Unit)? = null
    var funItogClick: ((ArrayList<Product>) -> Unit)? = null

    class TovarHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val nameTv: TextView = itemView.findViewById(R.id.name_tv)
        val priceTv : TextView = itemView.findViewById(R.id.price_tv)
        val countTv : TextView = itemView.findViewById(R.id.count_tv)
        val summaTv : TextView = itemView.findViewById(R.id.summ_tv)
        val editIb: Chip = itemView.findViewById(R.id.edit_ib)
        val delIb: Chip = itemView.findViewById(R.id.del_ib)

        fun setData(product: Product) {
            nameTv.text = product.name
            priceTv.text = "${product.price} ₽"
            countTv.text = product.count.toString()
            val summa = product.price.toFloat() * product.count
            summaTv.text = String.format("%.2f", summa)+" ₽"

        }
    }

    class ItogHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val emailChip: Chip = itemView.findViewById(R.id.email_chip)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  if (viewType == TOVAR) {
            TovarHolder(inflater.inflate(R.layout.layout_cart_product, parent, false) as View)
        } else {
            ItogHolder(inflater.inflate(R.layout.layout_cart_itog, parent, false) as View)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = listProd[position]
        if (getItemViewType(position) == TOVAR) {
            holder as TovarHolder
            holder.setData(product)
            holder.delIb.setOnClickListener {
                funDelClick?.invoke(product)
            }
            holder.editIb.setOnClickListener {
                funEditClick?.invoke(product)
            }
        }
        if (getItemViewType(position) == ITOG) {
            holder as ItogHolder
            holder.emailChip.setOnClickListener {
                funItogClick?.invoke(listProd)
            }

        }


    }

    override fun getItemViewType(position: Int): Int {
        return if (position + 1 != listProd.size) {
            TOVAR
        } else {
            ITOG
        }

    }

    override fun getItemCount(): Int {
        return listProd.size
    }

    fun updateAdapter(listItems: List<Product>){
        listProd.clear()
        listProd.addAll(listItems)
        if (listProd.size > 0 ) {
            listProd.add(Product())
        }

        notifyDataSetChanged()
    }
    companion object {
        const val TOVAR = 100
        const val ITOG = 101
    }
}