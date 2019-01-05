package technolifestyle.com.aqlite

import android.content.Context
import android.util.Log
import java.lang.StringBuilder

class Shout(context: Context) {

    companion object {
        // represents length of each row of shout message
        private const val EACH_ROW_LENGTH = 150

        // represents each row word limit
        private const val EACH_ROW_WORD_LIMIT = 120
    }

    private val TAG = context.javaClass.simpleName

    /**
     * @param length - length of string
     * @param delimiter - character which is used in the string
     * @return line containing delimiter characters
     */
    private fun stars(delimiter: Char, length: Int): String {
        val sb = StringBuilder()
        for (i in 1..length) {
            sb.append(delimiter)
        }
        sb.append("\n")
        return sb.toString()
    }

    /**
     * @param length
     * @return line containing empty spaces
     */
    private fun getEmptySpaces(length: Int): String {
        val sb = StringBuilder()
        for (i in 1..length) {
            sb.append(" ");
        }
        return sb.toString()
    }

    /**
     * @param text content of the line
     * @param starting_space string representing the starting space of every line
     * @param delimiter character used for separation
     * @return new line of the shout message
     */
    private fun addNewLineToShout(text: String, delimiter: Char): String {
        val line = StringBuilder()
        line.append(delimiter)
        line.append(delimiter)
        val extraLength = EACH_ROW_LENGTH - 4 - text.length
        var leftLength = extraLength / 2
        val rightLength = extraLength / 2
        if (extraLength % 2 != 0) {
            leftLength++
        }
        line.append(getEmptySpaces(leftLength))
        line.append(text)
        line.append(getEmptySpaces(rightLength))
        line.append(delimiter)
        line.append(delimiter)
        line.append("\n")
        return line.toString()
    }

    /**
     * @param message shout message
     * @param delimiter character used for separation
     * @return properly designed shout message
     */
    private fun generateShoutMessage(message: String, delimiter: Char): String {
        val words = message.split(" ")
        val number_of_words = words.size
        val words_length = Array(words.size) {i -> words[i].length}

        val stars = stars(delimiter, EACH_ROW_LENGTH)

        val text = StringBuilder()
        text.append(".\n")
        text.append(stars)
        var newLine = StringBuilder()
        var currLength = 0
        var wordLength: Int
        for(index in 1..number_of_words){
            wordLength = (words_length[index-1] + 1)
            if(currLength + wordLength > EACH_ROW_WORD_LIMIT) {
                text.append(addNewLineToShout(newLine.toString(), delimiter))
                newLine = StringBuilder()
                currLength = 0
            } else {
                newLine.append(words[index-1] + " ")
                currLength += wordLength
            }
        }
        if (!newLine.isEmpty()) text.append(addNewLineToShout(newLine.toString(), delimiter))
        text.append(stars)
        return text.toString()
    }

    /**
     * @param message shout message
     * @param type log type
     * @param delimiter character used for separation
     */
    fun shout(message: String, type: Type, delimiter: Char) {
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