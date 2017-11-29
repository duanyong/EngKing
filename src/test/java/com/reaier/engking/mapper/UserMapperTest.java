package com.reaier.engking.mapper;

import com.reaier.engking.bean.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by PP on 29/11/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    private String username = "test for junit";
    private String password = "test for junit";

    public void insert() {

    }

    @Test
    public void testInsert() throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(Md5Util.toMD5(password));

        mapper.insert(user);

        Assert.assertEquals(3, mapper.selectAll().size());
    }

    @Test
    public void testList() throws Exception {
        mapper.selectAll();
        Assert.assertEquals();
    }

    @Test
    public void testDelete() throws Exception {

    }
}
