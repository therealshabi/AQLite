package technolifestyle.com.aqlite.query

import android.view.View
import androidx.annotation.StringRes

interface ViewQueryInterface {
    fun image(imgSrc: Any, cache: Boolean = true): ViewQuery

    fun setView(view: View): ViewQuery

    fun background(): ViewQuery

    fun text(@StringRes resId: Int): Unit

    fun text(str: String): Unit

    fun text(charSequence: CharSequence): Unit

}