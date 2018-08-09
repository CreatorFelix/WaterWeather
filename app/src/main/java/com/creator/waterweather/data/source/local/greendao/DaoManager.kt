package com.creator.waterweather.data.source.local.greendao

import android.content.Context
import com.creator.waterweather.data.City
import com.creator.waterweather.data.source.local.greendao.gen.CityDao
import com.creator.waterweather.data.source.local.greendao.gen.DaoMaster
import com.creator.waterweather.data.source.local.greendao.gen.DaoSession
import com.creator.waterweather.extension.APP_DATABASE_NAME

class DaoManager private constructor(context: Context) {

    private var devOpenHelper: DaoMaster.DevOpenHelper? = null
    private var daoMaster: DaoMaster? = null
    private var daoSession: DaoSession?

    init {
        devOpenHelper = DaoMaster.DevOpenHelper(context, APP_DATABASE_NAME)
        daoMaster = DaoMaster(devOpenHelper!!.encryptedWritableDb)
        daoSession = daoMaster!!.newSession()
    }

    fun closeDatabase() {
        devOpenHelper?.close()
        devOpenHelper?.encryptedWritableDb?.close()
        devOpenHelper = null
        daoSession?.clear()
        daoSession = null
        daoMaster = null
    }

    companion object {
        private var INSTANCE: DaoManager? = null

        @JvmStatic
        fun getInstance(context: Context): DaoManager = DaoManager(context.applicationContext)

        @JvmStatic
        fun destroyInstance() {
            INSTANCE?.closeDatabase()
            INSTANCE = null
        }
    }

    fun querySelectedCities(): List<City> = daoSession?.cityDao?.queryBuilder()
            ?.where(CityDao.Properties.Selected.eq(true))
            ?.orderAsc(CityDao.Properties.Priority)
            ?.list() ?: emptyList()
}