package com.hoon.spring.service.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by babybong on 2018. 1. 16..
 */
@Slf4j
@Service
public class TableServiceImpl implements TableService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createTable() {
        log.info("> 테이블 생성 시작");
        jdbcTemplate.execute("DROP TABLE shorten_urls IF EXISTS");

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE shorten_urls(id NUMBER(10, 0) NOT NULL AUTO_INCREMENT,      ");
        sb.append("                         shorten_url VARCHAR2(10),                       ");
        sb.append("                         origin_url VARCHAR2(255), PRIMARY KEY(id) )     ");
        jdbcTemplate.execute(sb.toString());
        log.info("> 테이블 생성 완료");
    }

    public void createSequence() {
        log.info("> 시퀀스 생성 시작");
        jdbcTemplate.execute("DROP SEQUENCE id_seq IF EXISTS");

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE SEQUENCE id_seq     ");
        sb.append("MINVALUE 1000000001        ");
        sb.append("MAXVALUE 9999999999        ");
        sb.append("START WITH 1000000001      ");
        sb.append("INCREMENT BY 1             ");
        sb.append("CACHE 1                    ");
        jdbcTemplate.execute(sb.toString());
        log.info("> 시퀀스 생성 완료");
    }
}
