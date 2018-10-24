package apiteam.allpoisonim.features.books

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import apiteam.allpoisonim.R
import kotlinx.android.synthetic.main.activity_books_detail.*


class BooksDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_detail)

        val replyAdapter = ReplyAdapter(this, listOf(0,1,2,3,4,5))
        rv_reply.adapter = replyAdapter
    }
}
