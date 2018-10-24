package apiteam.allpoisonim.features.books

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apiteam.allpoisonim.R
import kotlinx.android.synthetic.main.viewholder_book.view.*

class BooksAdapter(val context: Context, var bookList: List<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_book, parent, false)
        view.setOnClickListener {
            val intent = Intent(context, BooksDetailActivity::class.java)
            context.startActivity(intent)
        }
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookViewHolder) {
            holder.bind(bookList[position])
        }
    }

    // ViewHolder Class
    class BookViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val ivProfile = v.iv_profile
        private val tvName = v.tv_title
        private val tvCreatedAt = v.tv_created_at
        private val tvDesc = v.tv_desc
        private val ivThumbnail = v.iv_thumbnail
        private val tvBookName = v.tv_book_name
        private val tvBookInfo = v.tv_book_info
        private val tvPublishedAt = v.tv_book_info2
        private val btnScrap = v.btn_scrap
        private val btnReply = v.btn_reply

        fun bind(number: Int) {
            // 프로필 사진 로딩
            // 사용자 이름 설정
            // 만든 시간 ~초전 설정
            // 탐라 내용 설정
            // 책 썸네일, 이름, 책정보 설정
            // 추천 버튼 설정
            // 댓글 버튼 설정
        }
    }
}