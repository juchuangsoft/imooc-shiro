package com.imooc.cache;

import com.imooc.util.JedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @Project : imooc-shiro
 * @Description :  [一句话描述该类的功能]
 * @Package Name :com.imooc.cache
 * @Author : 1643091610@qq.com
 * @Blog ：https://www.cnblogs.com/xiaohaojs/
 * @Date :2019 年 05月 20 日 20:06
 * @ModifcationHistory : ------Who----------When---------------What----------
 */
@Component
public class RedisCache<K, V> implements Cache<K, V> {

    @Resource
    private JedisUtil jedisUtil;

    private final String CACHE_PREFIX = "imooc-cache";

    private byte[] getKey(K k) {
        if (k instanceof String) {
            return (CACHE_PREFIX + k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    public V get(K k) throws CacheException {
        System.out.println("从redis获取权限数据");
        byte[] bytes = jedisUtil.get(getKey(k));
        if (bytes!=null){
            return (V) SerializationUtils.deserialize(bytes);
        }
        return null;
    }

    public V put(K k, V v) throws CacheException {
        byte[] key = getKey(k);
        byte[] serialize = SerializationUtils.serialize(v);
        jedisUtil.set(key,serialize);
        jedisUtil.expire(key,600);
        return v;
    }

    public V remove(K k) throws CacheException {
        byte[] key = getKey(k);
        byte[] bytes = jedisUtil.get(key);
        jedisUtil.del(key);
        if (bytes!=null){
         return (V) SerializationUtils.deserialize(bytes);
        }
        return null;
    }

    public void clear() throws CacheException {

    }

    public int size() {
        return 0;
    }

    public Set<K> keys() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }
}
