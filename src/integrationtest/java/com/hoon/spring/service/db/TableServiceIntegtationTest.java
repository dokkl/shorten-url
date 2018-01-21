package com.hoon.spring.service.db;

import com.hoon.spring.infra.test.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by babybong on 2018. 1. 21..
 */
public class TableServiceIntegtationTest extends IntegrationTest {
    @Autowired
    private TableService tableService;

    @Test
    public void createTable_test() {
        tableService.createTable();
    }

    @Test
    public void createSequence_test() {
        tableService.createSequence();
    }
}
