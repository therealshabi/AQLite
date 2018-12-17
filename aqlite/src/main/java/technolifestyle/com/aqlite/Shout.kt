package technolifestyle.com.aqlite

import android.content.Context
import android.util.Log
import java.lang.StringBuilder

class Shout(context: Context) {

    // represents length of each row of shout message
    private val EACH_ROW_LENGTH = 75

    // represents each row word limit
    private val EACH_ROW_WORD_LIMIT = 30

    // represents each row starting space
    private val EACH_ROW_STARTING_SPACE = 41

    private val TAG = context.javaClass.simpleName
    private val PACKAGE = context.javaClass.`package`.name

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
        val starting_space = getEmptySpaces(EACH_ROW_STARTING_SPACE + PACKAGE.length + TAG.length)

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