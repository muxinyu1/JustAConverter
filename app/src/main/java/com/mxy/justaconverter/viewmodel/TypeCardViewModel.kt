package com.mxy.justaconverter.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.mxy.justaconverter.R

class TypeCardViewModel(val typeName: String, val description: String):ViewModel() {

    companion object {
        fun getDefault(): TypeCardViewModel {
            return TypeCardViewModel(
                Resources.getSystem().getString(R.string.type_view_model_default_name),
                Resources.getSystem().getString(R.string.type_view_model_default_description)
            )
        }
    }

}