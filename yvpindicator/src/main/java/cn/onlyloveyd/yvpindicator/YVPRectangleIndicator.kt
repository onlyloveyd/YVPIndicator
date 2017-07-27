/**
 * Copyright 2017 yidong
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.onlyloveyd.yviewpagerindicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import cn.onlyloveyd.yvpindicator.R


/**
 * 文 件 名: YVPRectangleIndicator
 * 创 建 人: 易冬
 * 创建日期: 2017/7/27 10:18
 * 描   述：
 */
class YVPRectangleIndicator : LinearLayout {

    private var mRectWidth: Float = 0.0F//矩形底边宽
    private var mRectHeight: Int = 0//矩形高度
    private var mStartPos: Float = 0.0F//矩形起始点
    private var mWidthOffset: Int = 0//矩形移动偏移

    private var mPaint: Paint? = null
    private var mPath: Path? = null

    private var mIndicatorColor = Color.parseColor("#FFFFFF")
    private var mIndicatorHeight = 5

    private var mVp: ViewPager? = null
    private var pageListener = InterPageChangeListener()

    private var mTabCount: Int? = 0
    private val defaultlp = LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f)

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setWillNotDraw(false)

        val dm = resources.displayMetrics

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.YVPRectangleIndicator, defStyle, 0)
        mIndicatorHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mIndicatorHeight + 0.0F, dm).toInt()

        mIndicatorColor = a.getColor(R.styleable.YVPRectangleIndicator_y_indicator_color, Color.parseColor("#FFFFFF"))
        mIndicatorHeight = a.getDimensionPixelSize(R.styleable.YVPRectangleIndicator_y_indicator_height, 5)
        a.recycle()

        initPaint()
    }

    fun setViewPager(vp: ViewPager) {
        mVp = vp
        if (vp.adapter == null) {
            throw IllegalArgumentException()
        }
        notifyDataSetChanged()
        mVp?.addOnPageChangeListener(pageListener)
    }

    fun notifyDataSetChanged() {
        this.removeAllViews()
        mTabCount = mVp?.adapter?.count
        for (i in 0..mTabCount?.let { it - 1 } as Int) {
            addTextTab(i, mVp?.adapter?.getPageTitle(i).toString())
        }
    }

    fun addTextTab(position: Int, title: String) {
        var tab = TextView(context)
        tab.text = title;
        tab.gravity = Gravity.CENTER;
        tab.setSingleLine()

        tab.isFocusable = true
        tab.setOnClickListener { mVp?.currentItem = position }

        this.addView(tab, position, defaultlp)
    }

    /**
     * 初始化画笔
     */
    private fun initPaint() {
        mPaint = Paint()
        mPaint?.color = mIndicatorColor
        mPaint?.isAntiAlias = true
        mPaint?.style = Paint.Style.FILL
    }

    /**
     * 初始化矩形Path
     */
    private fun initRect() {
        mPath = Path()
        mPath?.moveTo(0.0F, 0.0F)
        mPath?.lineTo(mRectWidth, 0.0F)
        mPath?.lineTo(mRectWidth, (-mRectHeight).toFloat())
        mPath?.lineTo(0.0F, (-mRectHeight).toFloat())
        mPath?.close()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mRectWidth = (w / childCount).toFloat()
        mRectHeight = mIndicatorHeight
        mStartPos = 0.0F
        initRect()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        canvas?.translate(mStartPos + mWidthOffset, height.toFloat())
        canvas?.drawPath(mPath, mPaint)
    }

   inner class InterPageChangeListener: ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageSelected(position: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            val tabWidth = screenWidth / childCount
            mWidthOffset = (tabWidth * position + tabWidth * positionOffset).toInt()
            invalidate()
        }
    }

    /**
     * 获取屏幕宽度

     * @return
     */
    private val screenWidth: Int
        get() {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics.widthPixels
        }



}