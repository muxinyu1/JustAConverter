package com.mxy.justaconverter

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mxy.justaconverter.util.Utility
import org.apache.commons.io.FileUtils

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.io.File

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.mxy.justaconverter", appContext.packageName)
    }

    @Test
    fun cloudConvertAndroidTest() {
        val streamAndUrl = Utility.getResultStream(
            InstrumentationRegistry.getInstrumentation().targetContext,
            "F:\\res.jpg",
            "JPG",
            "PNG"
        )
        print(streamAndUrl.first)
        //FileUtils.copyInputStreamToFile(streamAndUrl.second, File("F:\\res.png"))
    }
}