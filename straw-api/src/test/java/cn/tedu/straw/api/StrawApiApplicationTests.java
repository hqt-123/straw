package cn.tedu.straw.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
public class StrawApiApplicationTests {
    @Test
    void contextLoads(){


    }
    @Autowired
    DataSource dataSource;
    @Test
    void getConnection() throws  Exception{
        System.out.println(dataSource.getConnection());
    }
}
