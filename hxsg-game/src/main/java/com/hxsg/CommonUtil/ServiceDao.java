package com.hxsg.CommonUtil;

import com.hxsg.Dao.RoleFriendsMsgMapper;
import com.hxsg.po.RoleFriendsMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dlf on 2016/1/27.
 */

public interface ServiceDao {

    public int getRoleFriendsMsg(RoleFriendsMsg rg);

}
