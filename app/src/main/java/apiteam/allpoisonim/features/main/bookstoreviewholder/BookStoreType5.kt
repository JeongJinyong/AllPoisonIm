package apiteam.allpoisonim.features.main.bookstoreviewholder

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.data.BookStore
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_bookstore_type_5.*

class BookStoreType5(val parent: ViewGroup, val bookstore: BookStore.Data) : RecyclerView.ViewHolder
(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_bookstore_type_5, parent, false)), LayoutContainer {

    companion object {
        fun create(parent: ViewGroup, bookstore: BookStore.Data): BookStoreType5 {
            return BookStoreType5(parent, bookstore)
        }
    }

    override val containerView: View = parent

    val context = parent.context!!

    @SuppressLint("SetTextI18n")
    private fun onBindViewHolder() {
        bookstore_map_txt.text = Html.fromHtml("<u>${bookstore.storeName}</u/>\n${bookstore.storeLocation}")
    }

}