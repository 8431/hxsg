package com.hxsg.CommonUtil.UtilDao.serviceimpl;

import com.hxsg.CommonUtil.UtilDao.WuPinDao;
import org.springframework.stereotype.Service;

/**
 * Created by dlf on 2016/3/20.
 */
@Service("WuPinDao")
public class WuPinDaoImpl implements WuPinDao {
    @Override
    public boolean shengxingshi() {
        return false;
    }

    @Override
    public boolean baoshizenkuang() {



        return false;
    }

    @Override
    public boolean baoshijingkuang() {
        return false;
    }

    @Override
    public boolean baoshiyinkuang() {
        return false;
    }
}
