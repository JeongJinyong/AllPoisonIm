package apiteam.allpoisonim.features.books

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apiteam.allpoisonim.R

class ReplyAdapter(val context: Context, var replyList: List<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_reply, parent, false)
        return ReplyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return replyList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ReplyViewHolder) {
            holder.bind(replyList[position])
        }
    }

    // ViewHolder Class
    class ReplyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bind(number: Int) {
        }
    }
}