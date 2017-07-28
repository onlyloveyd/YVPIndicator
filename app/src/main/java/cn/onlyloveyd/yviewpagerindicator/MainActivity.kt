package cn.onlyloveyd.yviewpagerindicator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v4.app.FragmentPagerAdapter
import cn.onlyloveyd.yvpindicator.YVPDotIndicator
import cn.onlyloveyd.yvpindicator.YVPRectangleIndicator
import cn.onlyloveyd.yvpindicator.YVPTriangleIndicator
import java.util.*


class MainActivity : AppCompatActivity() {
    private var mViewPager: ViewPager? = null
    private var mYVPRectangleIndicator: YVPRectangleIndicator? = null
    private var mYUVDotIndicator: YVPDotIndicator? = null
    private var mYVPTriangleIndicator: YVPTriangleIndicator? = null

    private val mTitles = Arrays.asList("新闻", "音乐", "游戏", "体育")
    private val mFragments = ArrayList<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewPager = findViewById(R.id.vp_main_content) as ViewPager
        mYVPRectangleIndicator = findViewById(R.id.yvprect_indicator) as YVPRectangleIndicator
        mYUVDotIndicator = findViewById(R.id.yvpdot_indicator) as YVPDotIndicator
        mYVPTriangleIndicator = findViewById(R.id.yvptriangle_indicator) as YVPTriangleIndicator

        //创建Fragment
        for (title in mTitles) {
            val simpleFragmet = SimpleFragment.newInstance(title)
            mFragments.add(simpleFragmet)
        }        //设置适配器
        mViewPager!!.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return mFragments[position]
            }

            override fun getCount(): Int {
                return mFragments.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return mTitles[position]
            }
        }        //添加滑动监听
        mYVPRectangleIndicator!!.setViewPager(mViewPager!!)
        mYUVDotIndicator!!.setViewPager(mViewPager!!)
        mYVPTriangleIndicator!!.setViewPager(mViewPager!!)

    }
}