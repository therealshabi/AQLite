package technolifestyle.com.aqlite.query

import android.text.Spanned
import androidx.annotation.StringRes

interface ViewQueryInterface {
    fun image(imgSrc: Any, cache: Boolean = true): ViewQuery

    fun text(@StringRes resId: Int): ViewQuery

    fun text(str: String): ViewQuery

    fun text(charSequence: CharSequence): ViewQuery

    fun text(text: Spanned): ViewQuery

    fun visible(): ViewQuery

    fun invisible(): ViewQuery

    fun gone(): ViewQuery

    fun checked(checked: Boolean): ViewQuery
}