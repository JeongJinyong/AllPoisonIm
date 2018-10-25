package apiteam.allpoisonim.features.main.bookstoreviewholder

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apiteam.allpoisonim.CommonUtil
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.data.BookStore
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewholder_bookstore_type_0.*

class BookStoreType0(val parent: ViewGroup) : RecyclerView.ViewHolder
(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_bookstore_type_0, parent, false)), LayoutContainer {

    override val containerView: View = parent

    val context = parent.context!!

    @SuppressLint("SetTextI18n")
    private fun onBindViewHolder(bookstore: BookStore.Data) {
        Glide.with(context).load(bookstore.storeMainImage).into(bookstore_main_img)
        bookstore_theme_txt.text = bookstore.storeTheme
        bookstore_title_txt.text = bookstore.storeTitle
        bookstore_map_txt.text = Html.fromHtml("<u>${bookstore.storeName}</u/>\n${bookstore.storeLocation}")
        bookstore_time_txt.text = bookstore.openTime + if (bookstore.weekendOpenTime.isNotEmpty()) "\n주말 : ${bookstore.weekendOpenTime}" else ""
        bookstore_phone_txt.text = bookstore.storePhoneNum
        bookstore_insta_txt.text = bookstore.storeSns
        bookstore_createtime_txt.text = CommonUtil.beforeTime(bookstore.createdAt)
    }

}