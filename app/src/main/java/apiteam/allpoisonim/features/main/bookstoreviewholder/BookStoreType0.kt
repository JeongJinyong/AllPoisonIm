package apiteam.allpoisonim.features.main.bookstoreviewholder

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import apiteam.allpoisonim.CommonUtil
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.data.BookStore
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewholder_bookstore_type_0.view.*
import java.lang.Exception

class BookStoreType0(val view: View) : RecyclerView.ViewHolder(view) {
    private val ivMain = view.bookstore_main_img
    private val tvTheme = view.bookstore_theme_txt
    private val tvTitle = view.bookstore_title_txt
    private val tvAddress = view.bookstore_map_txt
    private val tvOperatingTime = view.bookstore_time_txt
    private val tvPhone = view.bookstore_phone_txt
    private val tvInsta = view.bookstore_insta_txt
    private val tvCreatedAt = view.bookstore_createtime_txt
    private val tvEditor = view.bookstore_editor_txt
    private val ivEditor = view.bookstore_profile_img

    fun bind(store: BookStore.Data) {
        Picasso.get().load(store.storeMainImage).into(ivMain, object: Callback{
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
                ivMain.setImageResource(R.drawable.test1)
            }
        })
        tvTheme.text = store.storeTheme
        tvTitle.text = store.storeTitle
        tvAddress.text = Html.fromHtml("<u>${store.storeName}</u/><br>\n${store.storeLocation}")
        tvOperatingTime.text = store.openTime + if (store.weekendOpenTime.isNotEmpty()) "\n주말 : ${store.weekendOpenTime}" else ""
        tvPhone.text = store.storePhoneNum
        tvInsta.text = store.storeSns
        tvCreatedAt.text = CommonUtil.beforeTime(store.createdAt)
        Picasso.get().load(store.user.nickname).into(ivEditor, object : Callback {
            override fun onSuccess() {
            }

            override fun onError(e: Exception?) {
                ivEditor.setImageResource(R.drawable.iv_profile_default)
            }
        })
        tvEditor.text = String.format("에디터 %s", store.user.nickname)

    }
}