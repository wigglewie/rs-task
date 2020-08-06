package com.gmail.wigglewie.rstask6.contract

import com.gmail.wigglewie.rstask6.data.DataItem
import java.io.InputStream

interface MainActivityContract {
    interface View {
        fun initData(dataItems: MutableList<DataItem>)
        fun initAdapter()
        fun initView(isNightModeEnabled: Boolean)
        fun itemClicked(item: DataItem)
        fun enableNightMode()
        fun enableDayMode()
    }

    interface Presenter {
        fun onItemClicked(item: DataItem)
        fun switchDayNightMode(isNightModeEnabled: Boolean): Boolean
    }

    interface Model {
        fun loadJsonData(inputStream: InputStream): MutableList<DataItem>
        fun loadXmlData(callback: (items: MutableList<DataItem>) -> Unit)
    }
}
