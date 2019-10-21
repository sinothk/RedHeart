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
public class AccountInitLoader implements InitializingBean, ServletContextAware {

    @Resource(name = "userMapper")
    UserMapper userMapper;

    @Override
    public void afterPropertiesSet() {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        // 查询现有账号，并缓存
        Set<Long> accSet = userMapper.getUserAccountSet();
        if (accSet == null) {
            accSet = new HashSet<>();
            accSet.add(100000L);
            AccountUtil.init(accSet);
        } else {
            AccountUtil.init(accSet);
        }
    }

    /**
     * 系统预留账号
     *
     * @return
     */
    public static Set<Long> getInitAccountSet() {
        Set<Long> set = new HashSet<>();
        set.add(99999L);
        set.add(100000L);

        set.add(100002L);
        set.add(100003L);

        set.add(100006L);

        set.add(100008L);

        set.add(88888888L);
        return set;
    }
}
