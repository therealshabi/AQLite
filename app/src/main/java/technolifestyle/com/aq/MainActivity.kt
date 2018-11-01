package technolifestyle.com.aq

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import technolifestyle.com.aqlite.AQLite

class MainActivity : AppCompatActivity() {

    private lateinit var aq: AQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        aq = AQLite(findViewById(R.id.parent_layout))
//
//        val arr = listOf("https://source.unsplash.com/random", "https://source.unsplash.com/400x400")
//        val index = Random().nextInt(2)
        aq.id(R.id.image_view)
            .image("https://source.unsplash.com/random")
            .visible()

        aq.id(R.id.text_view)
            .text(R.string.app_name)

        aq.backgroundColor(
            ContextCompat
                .getColor(this, R.color.colorAccent))

//        aq.background(R.mipmap.ic_launcher_round)

//        aq.backgroundColor(Color.GREEN)
    }
}
