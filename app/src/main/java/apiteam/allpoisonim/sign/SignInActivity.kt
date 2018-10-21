package apiteam.allpoisonim.sign

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import apiteam.allpoisonim.R
import kotlinx.android.synthetic.main.activity_signin.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        signin_info_text.setOnClickListener {
            startActivity(Intent(this@SignInActivity,SignUpActivity::class.java))
        }
    }
}