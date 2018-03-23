package ui.anwesome.com.kotlinballrotrview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.ballrotrview.BallRotrView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BallRotrView.create(this)
    }
}
