package apiteam.allpoisonim.features.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apiteam.allpoisonim.R

class BookStoreAdapter(val context: Context, var storeList: List<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_store, parent, false)

        return StoreViewHolder(view)
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StoreViewHolder) {
            holder.bind(storeList[position])
        }
    }

    class StoreViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(number: Int) {

        }
    }
}