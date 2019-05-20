package com.imooc.session;

import com.imooc.util.JedisUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
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
 * @Package Name :com.imooc.session
 * @Author : 1643091610@qq.com
 * @Blog ：https://www.cnblogs.com/xiaohaojs/
 * @Date :2019 年 05月 09 日 19:57
 * @ModifcationHistory : ------Who----------When---------------What----------
 */
public class RedisSessionDao extends AbstractSessionDAO {

    @Resource
    private JedisUtil jedisUtil;

    private final String SHIRO_SESSION_PREFIX = "imooc-session";

    private byte[] getkey(String key) {
        return (SHIRO_SESSION_PREFIX + key).getBytes();
    }

    private void saveSession(Session session) {
        if (session != null && session.getId() != null) {
            byte[] key = getkey(session.getId().toString());
            byte[] value = SerializationUtils.serialize(session);
            jedisUtil.set(key, value);
            jedisUtil.expire(key, 600);
        }
    }

    protected Serializable doCreate(Session session) {
        Serializable serializable = generateSessionId(session);
        assignSessionId(session, serializable); //捆绑sessionID
        saveSession(session);
        return serializable;
    }

    protected Session doReadSession(Serializable serializable) {
        System.out.println("read session");
        if (serializable == null) {
            return null;
        }
        byte[] key = getkey(serializable.toString());
        byte[] value = jedisUtil.get(key);
        return (Session) SerializationUtils.deserialize(value);  //反序列化
    }

    public void update(Session session) throws UnknownSessionException {
        if (session != null && session.getId() != null) {
            saveSession(session);
        }
    }

    public void delete(Session session) {
        if (session == null && session.getId() == null) {
            return;
        }
        byte[] key = getkey(session.getId().toString());
        jedisUtil.del(key);
    }

    public Collection<Session> getActiveSessions() {
        Set<byte[]> keys = jedisUtil.keys(SHIRO_SESSION_PREFIX);
        Set<Session> sessions = new HashSet<Session>();
        if (CollectionUtils.isEmpty(keys)) {
            return sessions;
        }
        for (byte[] key : keys) {
            Session session = (Session) SerializationUtils.deserialize(jedisUtil.get(key));
            sessions.add(session);
        }
        return sessions;
    }
}
