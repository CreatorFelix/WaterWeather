package com.creator.waterweather.data.source.local.greendao

import android.content.Context
import com.creator.waterweather.data.City
import com.creator.waterweather.data.source.local.greendao.gen.DaoMaster
import com.creator.waterweather.data.source.local.greendao.gen.DaoSession
import com.creator.waterweather.data.source.local.greendao.gen.UserCityDao
import com.creator.waterweather.extension.APP_DATABASE_NAME
import com.creator.waterweather.extension.warning

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

    fun queryUserCities(): List<City> {
        val userCities = daoSession?.userCityDao?.queryBuilder()
                ?.orderAsc(UserCityDao.Properties.Priority)
                ?.list()
        if (userCities == null || userCities.isEmpty()) {
            return emptyList()
        }
        return List(userCities.size) { City(userCities[it]) }
    }

    fun addUserCity(city: City): Boolean {
        val founds = daoSession?.userCityDao?.queryBuilder()
                ?.where(UserCityDao.Properties.Name.eq(city.name))?.list()
        if (founds != null && founds.isNotEmpty()) {
            warning("City[${city.name}] has been added before!")
            return false
        }
        val count = daoSession?.userCityDao?.queryBuilder()?.count() ?: 0
        val rowID = daoSession?.userCityDao?.insert(city.toUserCity()
                .apply { priority = (count + 1).toInt() })
        return rowID == null
    }
}