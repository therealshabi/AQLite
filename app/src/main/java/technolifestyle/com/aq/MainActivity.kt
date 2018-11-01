package technolifestyle.com.aq

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import technolifestyle.com.aqlite.AQLite

class MainActivity : AppCompatActivity() {

    private lateinit var aq: AQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        aq = AQLite(this)
//
//        val arr = listOf("https://source.unsplash.com/random", "https://source.unsplash.com/400x400")
//        val index = Random().nextInt(2)
        aq.id(R.id.image_view)
            .image("https://source.unsplash.com/random")

        aq.id(R.id.text_view)
            .text(R.string.app_name)
    }
}
