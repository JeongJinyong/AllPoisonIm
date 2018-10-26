package apiteam.allpoisonim.features.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.data.BookStore
import apiteam.allpoisonim.features.main.bookstoreviewholder.*

class BookStoreDetailAdapter(val context: Context, var store: BookStore.Data) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val OVERVIEW = 3
    private val FIRST_CONTENT = 4
    private val NORMAL_CONTENT_1 = 0
    private val NORMAL_CONTENT_2 = 1
    private val NORMAL_CONTENT_3 = 2
    private val LAST_CONTENT = 5

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)

        when (viewType) {
            OVERVIEW -> {
                val view = inflater.inflate(R.layout.viewholder_bookstore_type_0, parent, false)
                return BookStoreType0(view)
            }
            FIRST_CONTENT -> {
                val view = inflater.inflate(R.layout.viewholder_bookstore_type_1, parent, false)
                return BookStoreType1(view)
            }
            NORMAL_CONTENT_1 -> {
                val view = inflater.inflate(R.layout.viewholder_bookstore_type_2, parent, false)
                return BookStoreType2(view)
            }
            NORMAL_CONTENT_2 -> {
                val view = inflater.inflate(R.layout.viewholder_bookstore_type_3, parent, false)
                return BookStoreType3(view)
            }
            NORMAL_CONTENT_3 -> {
                val view = inflater.inflate(R.layout.viewholder_bookstore_type_4, parent, false)
                return BookStoreType4(view)
            }
            LAST_CONTENT -> {
                val view = inflater.inflate(R.layout.viewholder_bookstore_type_5, parent, false)
                return BookStoreType5(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.viewholder_bookstore_type_5, parent, false)
                return BookStoreType5(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> OVERVIEW
            1 -> FIRST_CONTENT
            store.bookStoreImages.size + 2 -> LAST_CONTENT
            else -> position - 2 % 3
        }
    }

    override fun getItemCount(): Int {
        return store.bookStoreImages.size + 3
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BookStoreType0 -> holder.bind(store)
            is BookStoreType1 -> holder.bind(store)
            is BookStoreType2 -> holder.bind(store.bookStoreImages[position - 2 % 3])
            is BookStoreType3 -> holder.bind(store.bookStoreImages[position - 2 % 3])
            is BookStoreType4 -> holder.bind(store.bookStoreImages[position - 2 % 3])
            is BookStoreType5 -> holder.bind(store)
        }
    }
}