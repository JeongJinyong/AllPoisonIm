package apiteam.allpoisonim.features.books

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import apiteam.allpoisonim.CommonUtil
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.HttpRequest
import apiteam.allpoisonim.api.data.BookDetail
import apiteam.allpoisonim.api.data.Membership
import com.bumptech.glide.Glide
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_books_detail.*


class BooksDetailActivity : AppCompatActivity() {
    private lateinit var token: String
    private lateinit var user: Membership.Sign
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_detail)
        val commonUtil = CommonUtil()
        commonUtil.initPreferences(this@BooksDetailActivity)
        token = commonUtil.token
        user = Gson().fromJson(commonUtil.user, Membership.Sign::class.java)


        val id = if (intent.hasExtra("id")) intent.getStringExtra("id") else "1"

        HttpRequest.create().book(token, id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            it.body()?.let { book -> initData(book) }
        }, {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            it.printStackTrace()
        })
        btn_scrap.setOnClickListener {
            HttpRequest.create().bookLike(token, mapOf("bookId" to id)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ booklike ->
                if (booklike.body()?.statusCode == 200) {
                    tv_recommend.text = booklike.body()?.data?.likeCount.toString()
                    checkLikeButton(booklike.body()?.data?.like)
                } else {
                    Toast.makeText(this, booklike.body()?.message, Toast.LENGTH_SHORT).show()
                }
            }, {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                it.printStackTrace()
            })
        }
        btn_back.setOnClickListener { finish() }
    }

    private fun initData(book: BookDetail.Book) {
        tv_title.text = book.user.nickname
        tv_created_at.text = CommonUtil.beforeTime(book.createdAt)
        tv_desc.text = book.bookContents
        Glide.with(this).load(book.bookImgUri).into(iv_thumbnail)
        tv_book_name.text = book.bookName
        tv_book_info.text = book.bookAuthor
        tv_book_info2.text = book.bookBrief
        checkLikeButton(book.like)
        tv_recommend.text = book.likeCount.toString()
        tv_reply.text = book.replyCount.toString()
        tv_category.text = book.category.categoryName
        val replyAdapter = ReplyAdapter(this, book.bookComments)
        rv_reply.adapter = replyAdapter
        btn_register_reply.setOnClickListener {
            val map = mapOf(
                    "bookId" to book.id.toString(),
                    "comment" to et_reply.text.toString()
            )
            HttpRequest.create().bookComment(token, map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ comment ->
                if (comment.body()?.statusCode == 200) {
                    book.bookComments.add(BookDetail.BookComments(0, et_reply.text.toString(), user.data.userId, user.data.nickname, ""))
                    tv_reply.text = book.bookComments.size.toString()
                    rv_reply.adapter.notifyDataSetChanged()
                    et_reply.setText("")
                } else {
                    Toast.makeText(this, comment.body()?.message, Toast.LENGTH_SHORT).show()
                }
            }, {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                it.printStackTrace()
            })
        }
    }

    private fun checkLikeButton(check: Boolean?) {
        check?.let {
            if (it) {
                tv_recommend.setTextColor(getColor(R.color.blue))
                iv_reco.setImageResource(R.drawable.ic_scrap_on)
                tv_recommend_desc.setTextColor(getColor(R.color.blue))
            } else {
                tv_recommend.setTextColor(getColor(R.color.grey_909090))
                iv_reco.setImageResource(R.drawable.ic_scrap)
                tv_recommend_desc.setTextColor(getColor(R.color.grey_909090))
            }
        }
    }

}
