package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinothk.redheart.domain.TopicEntity;
import org.springframework.stereotype.Repository;

@Repository("topicMapper")
public interface TopicMapper extends BaseMapper<TopicEntity> {
}
