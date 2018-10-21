package apiteam.allpoisonim.sign

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import apiteam.allpoisonim.R
import kotlinx.android.synthetic.main.fragment_signup.*


class SignUpFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_signup, null)!!

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        signup_done.setOnClickListener {
            if(activity is SignUpActivity) (activity as SignUpActivity).signUpComplete()
        }
    }
}