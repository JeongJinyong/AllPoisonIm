package apiteam.allpoisonim.features.main.bookstoreviewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.data.BookStore
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewholder_bookstore_type_1.view.*
import java.lang.Exception

class BookStoreType1(val view: View) : RecyclerView.ViewHolder(view) {
    private val ivMark = view.bookstore_name_img
    private val tvVol = view.bookstore_vol_txt

    fun bind(store: BookStore.BookStoreImages) {
        tvVol.text = String.format("vol. %s", store.id)
        Picasso.get().load(store.image_url).into(ivMark, object : Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
                ivMark.setImageResource(R.drawable.bookscore_bi)
            }
        })
    }
}
