package technolifestyle.com.aqlite.query

import android.view.View

interface ViewQueryInterface {
    fun image(imgSrc: Any, cache: Boolean = true): ViewQuery

    fun setView(view: View): ViewQuery
}