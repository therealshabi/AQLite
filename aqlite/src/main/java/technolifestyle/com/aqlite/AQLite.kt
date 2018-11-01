package technolifestyle.com.aqlite

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import technolifestyle.com.aqlite.query.ViewQuery

class AQLite(context: Context) {

    private val viewQuery: ViewQuery = ViewQuery(context)
    private val rootView: View = (context as Activity).window
        .decorView.findViewById(android.R.id.content)

    fun id(@IdRes resId: Int): ViewQuery {
        return viewQuery.setView(rootView.findViewById(resId))
    }

    fun find(@IdRes resId: Int): ViewQuery {
        return id(resId)
    }

}