package apiteam.allpoisonim

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.text.SimpleDateFormat
import java.util.*


open class CommonUtil {

    companion object {
        @SuppressLint("SimpleDateFormat")
        fun beforeTime(date: String): String {
            val data = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date)

            val c = Calendar.getInstance()
            val now = c.timeInMillis
            val dateM = data.time
            var gap = now - dateM

            gap = (gap / 1000)
            val hour = gap / 3600
            gap %= 3600
            val min = gap / 60
            val sec = gap % 60

            val ret = if (hour > 24) {
                SimpleDateFormat("HH:mm").format(data)
            } else if (hour > 0) {
                "$hour 시간 전"
            } else if (min > 0) {
                "$min 분 전"
            } else if (sec > 0) {
                "$sec 초 전"
            } else {
                SimpleDateFormat("HH:mm").format(data)
            }
            return ret
        }
    }

    private val TOKEN = "token"
    private val USER = "user"
    private lateinit var preferences: SharedPreferences

    fun initPreferences(context: Context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    var token
        get() = preferences.getString(TOKEN,"")
        set(value) {
            preferences.editMe {
                it.putString(TOKEN, value)
            }
        }

    var user
        get() = preferences.getString(USER,"")
        set(value) {
            preferences.editMe {
                it.putString(USER, value)
            }
        }
}