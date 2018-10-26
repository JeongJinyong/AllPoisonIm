package apiteam.allpoisonim.features.main.bookstoreviewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import apiteam.allpoisonim.api.data.BookStore

class BookStoreType5(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(store: BookStore.Data) {
        //        bookstore_map_txt.text = Html.fromHtml("<u>${bookstore.storeName}</u/>\n${bookstore.storeLocation}")
    }
}