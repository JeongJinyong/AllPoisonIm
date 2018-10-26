package apiteam.allpoisonim.features.main.bookstoreviewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.data.BookStore
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewholder_bookstore_type_2.view.*
import java.lang.Exception

class BookStoreType2(val view: View) : RecyclerView.ViewHolder(view) {
    private val ivContent = view.iv_content
    private val tvContent = view.tv_content

    fun bind(content: BookStore.BookStoreImages) {
        Picasso.get().load(content.image_url).into(ivContent, object : Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
                ivContent.setImageResource(R.drawable.test1)
            }
        })
        tvContent.text = content.contents
    }
}