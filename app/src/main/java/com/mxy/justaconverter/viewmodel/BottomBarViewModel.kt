package com.mxy.justaconverter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class BottomBarViewModel(var state: BottomBarState): ViewModel() {
    enum class BottomBarState {
        Converter,
        History,
        Settings,
    }

    companion object {
        fun getDefault(): BottomBarViewModel {
            return BottomBarViewModel(BottomBarState.History)
        }
    }

    val onBottomBarItemClick: (BottomBarState) -> Unit = {
        state = it
        Log.d("bottom", when(state) {
            BottomBarState.Converter -> "Converter"
            BottomBarState.History -> "History"
            BottomBarState.Settings -> "Settings"
        })
        //TODO:修改ViewModel的状态
    }

}