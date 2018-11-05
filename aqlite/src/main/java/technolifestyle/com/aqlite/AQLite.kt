package technolifestyle.com.aqlite

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import technolifestyle.com.aqlite.query.ViewQuery
import java.lang.StringBuilder

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

    private val EACH_ROW_LENGTH = 75
    private val EACH_ROW_WORD_LIMIT = 30;

    private val TAG = context.javaClass.simpleName
    private val PACKAGE = context.javaClass.`package`.name

    private fun stars(delimiter: Char, length: Int): String {
        val sb = StringBuilder()
        for (i in 1..length) {
            sb.append(delimiter)
        }
        sb.append("\n")
        return sb.toString()
    }

    private fun getEmptySpaces(length: Int): String {
        val sb = StringBuilder()
        for (i in 1..length) {
            sb.append(" ");
        }
        return sb.toString()
    }

    private fun addNewLineToShout(text: String, starting_space: String, delimiter: Char): String {
        val line = StringBuilder()
        line.append(starting_space)
        line.append(delimiter);
        line.append(delimiter);
        val extra_length = EACH_ROW_LENGTH - 4 - text.length
        var left_length = extra_length / 2;
        val right_length = extra_length / 2
        if (extra_length % 2 != 0) {
            left_length++;
        }
        line.append(getEmptySpaces(left_length));
        line.append(text);
        line.append(getEmptySpaces(right_length));
        line.append(delimiter);
        line.append(delimiter);
        line.append("\n");
        return line.toString();
    }

    private fun generateShoutMessage(message: String, delimiter: Char): String {
        val words = message.split(" ")
        val number_of_words = words.size
        val words_length = Array<Int>(words.size) {i -> words[i].length}

        val stars = stars(delimiter, EACH_ROW_LENGTH)
        val starting_space = getEmptySpaces(39 + PACKAGE.length + TAG.length)

        val text = StringBuilder()
        text.append(stars)
        var newLine = StringBuilder()
        var currLength = 0
        var wordLength: Int
        for(index in 1..number_of_words){
            wordLength = (words_length[index-1] + 1)
            if(currLength + wordLength > EACH_ROW_WORD_LIMIT) {
                text.append(addNewLineToShout(newLine.toString(), starting_space, delimiter))
                newLine = StringBuilder()
                currLength = 0
            } else {
                newLine.append(words[index-1] + " ")
                currLength += wordLength
            }
        }
        if (!newLine.isEmpty()) text.append(addNewLineToShout(newLine.toString(), starting_space, delimiter))
        text.append(starting_space + stars)
        return text.toString()
    }

    fun shout(message: String, type: Type = Type.DEBUG, delimiter: Char = '*') {
        val text = generateShoutMessage(message, delimiter)

        when (type) {
            Type.VERBOSE -> Log.v(TAG, "\n" + text + "\n")
            Type.DEBUG -> Log.d(TAG, text)
            Type.INFO -> Log.i(TAG, text)
            Type.WARN -> Log.w(TAG, text)
            Type.ERROR -> Log.e(TAG, text)
            Type.WTF -> Log.wtf(TAG, text)
        }
    }

}

enum class Type {
    VERBOSE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    WTF,
}