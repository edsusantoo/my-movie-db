package com.edsusantoo.moviedb.utils

import android.content.Context
import java.io.IOException

class RootUtils {
    companion object{
        fun readJsonAsset(context: Context, fileName: String): String {
            return context.assets.open(fileName).bufferedReader().use { it.readText() }
        }
    }
}