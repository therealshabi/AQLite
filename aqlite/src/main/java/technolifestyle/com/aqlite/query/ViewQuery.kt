package technolifestyle.com.aqlite.query

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class ViewQuery(private var view: View) : ViewQueryInterface {

    private var glide: RequestManager
    private var context: Context = view.context
    private var requestOptions: RequestOptions
    private lateinit var imgSrc: Any

    init {
        glide = Glide.with(context)
        requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
    }

    override fun image(imgSrc: Any, cache: Boolean): ViewQuery {
        if (view is ImageView) {

            Log.d("Image Url", imgSrc.toString())
            this.imgSrc = imgSrc

            if (!cache) {
                requestOptions = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
            }

            glide.load(imgSrc)
                .apply(requestOptions)
                .into(view as ImageView)
        }
        return this
    }

    private fun setView(view: View): ViewQuery {
        this.view = view
        return this
    }


    override fun text(resId: Int): ViewQuery {
        if (view is EditText) {
            (view as EditText).setText(context.getString(resId))
        } else if (view is TextView) {
            (view as TextView).text = context.getString(resId)
        }
        return this
    }

    override fun text(str: String): ViewQuery {
        if (view is EditText) {
            (view as EditText).setText(str)
        } else if (view is TextView) {
            (view as TextView).text = str
        }
        return this
    }

    override fun text(charSequence: CharSequence): ViewQuery {
        if (view is EditText) {
            (view as EditText).setText(charSequence)
        } else if (view is TextView) {
            (view as TextView).text = charSequence
        }
        return this
    }

    override fun visible(): ViewQuery {
        view.visibility = View.VISIBLE
        return this
    }

    override fun invisible(): ViewQuery {
        view.visibility = View.INVISIBLE
        return this
    }

    override fun gone(): ViewQuery {
        view.visibility = View.GONE
        return this
    }
}