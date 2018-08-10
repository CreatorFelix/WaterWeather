package com.creator.waterweather.data.source.local.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.creator.waterweather.data.source.local.greendao.entity.UserCity;

import com.creator.waterweather.data.source.local.greendao.gen.UserCityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userCityDaoConfig;

    private final UserCityDao userCityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userCityDaoConfig = daoConfigMap.get(UserCityDao.class).clone();
        userCityDaoConfig.initIdentityScope(type);

        userCityDao = new UserCityDao(userCityDaoConfig, this);

        registerDao(UserCity.class, userCityDao);
    }
    
    public void clear() {
        userCityDaoConfig.clearIdentityScope();
    }

    public UserCityDao getUserCityDao() {
        return userCityDao;
    }

}
