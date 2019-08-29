package com.sinothk.redheart.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinothk.redheart.domain.FileEntity;
import org.springframework.stereotype.Repository;

@Repository("fileMapper")
public interface FileMapper extends BaseMapper<FileEntity> {
}
