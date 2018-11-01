package technolifestyle.com.aqlite

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import technolifestyle.com.aqlite.query.ViewQuery

class AQLite(private var view: View) {

    private val context: Context = view.context

    private val viewQuery: ViewQuery = ViewQuery(context)
    private val rootView: View = (context as Activity).window
        .decorView.findViewById(android.R.id.content)

    fun id(@IdRes resId: Int): ViewQuery {
        return viewQuery.setView(rootView.findViewById(resId))
    }

    fun find(@IdRes resId: Int): ViewQuery {
        return id(resId)
    }

    fun backgroundColor(@ColorInt color: Int) {
        view.setBackgroundColor(color)
    }



}