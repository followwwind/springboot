package com.wind.bootsample.service;

import com.wind.bootsample.BootSampleApplication;
import com.wind.bootsample.dao.UserInfoDao;
import com.wind.bootsample.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootSampleApplication.class)
public class UserInfoTest {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void insert(){
        UserInfo userInfo = new UserInfo();

        userInfo.setUsername("follow");
        userInfo.setPassword("123456");

        userInfoService.insert(userInfo);
    }

    @Test
    public void selectByPrimaryKey(){
        UserInfo userInfo= userInfoService.selectByPrimaryKey(1l);
        System.out.println(userInfo.getUsername());

    }

    @Test
    public void selectByCondition(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("wind");
        List<UserInfo> userInfos = userInfoService.selectByCondition(userInfo);
        System.out.println(userInfos.size());
    }


    /*@Test
    public void testSaveIndex(){
        UserInfo userInfo = new UserInfo();

        userInfo.setUsername("follow");
        userInfo.setPassword("123456");

        userInfoDao.save(userInfo);
    }

    @Test
    public void testSearch() {
        String queryString = "follow";//搜索关键字
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
        Iterable<UserInfo> searchResult = userInfoDao.search(builder);
        Iterator<UserInfo> iterator = searchResult.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }*/
}
