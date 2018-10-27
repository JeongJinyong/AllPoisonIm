package apiteam.allpoisonim.features.books

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ToggleButton
import apiteam.allpoisonim.R
import kotlinx.android.synthetic.main.activity_add_book.*


class AddBookActivity : AppCompatActivity() {
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PERMISSION_CAMERA = 2
    private val REQUEST_IMAGE_PHOTO = 3

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                val imageBitmap = data.extras.get("data") as Bitmap
                iv_book.setImageBitmap(imageBitmap)
                btn_add_photo.visibility = View.GONE
            }

            if (requestCode == REQUEST_IMAGE_PHOTO) {
                val uri = data.data
                val imageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                iv_book.setImageBitmap(imageBitmap)
                btn_add_photo.visibility = View.GONE
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSION_CAMERA -> dispatchTakePictureIntent()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_no_move, R.anim.slide_down_activity)
    }

    private fun initUi() {
        // 토글버튼들 그룹화
        toggleList = listOf(btn_type_all, btn_type_angry, btn_type_boring,
                btn_type_break, btn_type_depressed, btn_type_exhausting,
                btn_type_insomnia, btn_type_knowledge, btn_type_leaving_company,
                btn_type_like_photo, btn_type_lonely, btn_type_loving,
                btn_type_miss, btn_type_need_laugh, btn_type_no_text,
                btn_type_not_emotional, btn_type_want_trip, btn_type_whitout_thinking)

        for (toggleButton in toggleList) {
            toggleButton.setOnClickListener(typeListener)
        }

        btn_close.setOnClickListener { finish() }

        iv_book.setOnClickListener { showCameraPopup() }
        btn_add_photo.setOnClickListener { showCameraPopup() }
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

    private fun showCameraPopup() {
        val dialogBuilder = AlertDialog.Builder(this)
                .setTitle("책 사진 선택")
                .setItems(arrayOf("카메라", "앨범에서 선택", "닫기")) { dialog, which ->
                    when (which) {
                        0 -> dispatchTakePictureIntent()
                        1 -> startAlbum()
                        2 -> dialog.dismiss()
                    }
                }

        dialogBuilder.create().show()
    }

    private fun dispatchTakePictureIntent() {
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_PERMISSION_CAMERA)
        }

    }

    private fun startAlbum() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "사진 앱 선택"), REQUEST_IMAGE_PHOTO)
    }
}
