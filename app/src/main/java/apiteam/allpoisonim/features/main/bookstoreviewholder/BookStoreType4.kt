package apiteam.allpoisonim.features.main.bookstoreviewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import apiteam.allpoisonim.api.data.BookStore

class BookStoreType4(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(content: BookStore.BookStoreImages) {
//        Glide.with(context).load(bookstore.bookStoreImages[2].image_url).into(bookstore_type4_img)
//        bookstore_type4_txt.text = bookstore.bookStoreImages[2].contents
    }
}