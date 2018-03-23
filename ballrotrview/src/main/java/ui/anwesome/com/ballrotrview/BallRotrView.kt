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
    data class State(var prevScale : Float = 0f, var dir : Int = 0, var j : Int = 0) {
        val scales : Array<Float> = arrayOf(0f, 0f, 0f, 0f, 0f)
        fun update(stopcb : (Float) -> Unit) {
            scales[j] += dir * 0.1f
            if (Math.abs(scales[j] - prevScale) > 1) {
                scales[j] = prevScale + dir
                j += dir
                if (j == scales.size || j == -1) {
                    j -= dir
                    scales[j] = prevScale + dir
                    dir = 0
                    stopcb(prevScale)
                }
            }
        }
        fun startUpdating(startcb : () -> Unit) {
            if (dir == 0) {
                dir = 1 - 2 * prevScale.toInt()
                startcb()
            }
        }
    }
}