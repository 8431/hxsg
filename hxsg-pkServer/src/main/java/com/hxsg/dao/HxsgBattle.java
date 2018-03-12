package com.hxsg.dao;

import com.hxsg.vo.AttackA;
import com.hxsg.vo.PkRoleVo;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25 0025.
 */
public interface HxsgBattle {
    AttackA skillAttack(PkRoleVo p1, List<PkRoleVo> p2Li, String skillName) throws Exception;
}
