package com.hardeepsingh.yelpify.customlayouts

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.hardeepsingh.yelpify.R
import com.hardeepsingh.yelpify.model.Open
import kotlinx.android.synthetic.main.hours_view_layout.view.*
import java.text.SimpleDateFormat
import java.util.*


enum class Days(val day: Int) {
    MONDAY(0),
    TUESDAY(1),
    WEDNESDAY(2),
    THURSDAY(3),
    FRIDAY(4),
    SATUARDAY(5),
    SUNDAY(6)
}

private const val HYPEN = " - "
private const val MILITARY_TIME_FORMAT = "hhmm"
private const val DISPLAY_TIME_FORMAT = "hh:mm a"

/**
 * Custom Layout to create Hour of Operation Layout
 */
class HoursView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        orientation = VERTICAL
    }

    fun setHours(hours: Map<Int, Open>) {
        removeAllViews()
        hours.forEach { addView(generateDay(it.key, it.value)) }
    }

    @SuppressLint("SetTextI18n")
    private fun generateDay(dayValue: Int, hourOpen: Open) =
        (LayoutInflater
            .from(context)
            .inflate(R.layout.hours_view_layout, this, false) as ConstraintLayout)
            .apply {
                day.text = dayName(dayValue)?.name
                time.text = "${generateDisplayTime(hourOpen.start)}  $HYPEN  ${generateDisplayTime(hourOpen.end)}"
            }
}

private fun generateDisplayTime(miltaryTime: String): String {
    val date = SimpleDateFormat(MILITARY_TIME_FORMAT, Locale.US).parse(miltaryTime)
    val sdf = SimpleDateFormat(DISPLAY_TIME_FORMAT, Locale.US)
    return sdf.format(date)
}

private fun dayName(day: Int) = Days.values().find { it.day == day }
