package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinothk.redheart.domain.FileCoverEntity;
import org.springframework.stereotype.Repository;

@Repository("fileCoverMapper")
public interface FileCoverMapper extends BaseMapper<FileCoverEntity> {
}
