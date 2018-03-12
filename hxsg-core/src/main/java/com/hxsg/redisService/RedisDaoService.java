package com.hxsg.redisService;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public interface RedisDaoService {
    /**
     * 设置key
     * @param key
     * @param value
     * @param time key生存时间
     * @return
     */
    Boolean set(String key, Object value, Long time) throws Exception;
    /**
     * 设置key
     * @param key
     * @param value
     * @return
     */
    Boolean set(String key, Object value) throws Exception;


    /**
     * 通过key获取value
     * @param key
     * @return
     * @throws Exception
     */
    Object get(String key) throws Exception;

    /**
     * lpush
     * @param key
     * @param o
     * @return
     * @throws Exception
     */

    Boolean lpush(String key, Object o) throws Exception;

    /**
     * 获取队列指定范围数据
     * @param key
     * @param srart
     * @param end
     * @return
     * @throws Exception
     */
    Object lrange(String key, Long srart, Long end) throws Exception;

    /**
     * 获取所有的key
     * @param key
     * @return
     * @throws Exception
     */
    Set<String> keys(String key) throws Exception;

    /**
     * 删除key
     * @param c
     */
    void del(Collection c) throws Exception;;
}
