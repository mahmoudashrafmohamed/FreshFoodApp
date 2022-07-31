package com.mahmoudashraf.freshfoodapp.core.json

import android.content.res.AssetManager
import com.mahmoudashraf.freshfoodapp.presentation.App
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

fun getJSONString(filePath : String): String? {
    var str: String? = ""
    try {
        val assetManager: AssetManager = App.appContext.assets
        val `in`: InputStream = assetManager.open(filePath)
        val isr = InputStreamReader(`in`)
        val inputBuffer = CharArray(100)
        var charRead: Int
        while (isr.read(inputBuffer).also { charRead = it } > 0) {
            val readString = String(inputBuffer, 0, charRead)
            str += readString
        }
    } catch (ioe: IOException) {
        ioe.printStackTrace()
    }
    return str
}