package com.gmail.wigglewie.rstask6.contract

import com.gmail.wigglewie.rstask6.data.DataItem

interface ItemViewActivityContract {
    interface View {
        fun initView(item: DataItem?, mode: Boolean)
        fun initPlayer()
        fun playButtonClicked()
    }

    interface Presenter {
        fun onPlayButtonClicked()
    }
}
