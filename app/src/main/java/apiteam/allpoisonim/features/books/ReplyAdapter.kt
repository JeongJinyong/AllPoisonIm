package apiteam.allpoisonim.features.books

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.data.BookDetail
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_reply.*

class ReplyAdapter(val context: Context, var replyList: List<BookDetail.BookComments>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


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
    inner class ReplyViewHolder(val v: View) : RecyclerView.ViewHolder(v),LayoutContainer {
        override val containerView: View?
            get() = v
        fun bind(bookComments: BookDetail.BookComments) {
            tv_name.text = bookComments.userName
            tv_reply.text = bookComments.comment
        }
    }
}