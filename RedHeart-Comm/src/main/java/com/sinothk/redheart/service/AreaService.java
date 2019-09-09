package com.sinothk.redheart.service;

import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.FileCoverEntity;
import com.sinothk.redheart.domain.FileEntity;
import com.sinothk.redheart.domain.GaoDeAreaEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public interface AreaService {

    void add(GaoDeAreaEntity areaEntity);
}
