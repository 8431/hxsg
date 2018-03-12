package com.hxsg.yiguan;

import com.hxsg.po.Role;
import com.hxsg.po.yaoping;

import java.util.List;

/**
 * Created by dlf on 2016/1/1.
 */
public interface YaoPinService {
    List<yaoping> selectBySx(Integer sx);

}
