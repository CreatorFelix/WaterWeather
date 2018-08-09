package com.creator.waterweather.data.source.local.greendao

import com.creator.waterweather.BuildConfig
import com.creator.waterweather.data.source.local.greendao.gen.DaoMaster
import org.greenrobot.greendao.database.Database

val DaoMaster.DevOpenHelper.encryptedWritableDb: Database
    get() = getEncryptedWritableDb(BuildConfig.APPLICATION_ID)