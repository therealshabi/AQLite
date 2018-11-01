package technolifestyle.com.aqlite

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import technolifestyle.com.aqlite.query.ViewQuery

class AQLite(private var view: View) {

    private val context: Context = view.context

    private lateinit var viewQuery: ViewQuery
    private val rootView: View = (context as Activity).window
        .decorView.findViewById(android.R.id.content)

    fun id(@IdRes resId: Int): ViewQuery {
        return ViewQuery(rootView.findViewById(resId))
    }

    fun find(@IdRes resId: Int): ViewQuery {
        return id(resId)
    }

    fun backgroundColor(@ColorInt color: Int) {
        view.setBackgroundColor(color)
    }

    fun background(drawable: Drawable) {
        view.background = drawable
    }

    fun background(@DrawableRes drawableRes: Int) {
        view.background = ContextCompat.getDrawable(context, drawableRes)
    }

}