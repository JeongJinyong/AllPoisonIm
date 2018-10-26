package apiteam.allpoisonim.features.main.bookstoreviewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import apiteam.allpoisonim.api.data.BookStore

class BookStoreType3(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(content: BookStore.BookStoreImages) {
//        Glide.with(context).load(bookstore.bookStoreImages[1].image_url).into(bookstore_type3_img)
//        bookstore_type3_txt.text = bookstore.bookStoreImages[1].contents
    }
}