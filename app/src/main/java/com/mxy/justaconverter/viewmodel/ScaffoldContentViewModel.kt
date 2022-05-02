package com.mxy.justaconverter.viewmodel

import android.os.Debug
import android.util.Log
import androidx.lifecycle.ViewModel

class ScaffoldContentViewModel(
    var scaffoldContentState: ScaffoldContentState,
    var chooseFileType: ChooseFileType
) : ViewModel() {
    enum class ScaffoldContentState {
        TypeCards,
        ChooseFile,
        History,
        Settings
    }

    enum class ChooseFileType {
        Archive,
        Audio,
        Document,
        Ebook,
        Font,
        Image,
        Presentation,
        Sheet,
        Vector,
        Video
    }

    val onTypeCardClick: (String) -> Unit = {
        Log.d("test", "Now chooseFile type is $it")
        when (it) {
            "Archive" -> {
                chooseFileType = ChooseFileType.Archive
                scaffoldContentState = ScaffoldContentState.ChooseFile
            }
            "Audio" -> {
                chooseFileType = ChooseFileType.Audio
                scaffoldContentState = ScaffoldContentState.ChooseFile
            }
            "Document" -> {
                chooseFileType = ChooseFileType.Document
                scaffoldContentState = ScaffoldContentState.ChooseFile
            }
            "E-Book" -> {
                chooseFileType = ChooseFileType.Ebook
                scaffoldContentState = ScaffoldContentState.ChooseFile
            }
            "Font" -> {
                chooseFileType = ChooseFileType.Font
                scaffoldContentState = ScaffoldContentState.ChooseFile
            }
            "Image" -> {
                chooseFileType = ChooseFileType.Image
                scaffoldContentState = ScaffoldContentState.ChooseFile
            }
            "Presentation" -> {
                chooseFileType = ChooseFileType.Presentation
                scaffoldContentState = ScaffoldContentState.ChooseFile
            }
            "Sheet" -> {
                chooseFileType = ChooseFileType.Sheet
                scaffoldContentState = ScaffoldContentState.ChooseFile
            }
            "Vector" -> {
                chooseFileType = ChooseFileType.Vector
                scaffoldContentState = ScaffoldContentState.ChooseFile
            }
            "Video" -> {
                chooseFileType = ChooseFileType.Video
                scaffoldContentState = ScaffoldContentState.ChooseFile
            }
            else -> {
                chooseFileType = ChooseFileType.Archive
                scaffoldContentState = ScaffoldContentState.TypeCards
            }
        }
    } //TODO:设计回调方法

    companion object {
        fun getDefault(): ScaffoldContentViewModel {
            return ScaffoldContentViewModel(
                ScaffoldContentState.TypeCards,
                ChooseFileType.Archive,
            )
        }
    }
}