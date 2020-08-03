package com.stackroute.service;

public interface RedisOperation {


    /**
     * AbstractMethod to saves Key-Value pair
     */
    String saveKeyValuePair(String key, String value);

    /**
     * AbstractMethod to retrieve value for a given key
     */
    String retrieveValueFromKey(String key);

    /**
     * AbstractMethod to delete all key values pairs
     */
    String deleteAllKeyValuePairs();

    /**
     * AbstractMethod to update value for the key
     */
    String updateValueForKey(String key, String value);

}
