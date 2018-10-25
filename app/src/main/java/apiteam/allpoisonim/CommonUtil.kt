package apiteam.allpoisonim

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


class CommonUtil {

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
}