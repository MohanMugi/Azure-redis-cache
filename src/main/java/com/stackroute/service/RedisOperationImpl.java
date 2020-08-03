package com.stackroute.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

import java.util.logging.Logger;

public class RedisOperationImpl implements RedisOperation {
    private Logger logger = Logger.getLogger(RedisOperationImpl.class.getName());
    private boolean useSsl = true;
    /*
     * Retrieve `Host name` from cache `Properties` and add to `cacheHostname` property.
     */
    private String cacheHostname = "lavanms-cache.redis.cache.windows.net";

    /*
     * Retrieve `Primary` access key from cache `Access keys` and add to `cachekey` property.
     */
    private String cachekey = "MoRwtX07FLblAx74r+kQCNBXXXXB46zyfbd3hIXexcZop5XbrXAI=";

    private int portNummber = 6380;



    Jedis jedis;




    public RedisOperationImpl() {
        init(useSsl, cacheHostname, cachekey);
    }


    /*
     * To connect to the Azure Cache for Redis
     */
    private void init(boolean useSsl, String cacheHostname, String cachekey) {
        /*
         * Add code to connect to the Azure Cache for Redis
         */
        JedisShardInfo jedisShardInfo = new JedisShardInfo(cacheHostname, portNummber, useSsl );
        jedisShardInfo.setPassword(cachekey);
        jedis = new Jedis(jedisShardInfo);
    }

    /*
     * Implementation of saveKeyValuePair() method
     */
    public String saveKeyValuePair(String key, String value) {
        /*
         * Add code to save key value pair to the Azure Cache for Redis
         */
        try {
            String statusCoseReply = jedis.set(key,value);
            logger.info(statusCoseReply);
            return statusCoseReply;
        }
        catch (Exception exception) {
            return exception.toString();
        }

    }

    /*
     * Implementation of retrieveValueFromKey() method
     */
    public String retrieveValueFromKey(String key) {
        /*
         * Add code to retrieve value from the Azure Cache for Redis
         */
        String value = "";
        if (jedis.exists(key)) {
            value = jedis.get(key);
            logger.info(value);
        }
        return value;
    }

    /*
     * Implementation of deleteAllKeyValuePairs() method
     */
    public String deleteAllKeyValuePairs() {
        /*
         * Add code to delete all key value pairs from the Azure Cache for Redis
         */
        String deleteStatus = jedis.flushDB();
        logger.info(deleteStatus);
        return deleteStatus;
    }

    /*
     * Implementation of updateValueForKey() method
     */
    @Override
    public String updateValueForKey(String key, String value) {
        /*
         * Add code to update value for key to the Azure Cache for Redis
         */
        try {
            String updateStatus = jedis.set(key, value);
            logger.info(updateStatus);
            String updateBookName = jedis.get(key);
            logger.info(updateBookName);
            return updateBookName;
        }
        catch (Exception exception) {
            return exception.toString();
        }

    }


}
