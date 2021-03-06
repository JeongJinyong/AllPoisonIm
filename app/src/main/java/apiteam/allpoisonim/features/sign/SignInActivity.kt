package apiteam.allpoisonim.features.sign

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import apiteam.allpoisonim.CommonUtil

import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.HttpRequest
import apiteam.allpoisonim.api.data.Membership
import apiteam.allpoisonim.api.data.UserModel
import apiteam.allpoisonim.features.main.MainActivity
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_signin.*
import java.util.regex.Pattern

class SignInActivity : AppCompatActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val commonUtil = CommonUtil()
        commonUtil.initPreferences(this)
        if(commonUtil.token.isNotEmpty() and commonUtil.user.isNotEmpty()){
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            finish()
        }
        signin_edit_email.addTextChangedListener(this)
        signin_edit_pw.addTextChangedListener(this)
        signin_info_text.setOnClickListener {
            startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
        }
        signin_btn.setOnClickListener { _ ->
            val email = signin_edit_email.text.toString()
            val pw = signin_edit_pw.text.toString()
            val map = mapOf(
                    "email" to email,
                    "password" to pw
            )
            if (isEmailValid(email)) {
                val progressDialog = ProgressDialog(this)
                progressDialog.setMessage("로딩중입니다.")
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                progressDialog.show()
                HttpRequest.create().signIn(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                    if(it.body()?.statusCode == 200) {
                        UserModel.user = it.body()
                        commonUtil.token = it.headers().get("Authorization")
                        commonUtil.user = Gson().toJson(it.body())
                        progressDialog.dismiss()
                        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, it.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                }, {
                    progressDialog.dismiss()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    it.printStackTrace()
                })
            }else{
                Toast.makeText(this@SignInActivity,getString(R.string.error_signin_id_valid),Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isSignInEvaluate(email: String, pw: String): Boolean {
        return when {
            email.isEmpty() -> false
            pw.isEmpty() -> false
            else -> true
        }
    }

    override fun afterTextChanged(s: Editable?) {
        val email = signin_edit_email.text.toString()
        val pw = signin_edit_pw.text.toString()
        signin_btn.isEnabled = isSignInEvaluate(email,pw)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}


    private fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

}