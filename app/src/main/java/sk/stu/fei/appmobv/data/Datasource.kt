package sk.stu.fei.appmobv.data

import android.content.Context
import java.io.IOException


abstract class Datasource {
    fun loadJson(context: Context, path: String): String? {
        return try {
            context.assets.open(path)
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            null
        }
    }
}