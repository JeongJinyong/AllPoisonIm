package apiteam.allpoisonim.features.sign

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import apiteam.allpoisonim.R
import apiteam.allpoisonim.api.HttpRequest
import kotlinx.android.synthetic.main.fragment_signup.*


class SignUpFragment : Fragment(), TextWatcher {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_signup, null)!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        signup_edit_nick.addTextChangedListener(this)
        signup_edit_email.addTextChangedListener(this)
        signup_edit_pw.addTextChangedListener(this)
        signup_edit_pwok.addTextChangedListener(this)
        signup_done.setOnClickListener { _ ->
            val nick = signup_edit_nick.text.toString()
            val email = signup_edit_email.text.toString()
            val pw = signup_edit_pw.text.toString()
            val map = mapOf(
                    "email" to email,
                    "password" to pw,
                    "nickname" to nick
            )
            HttpRequest.create().signIn(map).subscribe({
                if (activity is SignUpActivity) (activity as SignUpActivity).signUpComplete()
            }, {
                Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()
                it.printStackTrace()
            }).dispose()
        }
    }

    private fun isSignUpEvaluate(nick: String, email: String, pw: String, pwok: String): Boolean {
        return when {
            nick.isEmpty() -> false
            email.isEmpty() -> false
            pw.isEmpty() -> false
            pwok.isEmpty() -> false
            pw != pwok -> {
                signup_textinput_pwok.isErrorEnabled = true
                signup_textinput_pwok.error = getString(R.string.error_signup_pwok)
                false
            }
            else -> {
                signup_textinput_pwok.isErrorEnabled = false
                true
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        val nick = signup_edit_nick.text.toString()
        val email = signup_edit_email.text.toString()
        val pw = signup_edit_pw.text.toString()
        val pwok = signup_edit_pwok.text.toString()
        signup_done.isEnabled = isSignUpEvaluate(nick, email, pw, pwok)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

}