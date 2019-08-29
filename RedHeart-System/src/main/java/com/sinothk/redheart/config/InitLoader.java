package com.sinothk.redheart.config;

import com.sinothk.base.utils.AccountUtil;
import com.sinothk.redheart.repository.UserMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.HashSet;
import java.util.Set;

/**
 * 读取数据库参数，设置初始值！
 */
@Service
public class InitLoader implements InitializingBean, ServletContextAware {

    @Resource(name = "userMapper")
    UserMapper userMapper;

    @Override
    public void afterPropertiesSet() {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {

        Set<Long> accSet = userMapper.getUserAccountSet();

        if (accSet == null || accSet.size() == 0) {
            Set<Long> accSet2 = new HashSet<>();
            accSet2.add(9999L);
            accSet2.add(10000L);
            AccountUtil.init(accSet2);

        } else {
            AccountUtil.init(accSet);
        }
    }
}
