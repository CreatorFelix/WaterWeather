package com.creator.waterweather.widget

import android.content.Context
import android.text.TextUtils

import android.util.SparseArray
import org.xmlpull.v1.XmlPullParser

class WeatherDrawableHelper private constructor(context: Context, propertiesResId: Int) {

    companion object {

        private const val TAG_WEATHER_ITEM = "weather"
        private const val ATTRIBUTE_TYPE_CODE = "typeCode"
        private const val ATTRIBUTE_TYPE_CLASS = "class"
        private const val ATTRIBUTE_NAME = "name"

        @JvmStatic
        private var INSTANCE: WeatherDrawableHelper? = null

        @JvmStatic
        fun getInstance(context: Context, propertiesResId: Int): WeatherDrawableHelper {
            return INSTANCE
                    ?: WeatherDrawableHelper(context, propertiesResId).apply { INSTANCE = this }
        }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

    private val weatherData: SparseArray<Weather> = parse(context, propertiesResId)

    fun getDrawableOfType(type: Int): WeatherDrawable? {
        val drawableName = weatherData.get(type)?.drawableClassName
                ?: throw IllegalArgumentException("Fail to find weather of type: $type")
        if (TextUtils.isEmpty(drawableName)) return null
        return try {
            val drawableClass = Class.forName(drawableName)
            val instance = drawableClass.newInstance()
            instance as? WeatherDrawable
                    ?: throw IllegalArgumentException("$drawableName is not a weather drawable!")
        } catch (exception: ClassNotFoundException) {
            exception.printStackTrace()
            null
        }
    }

    private fun parse(context: Context, propertiesResId: Int): SparseArray<Weather> {
        val result = SparseArray<Weather>()
        val dataParser = context.resources.getXml(propertiesResId)
        var type = dataParser.eventType
        while (type != XmlPullParser.END_DOCUMENT) {
            if (type == XmlPullParser.START_TAG) {
                if (TAG_WEATHER_ITEM == dataParser.name) {
                    val name = dataParser.getAttributeValue(null, ATTRIBUTE_NAME)
                    val weatherType = dataParser.getAttributeIntValue(
                            null, ATTRIBUTE_TYPE_CODE, 0)
                    val className = dataParser.getAttributeValue(
                            null, ATTRIBUTE_TYPE_CLASS)
                    val weather = Weather(name, className)
                    result.put(weatherType, weather)
                }
            }
            type = dataParser.next()
        }
        return result
    }

    private data class Weather(var name: String? = "", var drawableClassName: String? = "")
}
