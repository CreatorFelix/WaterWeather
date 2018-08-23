package com.creator.waterweather.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.SparseArray
import android.view.View

class WeatherView(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
    : View(context, attrs, defStyleAttr, defStyleRes) {

    companion object {

        const val TYPE_UNSPECIFIED = -1

        const val UPDATE_DELAY_TIME = 10L

        const val SWITCH_ANIMATION_DURATION = 300L
    }

    private val drawableHelper: WeatherDrawableHelper
    var weather: Int = TYPE_UNSPECIFIED
        set(type) {
            if (field != type) {
                if (switchAnimator?.isRunning == true) switchAnimator?.end()
                nextDrawable = drawableCache.get(type)
                        ?: drawableHelper.getDrawableOfType(type)
                        .apply {
                            if (this != null) {
                                setBounds(left, top, right, bottom)
                                drawableCache.put(type, this)
                            }
                        }
                executeWeatherChangeAnimation()
                field = type
            }
        }

    private var currentDrawable: WeatherDrawable? = null
    private var nextDrawable: WeatherDrawable? = null
    private var defaultDrawable: Drawable? = null
    private val drawableCache: SparseArray<WeatherDrawable> = SparseArray()
    private val propertiesResId: Int
    private var switchAnimator: ValueAnimator? = null
    private val updateAction = object : Runnable {
        override fun run() {
            createNewFrame()
            postDelayed(this, UPDATE_DELAY_TIME)
        }
    }
    private var visible = false
    private var started = false
        set(value) {
            if (field != value) {
                field = value
                updateRunning()
            }
        }
    private var running = false

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : this(context, attrs, defStyleAttr, 0)

    init {
        val typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.WeatherView, defStyleAttr, defStyleRes)
        propertiesResId = typedArray.getResourceId(
                R.styleable.WeatherView_weatherProperties, R.xml.weather_properties)
        drawableHelper = WeatherDrawableHelper.getInstance(context, propertiesResId)
        defaultDrawable = typedArray.getDrawable(R.styleable.WeatherView_defaultDrawable)
        defaultDrawable ?: ColorDrawable(Color.WHITE).apply { defaultDrawable = this }
        typedArray.recycle()
    }

    private fun createNewFrame() {
        currentDrawable?.onPrepareNewFrame()
        nextDrawable?.onPrepareNewFrame()
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        nextDrawable?.setBounds(left, top, right, bottom)
        currentDrawable?.setBounds(left, top, right, bottom)
        defaultDrawable?.setBounds(left, top, right, bottom)
        started = true
    }

    override fun onDraw(canvas: Canvas) {
        if (currentDrawable == null) defaultDrawable?.draw(canvas)
        nextDrawable?.draw(canvas)
        currentDrawable?.draw(canvas)
    }

    private fun executeWeatherChangeAnimation() {
        nextDrawable ?: return
        switchAnimator ?: ValueAnimator.ofInt(0, 255)
                .apply { switchAnimator = this }
        switchAnimator?.duration = SWITCH_ANIMATION_DURATION
        switchAnimator?.addUpdateListener {
            val alphaValue = it.animatedValue as Int
            nextDrawable?.alpha = alphaValue
            currentDrawable?.alpha = 255 - alphaValue
            invalidate()
        }
        switchAnimator?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                currentDrawable?.alpha = 255
                nextDrawable?.alpha = 255
                currentDrawable = nextDrawable
                nextDrawable = null
                switchAnimator?.removeAllUpdateListeners()
                switchAnimator?.removeAllListeners()
            }

            override fun onAnimationCancel(animation: Animator?) {
                currentDrawable?.alpha = 255
                nextDrawable?.alpha = 255
                currentDrawable = nextDrawable
                nextDrawable = null
                switchAnimator?.removeAllUpdateListeners()
                switchAnimator?.removeAllListeners()
            }
        })
        switchAnimator?.start()
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        visible = visibility == VISIBLE
        updateRunning()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        visible = false
        updateRunning()
        WeatherDrawableHelper.destroyInstance()
        drawableCache.clear()
    }

    private fun updateRunning() {
        val running = started && visible
        if (this.running != running) {
            if (running) {
                post(updateAction)
            } else {
                removeCallbacks(updateAction)
            }
            this.running = running
        }
    }
}