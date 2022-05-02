package com.mxy.justaconverter.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel(
    val scaffoldContentViewModel: ScaffoldContentViewModel,
    val bottomBarViewModel: BottomBarViewModel
) : ViewModel() {

}