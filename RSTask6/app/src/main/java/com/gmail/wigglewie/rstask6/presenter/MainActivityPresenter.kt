package com.gmail.wigglewie.rstask6.presenter

import android.util.Log
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
                view.initView(it, isNightModeEnabled)
            }
        } else {
            dataItems = model.loadJsonData(inputStream)
        }
        view.initView(dataItems, isNightModeEnabled)
    }

    override fun onItemWasClicked(item: DataItem) {
        view.itemWasClicked(item)
    }

    override fun switchDayNightMode(isNightModeEnabled: Boolean): Boolean {
        if (isNightModeEnabled) {
            this.isNightModeEnabled = false
            view.enableDayMode()
            Log.d("mode-------------", "switched to DAY MODE")
        } else {
            this.isNightModeEnabled = true
            view.enableNightMode()
            Log.d("mode-------------", "switched to NIGHT MODE")
        }
        return this.isNightModeEnabled
    }
}
