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

class ViewQuery(private var context: Context) : ViewQueryInterface {

    private var glide: RequestManager = Glide.with(context)
    private lateinit var view: View
    private var requestOptions: RequestOptions
    private lateinit var imgSrc: Any

    init {
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

    override fun setView(view: View): ViewQuery {
        this.view = view
        return this
    }


    override fun text(resId: Int) {
        if (view is EditText) {
            (view as EditText).setText(context.getString(resId))
        } else if (view is TextView) {
            (view as TextView).text = context.getString(resId)
        }
    }

    override fun text(str: String) {
        if (view is EditText) {
            (view as EditText).setText(str)
        } else if (view is TextView) {
            (view as TextView).text = str
        }
    }

    override fun text(charSequence: CharSequence) {
        if (view is EditText) {
            (view as EditText).setText(charSequence)
        } else if (view is TextView) {
            (view as TextView).text = charSequence
        }
    }
}