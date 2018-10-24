package apiteam.allpoisonim.features.sign

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import apiteam.allpoisonim.R
import kotlinx.android.synthetic.main.fragment_signup_complete.*

class SignUpCompleteFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_signup_complete, null)!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        signup_complete_login_btn.setOnClickListener { activity?.finish() }
        signup_complete_detail_text.text = getString(R.string.signup_complete_detail)
    }
}