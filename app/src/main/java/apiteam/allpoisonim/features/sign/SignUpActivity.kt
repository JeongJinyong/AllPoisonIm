package apiteam.allpoisonim.features.sign

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import apiteam.allpoisonim.R

class SignUpActivity : AppCompatActivity() {

    var nick : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.signup_fragment, SignUpFragment())
                .commit()
    }

    fun signUpComplete(nickName: String) {
        nick = nickName
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.signup_fragment, SignUpCompleteFragment())
                .commit()
    }

}