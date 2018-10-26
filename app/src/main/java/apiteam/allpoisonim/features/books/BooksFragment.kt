package apiteam.allpoisonim.features.books


import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.BookService
import apiteam.allpoisonim.api.BookStoreService
import apiteam.allpoisonim.api.TOKEN
import apiteam.allpoisonim.api.data.Book
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.fragment_books.*

class BooksFragment : Fragment() {
    private val compositeDisposable = CompositeDisposable()
    private lateinit var toggleList: List<ToggleButton>
    private val typeListener = View.OnClickListener { view ->
        for (toggleButton in toggleList) {
            clearToggle(toggleButton)
        }
        if (view is ToggleButton) {
            setToggle(view)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }

    private fun initData() {
        compositeDisposable.add(BookService.getAllBooks(TOKEN)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { setRecyclerViewData(it) })
    }

    private fun initUi() {
        btn_add_book.setOnClickListener {
            val option =
                    ActivityOptions.makeCustomAnimation(context,
                            R.anim.slide_up_activity, R.anim.slide_no_move)
            val intent = Intent(context, AddBookActivity::class.java)
            startActivity(intent, option.toBundle())
        }

        // 토글버튼들 그룹화
        toggleList = listOf(btn_type_all, btn_type_angry, btn_type_boring,
                btn_type_break, btn_type_depressed, btn_type_exhausting,
                btn_type_insomnia, btn_type_knowledge, btn_type_leaving_company,
                btn_type_like_photo, btn_type_lonely, btn_type_loving,
                btn_type_miss, btn_type_need_laugh, btn_type_no_text,
                btn_type_not_emotional, btn_type_want_trip, btn_type_whitout_thinking)

        btn_type_all.isChecked = true
        for (toggleButton in toggleList) {
            toggleButton.setOnClickListener(typeListener)
        }
    }

    private fun setRecyclerViewData(bookList: List<Book.Data>) {
        context?.let {
            val booksAdapter = BooksAdapter(it, bookList)
            rv_book.adapter = booksAdapter
        }
    }

    private fun clearToggle(button: ToggleButton) {
        context?.let {
            button.isChecked = false
            button.setTextColor(resources.getColor(R.color.black_434343, it.theme))
            button.setBackgroundResource(R.drawable.selector_reco_category)
            button.typeface = ResourcesCompat.getFont(it, R.font.noto_regular)
        }
    }

    private fun setToggle(button: ToggleButton) {
        context?.let {
            button.isChecked = true
            button.setTextColor(resources.getColor(R.color.white, it.theme))
            button.typeface = ResourcesCompat.getFont(it, R.font.noto_bold)
            // 리스트 필터
        }
    }
}
