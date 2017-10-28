package io.khasang.bazaar.model;

import io.khasang.bazaar.config.HibernateConfig;
import io.khasang.bazaar.config.application.WebConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, HibernateConfig.class})
public class CreateTableTest {

    @Autowired
    CreateTable createTable;

    @Test
    public void testCreateStatus(){
        assertEquals("Table created", createTable.createStatus());
    }
}
