package apiteam.allpoisonim.features.main.bookstoreviewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import apiteam.allpoisonim.api.data.BookStore

class BookStoreType1(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(store: BookStore.Data) {
//        bookstore_vol_txt.text = "vol. " + if(bookstore.id.toString().length == 1) "0${bookstore.id}" else bookstore.id.toString()
//        Glide.with(context).load(bookstore.storeMainImage).into(bookstore_name_img)
    }
}
