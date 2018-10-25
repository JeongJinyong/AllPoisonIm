package apiteam.allpoisonim.features.main.bookstoreviewholder

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.data.BookStore
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_bookstore_type_2.*

class BookStoreType2(val parent: ViewGroup) : RecyclerView.ViewHolder
(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_bookstore_type_2, parent, false)), LayoutContainer {
    companion object {
        fun create(parent: ViewGroup, bookstore: BookStore.Data): BookStoreType3 {
            return BookStoreType3(parent, bookstore)
        }
    }
    override val containerView: View = parent

    val context = parent.context!!

    @SuppressLint("SetTextI18n")
    private fun onBindViewHolder(bookstore: BookStore.Data) {
        Glide.with(context).load(bookstore.bookStoreImages[0].image_url).into(bookstore_type2_img)
        bookstore_type2_txt.text = bookstore.bookStoreImages[0].contents
    }

}