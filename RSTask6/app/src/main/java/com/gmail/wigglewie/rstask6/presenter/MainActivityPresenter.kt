package com.gmail.wigglewie.rstask6.presenter

import com.gmail.wigglewie.rstask6.contract.MainActivityContract
import com.gmail.wigglewie.rstask6.data.DataItem
import com.gmail.wigglewie.rstask6.model.MainActivityModel
import java.io.InputStream

class MainActivityPresenter(
    _view: MainActivityContract.View,
    _inputStream: InputStream,
    _hasInternetConnection: Boolean
) :
    MainActivityContract.Presenter {

    private var view: MainActivityContract.View = _view
    private var model: MainActivityContract.Model = MainActivityModel()
    private var inputStream: InputStream = _inputStream
    private var hasInternetConnection: Boolean = _hasInternetConnection
    private var isNightModeEnabled: Boolean = false

    private var dataItems = mutableListOf<DataItem>()

    init {
        if (hasInternetConnection) {
            model.loadXmlData {
                view.initData(it)
                view.initAdapter()
                view.initView(isNightModeEnabled)
            }
        } else {
            dataItems = model.loadJsonData(inputStream)
        }
        view.initData(dataItems)
        view.initAdapter()
        view.initView(isNightModeEnabled)
    }

    override fun onItemClicked(item: DataItem) {
        view.itemClicked(item)
    }

    override fun switchDayNightMode(isNightModeEnabled: Boolean): Boolean {
        if (isNightModeEnabled) {
            this.isNightModeEnabled = false
            view.enableDayMode()
        } else {
            this.isNightModeEnabled = true
            view.enableNightMode()
        }
        return this.isNightModeEnabled
    }
}
