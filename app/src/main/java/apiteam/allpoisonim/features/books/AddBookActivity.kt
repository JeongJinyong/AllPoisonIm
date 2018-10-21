package apiteam.allpoisonim.features.books

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.view.View
import android.widget.ToggleButton
import apiteam.allpoisonim.R
import kotlinx.android.synthetic.main.activity_add_book.*


class AddBookActivity : AppCompatActivity() {
    private lateinit var toggleList: List<ToggleButton>
    private val typeListener = View.OnClickListener { view ->
        for (toggleButton in toggleList) {
            clearToggle(toggleButton)
        }
        if (view is ToggleButton) {
            setToggle(view)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        initUi()
    }

    private fun initUi() {
        toggleList = listOf(btn_type_all, btn_type_angry, btn_type_boring,
                btn_type_break, btn_type_depressed, btn_type_exhausting,
                btn_type_insomnia, btn_type_knowledge, btn_type_leaving_company,
                btn_type_like_photo, btn_type_lonely, btn_type_loving,
                btn_type_miss, btn_type_need_laugh, btn_type_no_text,
                btn_type_not_emotional, btn_type_want_trip, btn_type_whitout_thinking)

        for (toggleButton in toggleList) {
            toggleButton.setOnClickListener(typeListener)
        }
    }

    private fun clearToggle(button: ToggleButton) {
        button.isChecked = false
        button.setTextColor(resources.getColor(R.color.black_434343, theme))
        button.setBackgroundResource(R.drawable.selector_reco_category)
        button.typeface = ResourcesCompat.getFont(this, R.font.noto_regular)
    }

    private fun setToggle(button: ToggleButton) {
        button.isChecked = true
        button.setTextColor(resources.getColor(R.color.white, theme))
        button.typeface = ResourcesCompat.getFont(this, R.font.noto_bold)
        tv_book_reco_type.text = button.text
    }
}
