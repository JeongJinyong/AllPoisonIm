package apiteam.allpoisonim.features.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.BookStoreService
import apiteam.allpoisonim.api.TOKEN
import apiteam.allpoisonim.api.data.BookStore
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
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

    private fun initUi() {

    }

    private fun initData() {
        compositeDisposable.add(BookStoreService.getStoreList(TOKEN)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    setRecyclerViewData(it)
                })
    }

    private fun setRecyclerViewData(storeList: List<BookStore.Data>) {
        context?.let {
            val storeAdapter = BookStoreAdapter(it, storeList)
            rv_store.adapter = storeAdapter
        }
    }
}
