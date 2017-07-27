package cn.onlyloveyd.yviewpagerindicator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*


class MainActivity : AppCompatActivity() {
    private var mViewPager: ViewPager? = null
    private var mViewPagerTriangleIndicator: YVPRectangleIndicator? = null
    private val mTitles = Arrays.asList("新闻", "音乐", "游戏", "体育")
    private val mFragments = ArrayList<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewPager = findViewById(R.id.vp_main_content) as ViewPager
        mViewPagerTriangleIndicator = findViewById(R.id.vpti_main_tab) as YVPRectangleIndicator
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
        mViewPagerTriangleIndicator!!.setViewPager(mViewPager!!)

    }
}