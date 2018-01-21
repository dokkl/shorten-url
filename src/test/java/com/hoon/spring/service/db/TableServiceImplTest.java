package com.hoon.spring.service.db;

import com.hoon.spring.service.vo.ShortenUrlVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.*;

/**
 * Created by babybong on 2018. 1. 21..
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class TableServiceImplTest {
    @InjectMocks
    private TableServiceImpl tableService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Test
    public void createTable_test() {
        //when
        doNothing().when(jdbcTemplate).execute(any(String.class));

        //then
        tableService.createTable();
    }

    @Test
    public void createSequence_test() {
        //when
        doNothing().when(jdbcTemplate).execute(any(String.class));

        //then
        tableService.createSequence();
    }
}
