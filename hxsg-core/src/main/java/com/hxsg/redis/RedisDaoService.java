package com.hxsg.redis;

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
    Object set(String key, Object value, Long time) throws Exception;

    /**
     * 通过key获取value
     * @param key
     * @return
     * @throws Exception
     */
    Object get(String key) throws Exception;

    /**
     * 队列
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    Object setLpush(String key, Object value) throws Exception;

    /**
     * 获取队列指定范围数据
     * @param key
     * @param srart
     * @param end
     * @return
     * @throws Exception
     */
    Object lrange(String key, Long srart, Long end) throws Exception;
}
