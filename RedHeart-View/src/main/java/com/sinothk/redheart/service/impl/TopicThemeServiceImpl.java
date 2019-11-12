package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinothk.base.entity.ResultData;
import com.sinothk.base.utils.IdUtil;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.redheart.domain.*;
import com.sinothk.redheart.repository.TopicMapper;
import com.sinothk.redheart.repository.TopicThemeMapper;
import com.sinothk.redheart.repository.TopicThemeUserMapper;
import com.sinothk.redheart.service.TopicService;
import com.sinothk.redheart.service.TopicThemeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("topicThemeService")
public class TopicThemeServiceImpl implements TopicThemeService {

    @Resource(name = "topicThemeMapper")
    private TopicThemeMapper topicThemeMapper;
    @Resource(name = "topicThemeUserMapper")
    private TopicThemeUserMapper topicThemeUserMapper;

    @Override
    public ResultData<Boolean> add(TopicThemeEntity topicThemeEntity) {
        try {
            topicThemeMapper.insert(topicThemeEntity);
            return ResultData.success(true);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<List<TopicThemeAo>> getAllTopicThemeList(Long account) {
        try {
            List<TopicThemeAo> list = topicThemeMapper.getAllTopicThemeList(account);
            return ResultData.success(list);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<String> likeTheme(TopicThemeUserEntity ttuEntity) {
        try {
            // 判断是否存在
            QueryWrapper<TopicThemeUserEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(TopicThemeUserEntity::getAccount, ttuEntity.getAccount())
                    .eq(TopicThemeUserEntity::getThemeCode, ttuEntity.getThemeCode());

            TopicThemeUserEntity ttuDbEntity = topicThemeUserMapper.selectOne(wrapper);

            if (ttuDbEntity == null) {
                topicThemeUserMapper.insert(ttuEntity);
                return ResultData.success("关注成功");
            } else {
                topicThemeUserMapper.deleteById(ttuDbEntity.getId());
                return ResultData.success("取消关注");
            }
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<List<TopicThemeAo>> getMyTopicThemeList(Long account) {
        try {
            List<TopicThemeAo> list = topicThemeUserMapper.getMyTopicThemeList(account);

            if (list == null || list.size() == 0) {
                list = topicThemeMapper.getAllTopicThemeList(account).subList(0, 5);
            }

            TopicThemeAo topicTheme = new TopicThemeAo();
            topicTheme.setAccount(account);
            topicTheme.setThemeCode("");
            topicTheme.setThemeTxt("全部");
            topicTheme.setThemeIcon("living/10001/sysem/201911/theme_all2.jpg");
            topicTheme.setRemark("1.5");
            list.add(0, topicTheme);

            return ResultData.success(list);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<ThemeOtherInfoAo> getUserWhitThemeRelation(Long account, String themeCode) {

        try {
            int themeMemberNum = topicThemeUserMapper.getThemeMemberNum(themeCode);

            int themeTopicNum = topicThemeUserMapper.getThemeTopicNum(themeCode);

            int themeUserNum = topicThemeUserMapper.getThemeUserRelation(themeCode, account);

            ThemeOtherInfoAo data = new ThemeOtherInfoAo();
            data.setThemeMemberNum(themeMemberNum);
            data.setThemeTopicNum(themeTopicNum);
            data.setThemeUserRela(themeUserNum > 0);

            return ResultData.success(data);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
