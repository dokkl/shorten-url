package com.hoon.spring.infra.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by babybong on 2018. 1. 17..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@TestPropertySource(properties = {"hoon.server.domain=http://localhost:8080/",
                                  "hoon.strategy.algrithm=shortenUrlAlgorithmBase62Impl"})
public abstract class IntegrationTest {
}

