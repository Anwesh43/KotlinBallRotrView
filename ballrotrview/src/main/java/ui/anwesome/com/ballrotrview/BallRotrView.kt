package ui.anwesome.com.ballrotrview

/**
 * Created by anweshmishra on 23/03/18.
 */
import android.app.Activity
import android.view.*
import android.content.*
import android.graphics.*
class BallRotrView(ctx : Context) : View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val renderer : Renderer = Renderer(this)
    override fun onDraw(canvas : Canvas) {
        renderer.render(canvas, paint)
    }
    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                renderer.handleTap()
            }
        }
        return true
    }
    data class State(var prevScale : Float = 0f, var dir : Int = 0, var j : Int = 0) {
        var scales : Array<Float> = arrayOf(0f, 0f, 0f, 0f, 0f)
        fun update(stopcb : (Float) -> Unit) {
            scales[j] += dir * 0.1f
            if (Math.abs(scales[j] - prevScale) > 1) {
                scales[j] = prevScale + dir
                j += dir
                if (j == scales.size) {
                    j = 0
                    dir = 0
                    stopcb(scales[j])
                }
            }
        }
        fun startUpdating(startcb : () -> Unit) {
            if (dir == 0) {
                dir = 1
                scales = arrayOf(0f, 0f, 0f, 0f, 0f)
                startcb()
            }
        }
    }
    data class Animator(var view : View, var animated : Boolean = false) {
        fun animate(updatecb : () -> Unit) {
            if (animated) {
                updatecb()
                try {
                    Thread.sleep(50)
                    view.invalidate()
                }
                catch(ex : Exception) {

                }
            }
        }
        fun start() {
            if (!animated) {
                animated = true
                view.postInvalidate()
            }
        }
        fun stop() {
            if (animated) {
                animated = false
            }
        }
    }
    data class BallRotr(var i : Int, val state : State = State()) {
        fun draw(canvas : Canvas, paint : Paint) {
            val w = canvas.width.toFloat()
            val h = canvas.height.toFloat()
            val r = Math.min(w, h)/20
            paint.color = Color.parseColor("#673AB7")
            val x_gap = Math.min(w,h)/3
            canvas.save()
            canvas.translate(w/2, h/2)
            for (i in 0..1) {
                val factor = 1f - 2 * i
                canvas.save()
                canvas.rotate(90f * factor * state.scales[2])
                val mx : Float = x_gap * factor * state.scales[1] * (1 - state.scales[3])
                val my : Float = 0f
                val mr : Float = r * state.scales[0] * (1 - state.scales[4])
                canvas.drawCircle(mx, my, mr, paint)
                canvas.restore()
            }
            canvas.restore()
        }
        fun update(stopcb : (Float) -> Unit) {
            state.update(stopcb)
        }
        fun startUpdating(startcb : () -> Unit) {
            state.startUpdating(startcb)
        }
    }
    data class Renderer(var view : BallRotrView) {
        val ballRotr : BallRotr = BallRotr(0)
        val animator : Animator  = Animator(view)
        fun render(canvas : Canvas, paint : Paint) {
            canvas.drawColor(Color.parseColor("#212121"))
            ballRotr.draw(canvas, paint)
            animator.animate {
                ballRotr.update {
                    animator.stop()
                }
            }
        }
        fun handleTap() {
            ballRotr.startUpdating {
                animator.start()
            }
        }
    }
    companion object {
        fun create(activity : Activity) : BallRotrView {
            val view : BallRotrView = BallRotrView(activity)
            activity.setContentView(view)
            return view
        }
    }
}