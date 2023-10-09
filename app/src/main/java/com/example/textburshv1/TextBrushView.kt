package com.example.textburshv1

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources

class TextBrushView(context: Context?) : View(context) {

    private val TEXT_BURSH = "TEXT  BRUSH"

    private var mPaint = Paint()
    private var mPath: Path? = null
    private var mAllPaths = mutableListOf<Path>()

    init {
        context?.let {
            this.background = AppCompatResources.getDrawable(it, R.drawable.background)
        }
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.WHITE
        mPaint.strokeWidth = 3f
        mPaint.style = Paint.Style.FILL_AND_STROKE
        mPaint.textSize = 100f
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mPath = Path()
                mPath?.moveTo(event.x, event.y)
            }
            MotionEvent.ACTION_MOVE -> {
                mPath?.lineTo(event.x, event.y)
            }
            MotionEvent.ACTION_UP -> {
                mPath?.lineTo(event.x, event.y)
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPath?.let { path ->
            if (!mAllPaths.contains(path)) {
                mAllPaths.add(path)
            }
        }

        if (mAllPaths.size > 0) {
            mAllPaths.forEach {
                canvas.drawTextOnPath(TEXT_BURSH, it, 0f, 0f, mPaint)
            }
        }

        this.invalidate()
    }
}
