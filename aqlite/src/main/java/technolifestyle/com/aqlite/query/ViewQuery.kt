package technolifestyle.com.aqlite.query

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.CompoundButton
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
        if (this.view is ImageView) {

            Log.d("Image Url", imgSrc.toString())
            this.imgSrc = imgSrc

            if (!cache) {
                requestOptions = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
            }

            glide.load(imgSrc)
                .apply(requestOptions)
                .into(this.view as ImageView)
        }
        return this
    }

    private fun setView(view: View): ViewQuery {
        this.view = view
        return this
    }


    override fun text(resId: Int): ViewQuery {
        if (this.view is EditText) {
            (this.view as EditText).setText(context.getString(resId))
        } else if (this.view is TextView) {
            (this.view as TextView).text = context.getString(resId)
        }
        return this
    }

    override fun text(str: String): ViewQuery {
        if (this.view is EditText) {
            (this.view as EditText).setText(str)
        } else if (this.view is TextView) {
            (this.view as TextView).text = str
        }
        return this
    }

    override fun text(charSequence: CharSequence): ViewQuery {
        if (this.view is EditText) {
            (this.view as EditText).setText(charSequence)
        } else if (this.view is TextView) {
            (this.view as TextView).text = charSequence
        }
        return this
    }

    override fun visible(): ViewQuery {
        this.view.visibility = View.VISIBLE
        return this
    }

    override fun invisible(): ViewQuery {
        this.view.visibility = View.INVISIBLE
        return this
    }

    override fun gone(): ViewQuery {
        this.view.visibility = View.GONE
        return this
    }

    override fun checked(checked: Boolean): ViewQuery {
        if(this.view is CompoundButton) {
            (view as CompoundButton).isChecked = checked
        }
        return this
    }
}