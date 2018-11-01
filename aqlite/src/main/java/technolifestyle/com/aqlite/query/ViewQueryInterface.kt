package technolifestyle.com.aqlite.query

import android.view.View
import androidx.annotation.StringRes

interface ViewQueryInterface {
    fun image(imgSrc: Any, cache: Boolean = true): ViewQuery

    fun setView(view: View): ViewQuery

    fun text(@StringRes resId: Int)

    fun text(str: String)

    fun text(charSequence: CharSequence)
}