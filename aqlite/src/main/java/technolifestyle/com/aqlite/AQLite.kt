package technolifestyle.com.aqlite

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

    private var rootView: View = view

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

    /**
     * prints the log message
     * @param message log message
     * @param type log type(enum Type)
     * @param delimiter character used for separation (default '*')
     */
    fun shout(message: String, type: Type = Type.DEBUG, delimiter: Char = '*') {
        val shout = Shout(context)
        shout.shout(message, type, delimiter)
    }

}

/**
 * Logging types that can be used
 */
enum class Type {
    VERBOSE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    WTF,
}