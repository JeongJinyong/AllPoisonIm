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
import kotlinx.android.synthetic.main.viewholder_bookstore_type_1.*

class BookStoreType1(val parent: ViewGroup) : RecyclerView.ViewHolder
(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_bookstore_type_1, parent, false)), LayoutContainer {

    override val containerView: View = parent

    val context = parent.context!!

    @SuppressLint("SetTextI18n")
    private fun onBindViewHolder(bookstore: BookStore.Data) {
        bookstore_vol_txt.text = "vol. " + if(bookstore.id.toString().length == 1) "0${bookstore.id}" else bookstore.id.toString()
        Glide.with(context).load(bookstore.storeMainImage).into(bookstore_name_img)
    }

}