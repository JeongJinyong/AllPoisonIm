package apiteam.allpoisonim.features.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.BookStoreService
import apiteam.allpoisonim.api.TOKEN
import apiteam.allpoisonim.api.data.BookStore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_bookstore.*

class BookStoreDetailActivity : AppCompatActivity(){
    private val compositeDisposable = CompositeDisposable()
    private var storeId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookstore)
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun initData() {
        storeId = intent.getIntExtra("ARG_STORE_ID", -1)

        storeId?.let { id ->
            compositeDisposable.add(BookStoreService.getStoreDetail(TOKEN, id)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { store ->
                        setRecyclerViewData(store)
                    })
        }
    }

    private fun setRecyclerViewData(store: BookStore.Data) {
        val storeAdapter = BookStoreDetailAdapter(this, store)
        rv_store.adapter = storeAdapter
    }

}