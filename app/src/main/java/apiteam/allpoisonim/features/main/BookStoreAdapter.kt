package apiteam.allpoisonim.features.main

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apiteam.allpoisonim.CommonUtil
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.data.BookStore
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewholder_store.view.*
import java.lang.Exception

class BookStoreAdapter(val context: Context, var storeList: List<BookStore.Data>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_store, parent, false)

        return StoreViewHolder(view)
    }

    override fun getItemCount(): Int {
        return storeList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StoreViewHolder) {
            holder.bind(storeList[position])
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, BookStoreDetailActivity::class.java)
            intent.putExtra("ARG_STORE_ID", storeList[position].id)
            context.startActivity(intent)
        }
    }

    class StoreViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        private val ivMain = v.iv_main
        private val tvTheme = v.tv_theme
        private val tvTitle = v.tv_title
        private val tvStoreName = v.tv_store_name
        private val tvAddress = v.tv_address
        private val ivProfile = v.iv_profile
        private val tvEditor = v.tv_editor_name
        private val tvCreatedAt = v.tv_created_at

        fun bind(store: BookStore.Data) {
            if (store.bookStoreImages.isNotEmpty()) {
                Picasso.get().load(store.storeMainImage).into(ivMain, object : Callback {
                    override fun onSuccess() {
                    }

                    override fun onError(e: Exception?) {
                        ivMain.setImageResource(R.drawable.test1)
                    }
                })
            }
            tvTheme.text = store.storeTheme
            tvTitle.text = store.storeTitle
            tvStoreName.text = store.storeName
            tvAddress.text = store.storeLocation
            Picasso.get().load(store.user.nickname).into(ivProfile, object : Callback {
                override fun onSuccess() {
                }

                override fun onError(e: Exception?) {
                    ivProfile.setImageResource(R.drawable.iv_profile_default)
                }
            })
            tvEditor.text = String.format("에디터 %s", store.user.nickname)
            tvCreatedAt.text = CommonUtil.beforeTime(store.createdAt)
        }
    }
}