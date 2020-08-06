package com.gmail.wigglewie.rstask6.contract

import com.gmail.wigglewie.rstask6.data.DataItem
import java.io.InputStream

interface MainActivityContract {
    interface View {
        fun initView(dataItems: MutableList<DataItem>, mode: Boolean)
        fun itemWasClicked(item: DataItem)
        fun enableNightMode()
        fun enableDayMode()
    }

    interface Presenter {
        fun onItemWasClicked(item: DataItem)
        fun switchDayNightMode(isNightModeEnabled: Boolean): Boolean
    }

    interface Model {
        fun loadJsonData(inputStream: InputStream): MutableList<DataItem>
        fun loadXmlData(callback: (items: MutableList<DataItem>) -> Unit)
    }
}
