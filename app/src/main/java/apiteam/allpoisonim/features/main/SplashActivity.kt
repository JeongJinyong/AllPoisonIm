package apiteam.allpoisonim.features.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import apiteam.allpoisonim.R
import apiteam.allpoisonim.features.sign.SignInActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initUi()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun initUi() {
        compositeDisposable.add(Observable.timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                })
    }
}
