package apiteam.allpoisonim.features.books

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import apiteam.allpoisonim.CommonUtil
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.BookService
import apiteam.allpoisonim.api.HttpRequest
import apiteam.allpoisonim.api.TOKEN
import apiteam.allpoisonim.api.data.Book
import apiteam.allpoisonim.api.data.BookDetail
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.viewholder_book.view.*
import java.lang.Exception

class BooksAdapter(val context: Context, var bookList: List<Book.Data>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.viewholder_book, parent, false)

        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BookViewHolder) {
            holder.bind(bookList[position])
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, BooksDetailActivity::class.java)
            intent.putExtra("id", bookList[position].id.toString())
            context.startActivity(intent)
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
        private val tvScrap = v.tv_recommend
        private val tvReply = v.tv_reply
        private val ivRecommend = v.iv_recommend
        private val tvRecommendDesc = v.tv_recommend_desc

        fun bind(book: Book.Data) {
            // 프로필 사진 로딩
            Picasso.get().load(book.user.email).into(ivProfile, object : Callback {
                override fun onSuccess() {
                }

                override fun onError(e: Exception?) {
                    ivProfile.setImageResource(R.drawable.iv_profile_default)
                }
            })

            // 사용자 이름 설정
            tvName.text = book.user.nickname
            // 만든 시간 ~초전 설정
            tvCreatedAt.text = CommonUtil.beforeTime(book.createdAt)
            // 탐라 내용 설정
            tvDesc.text = book.contents
            // 책 썸네일, 이름, 책정보 설정
            Picasso.get().load(book.book.bookImgUri).into(ivThumbnail, object : Callback {
                override fun onSuccess() {
                }

                override fun onError(e: Exception?) {
                    ivThumbnail.setImageResource(R.drawable.iv_book_example)
                }
            })

            tvBookName.text = book.book.bookName
            tvBookInfo.text = String.format("%s 저 | %s 이음", book.book.bookAuthor, book.book.bookPublisher)
            tvPublishedAt.text = book.book.publishAt

            tvScrap.text = book.book.likeCount.toString()
            checkLikeButton(book.book.isLike)
            tvReply.text = book.book.replyCount.toString()

            btnScrap.setOnClickListener {
                HttpRequest.create().bookLike(TOKEN, mapOf("bookId" to book.id.toString()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { booklike ->
                            checkLikeButton(booklike.body()?.data?.like)
                            booklike.body()?.data?.likeCount.let { likeCount ->
                                tvScrap.text = likeCount.toString()
                            }
                        }
            }
        }

        private fun checkLikeButton(check: Boolean?) {
            check?.let {
                if (it) {
                    tvScrap.setTextColor(itemView.context.getColor(R.color.blue))
                    ivRecommend.setImageResource(R.drawable.ic_scrap_on)
                    tvRecommendDesc.setTextColor(itemView.context.getColor(R.color.blue))
                } else {
                    tvScrap.setTextColor(itemView.context.getColor(R.color.grey_909090))
                    ivRecommend.setImageResource(R.drawable.ic_scrap)
                    tvRecommendDesc.setTextColor(itemView.context.getColor(R.color.grey_909090))
                }
            }
        }
    }
}