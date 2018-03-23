package ui.anwesome.com.ballrotrview

/**
 * Created by anweshmishra on 23/03/18.
 */
import android.view.*
import android.content.*
import android.graphics.*
class BallRotrView(ctx : Context) : View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas : Canvas) {

    }
    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                
            }
        }
        return true
    }
}