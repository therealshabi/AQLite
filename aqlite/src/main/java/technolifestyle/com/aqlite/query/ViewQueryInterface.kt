package technolifestyle.com.aqlite.query

import androidx.annotation.StringRes

interface ViewQueryInterface {
    fun image(imgSrc: Any, cache: Boolean = true): ViewQuery

    fun text(@StringRes resId: Int): ViewQuery

    fun text(str: String): ViewQuery

    fun text(charSequence: CharSequence): ViewQuery

    fun visible(): ViewQuery

    fun invisible(): ViewQuery

    fun gone(): ViewQuery
}