package apiteam.allpoisonim.features.main.bookstoreviewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import apiteam.allpoisonim.api.data.BookStore

class BookStoreType2(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(content: BookStore.BookStoreImages) {
//        Glide.with(context).load(bookstore.bookStoreImages[0].image_url).into(bookstore_type2_img)
//        bookstore_type2_txt.text = bookstore.bookStoreImages[0].contents
    }
}