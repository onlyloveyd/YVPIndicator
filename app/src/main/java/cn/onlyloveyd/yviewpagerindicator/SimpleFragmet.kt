/**
 * Copyright 2017 yidong
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.onlyloveyd.yviewpagerindicator

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * 文 件 名: SimpleFragmet
 * 创 建 人: 易冬
 * 创建日期: 2017/7/27 10:40
 * 描   述：
 */
class SimpleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val title = arguments.getString("TITLE")
        val tv = TextView(container!!.context)
        tv.text = title
        return tv

    }

    companion object {
        fun newInstance(title: String): SimpleFragment {
            val args = Bundle()
            args.putString("TITLE", title)
            val fragment = SimpleFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
