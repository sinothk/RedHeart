package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinothk.redheart.domain.AreaEntity;
import org.springframework.stereotype.Repository;

@Repository("areaMapper")
public interface AreaMapper extends BaseMapper<AreaEntity> {
}
