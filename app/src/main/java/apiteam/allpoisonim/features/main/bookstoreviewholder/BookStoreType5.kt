package apiteam.allpoisonim.features.main.bookstoreviewholder

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.data.BookStore
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewholder_bookstore_type_5.view.*
import java.lang.Exception

class BookStoreType5(val view: View) : RecyclerView.ViewHolder(view) {
    private val ivMap = view.bookstore_map
    private val tvAddress = view.bookstore_map_txt

    fun bind(store: BookStore.Data) {
        // 지도 이미지로 변경해야함
        Picasso.get().load(store.storeMainImage).into(ivMap, object : Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
                ivMap.setImageResource(R.drawable.test1)
            }
        })
        tvAddress.text = Html.fromHtml("<u>${store.storeName}</u/><br>${store.storeLocation}")
    }
}