package com.hoon.spring.infra.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by babybong on 2018. 1. 17..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public abstract class IntegrationTest {
}

