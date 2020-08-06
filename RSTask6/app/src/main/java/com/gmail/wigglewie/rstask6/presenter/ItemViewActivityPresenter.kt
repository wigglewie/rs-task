package com.gmail.wigglewie.rstask6.presenter

import com.gmail.wigglewie.rstask6.contract.ItemViewActivityContract
import com.gmail.wigglewie.rstask6.data.DataItem

class ItemViewActivityPresenter(
    _view: ItemViewActivityContract.View,
    _item: DataItem,
    _mode: Boolean
) :
    ItemViewActivityContract.Presenter {

    private var view: ItemViewActivityContract.View = _view
    private var item: DataItem = _item
    private var mode: Boolean = _mode

    init {
        view.initView(item, mode)
        view.initPlayer()
    }

    override fun onPlayButtonClicked() {
        view.playButtonClicked()
    }
}
