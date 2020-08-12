package cn.tedu.straw.api.mapper;

import cn.tedu.straw.commons.model.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jdk.nashorn.internal.lookup.MethodHandleFactory;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserMapperTests {

    @Autowired
    UserMapper mapper;
    @Test
    void  insert(){
        User user = new User();
        user.setUsername("spring1");
        user.setPassword("1234");
        int rows = mapper.insert(user);
        System.out.println(rows);
        log.debug("rows={}",rows);



    }
    @Test
    void deleteById (){
        Integer id = 1;
        int rows = mapper.deleteById(id);
        log.debug("rows={}",rows);
    }
    @Test
    void  updateById(){
        User user = new User();
        user.setId(3);
        user.setPassword("8888");
        int rows = mapper.updateById(user);
        log.debug("rows={}",rows);
    }
    @Test
    void  a (){
        Integer id = 4;
        User user =  mapper.selectById(id);
        log.debug("query result >>> {}",user);
    }
    @Test
    void  selectByUsername(){
        String username = "admin";
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //eq方法用于表示username=？ ，表示'等于'的判断条件
        queryWrapper.eq("username",username);

        //执行查询
         User user = mapper.selectOne(queryWrapper);
          //测试输出
         log.debug("query result >>> {}",user);
    }
    @Test
    void  selectList(){
        //当查询列表的参数queryWrapper为null时，表示没有where子句
        List<User> users = mapper.selectList(null);
        log.debug("query result >>> {}",users.size());

//        for ((User user : users)){
//            log.debug(">>> {}",user);
//        }
    }
}
