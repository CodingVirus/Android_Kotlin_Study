package com.example.applemarket.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.data.Product
import com.example.applemarket.data.ProductList
import com.example.applemarket.databinding.RecyclerItemBinding

class ProductListAdapter(private val list: MutableList<Product>) : RecyclerView.Adapter<ProductListAdapter.ProductHolder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
        fun onLongClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null

    fun removeItem(position: Int) {
        if (position < list.size) {
            list.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position, list.size - position)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        runCatching {
            holder.itemView.setOnLongClickListener {
                itemClick?.onLongClick(it, position)
                false
            }
            holder.itemView.setOnClickListener {
                itemClick?.onClick(it, position)
            }
            list[position].run {
                holder.bind(this)
            }
        }.onFailure { exception ->
            Log.e("ProductListAdapter", "Exception! ${exception.message}")
        }
    }

    inner class ProductHolder(binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {
        private val productImg = binding.productImage
        private val productName = binding.productName
        private val region = binding.regionName
        private val pricing = binding.price
        private val heartNum = binding.heartNumberText
        private val commentNum = binding.commentNumberText
        private var isHeartFill = false

        fun bind(product: Product) {
            with(product) {
                productImg.setImageResource(image)
                productName.text = name
                pricing.text = price + "원"
                region.text = address
                heartNum.text = like.toString()
                commentNum.text = comments.toString()
                isHeartFill = isLike
            }
        }
    }
}