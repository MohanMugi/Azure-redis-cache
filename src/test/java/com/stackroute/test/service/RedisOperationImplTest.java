package com.stackroute.test.service;

import com.stackroute.service.RedisOperation;
import com.stackroute.service.RedisOperationImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class RedisOperationImplTest {
    private static RedisOperation redisOperation;
    private static final String MESSAGE = "Check the implementation of the method";


    @BeforeEach
    void setUp() {
        redisOperation = new RedisOperationImpl();
        redisOperation.saveKeyValuePair("bookName1", "Time Machine");
        redisOperation.saveKeyValuePair("bookName2", "The Secret");
    }

    @AfterEach
    void tearDown() {
        redisOperation.deleteAllKeyValuePairs();
    }

    @Test
    void givenKeyValueToSaveThenShouldReturnStatus() {
        String actualStatus = redisOperation.saveKeyValuePair("bookName5", "The Heart of India");
        assertEquals("OK", actualStatus, MESSAGE);
    }

    @Test
    void givenKeyToRetrieveValueThenShouldReturnBookName() {
        String actualRetrievedBookName = redisOperation.retrieveValueFromKey("bookName2");
        assertEquals("The Secret", actualRetrievedBookName, MESSAGE);
    }

    @Test
    void givenCallToDeleteAllKeyValuePairsThenShouldReturnStatus() {
        String actualStatus = redisOperation.deleteAllKeyValuePairs();
        assertEquals("OK", actualStatus, MESSAGE);
    }

    @Test
    void givenValueToUpdateThenShouldReturnUpdatedValue() {
        String actualUpdatedBookName = redisOperation.updateValueForKey("bookName2", "The Secret by Rhonda Byrne");
        assertEquals("The Secret by Rhonda Byrne", actualUpdatedBookName, MESSAGE);
    }
}