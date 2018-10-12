package com.lq.redis;

import com.google.common.base.Joiner;
import com.lq.enums.CahceKeyConst;
import com.lq.utils.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;
import javax.naming.Name;

/**
 * @Auther: LQ
 * @Date: 2018/10/11 23:40
 * @Description: redis缓存
 */
@Service(value="sysCacheServiceOfRedis")
@Slf4j
public class SysCacheServiceOfRedis {

    @Resource(name = "redisPool")
    private RedisPool redisPool;

    public void saveCache(String toSavedValue, int timeOutSeconds, CahceKeyConst prefix) {
        saveCache(toSavedValue, timeOutSeconds, prefix, null);
    }

    public void saveCache(String toSavedValue, int timeOutSeconds, CahceKeyConst prefix, String... keys) {
        if (StringUtils.isBlank(toSavedValue)) {
            return;
        }
        ShardedJedis shardedJedis = null;
        try {
            String key = generateKey(prefix, keys);
            shardedJedis = redisPool.instance();
            shardedJedis.setex(key, timeOutSeconds, toSavedValue);
        } catch (Exception e) {
            log.error("save cache error e {} preifx {}  keys {} ", e, prefix, JsonMapper.obj2String(keys));
        } finally {
            redisPool.safeClose(shardedJedis);
        }
    }

    public String getFromCache(CahceKeyConst prefix, String... keys) {
        ShardedJedis shardedJedis = null;
        String key = generateKey(prefix, keys);
        try {
            shardedJedis = redisPool.instance();
            String value = shardedJedis.get(key);
            return value;
        } catch (Exception e) {
            log.error("get cache error e {} preifx {}  keys {} ", e, prefix, JsonMapper.obj2String(keys));
            return null;
        } finally {
            redisPool.safeClose(shardedJedis);
        }
    }


    public void deleteFromCache(CahceKeyConst prefix, String... keys) {
        ShardedJedis shardedJedis = null;
        String key = generateKey(prefix, keys);
        try {
            shardedJedis = redisPool.instance();
            shardedJedis.del(key);
        } catch (Exception e) {
            log.error("delete cache error e {} preifx {}  keys {} ", e, prefix, JsonMapper.obj2String(keys));
        } finally {
            redisPool.safeClose(shardedJedis);
        }
    }

    public void updateCache(String toSavedValue, int timeOutSeconds, CahceKeyConst prefix, String... keys) {
        if (StringUtils.isBlank(toSavedValue)) {
            return;
        }
        ShardedJedis shardedJedis = null;
        try {
            String key = generateKey(prefix, keys);
            shardedJedis = redisPool.instance();
            deleteFromCache(prefix, keys);
            saveCache(toSavedValue, timeOutSeconds, prefix, keys);
        } catch (Exception e) {
            log.error("update cache error e {} preifx {}  keys {} ", e, prefix, JsonMapper.obj2String(keys));
        } finally {
            redisPool.safeClose(shardedJedis);
        }
    }


    private String generateKey(CahceKeyConst prefix, String... keys) {
        String key = prefix.name();
        if (keys != null && keys.length > 0) {
            key += "_" + Joiner.on("_").join(keys);
        }
        return key;
    }
}
