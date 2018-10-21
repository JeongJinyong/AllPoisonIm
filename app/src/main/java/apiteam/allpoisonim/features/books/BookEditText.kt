package apiteam.allpoisonim.features.books

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import apiteam.allpoisonim.R
import kotlinx.android.synthetic.main.view_edit_text_book.view.*

class BookEditText : LinearLayout {
    constructor(context: Context?) : super(context) {
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initView()
        getAttrs(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
        getAttrs(attrs, defStyleAttr)
    }

    private fun initView() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.view_edit_text_book, this, false)
        addView(view)
    }

    @SuppressLint("Recycle")
    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BookEditText)
        setTypeArray(typedArray)
    }

    @SuppressLint("Recycle")
    private fun getAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BookEditText, defStyleAttr, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val hint = typedArray.getString(R.styleable.BookEditText_hint)
        et_value.hint = hint

        val isSearch = typedArray.getBoolean(R.styleable.BookEditText_isSearch, false)
        if (isSearch) {
            ll_btn.visibility = View.VISIBLE
        } else {
            ll_btn.visibility = View.GONE
        }
    }

    private fun getText(): String {
        return et_value.text.toString()
    }

    private fun setOnCLickSearch(listener: View.OnClickListener) {
        btn_search_book.setOnClickListener(listener)
    }
}