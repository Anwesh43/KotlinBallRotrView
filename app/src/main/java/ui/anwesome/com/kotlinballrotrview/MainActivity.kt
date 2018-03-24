package ui.anwesome.com.kotlinballrotrview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import ui.anwesome.com.ballrotrview.BallRotrView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = BallRotrView.create(this)
        view.addOnCompleteListener {
            Toast.makeText(this, "complete", Toast.LENGTH_SHORT).show()
        }
        fullScreen()
    }
}
fun MainActivity.fullScreen() {
    supportActionBar?.hide()
    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}
