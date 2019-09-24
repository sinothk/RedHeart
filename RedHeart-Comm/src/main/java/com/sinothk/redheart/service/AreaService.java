package com.sinothk.redheart.service;

import com.sinothk.redheart.domain.AreaEntity;
import com.sinothk.redheart.domain.GaoDeAreaEntity;

import java.util.ArrayList;

public interface AreaService {

    void add(GaoDeAreaEntity areaEntity);

    ArrayList<AreaEntity> findAllDistricts();

    void initDistrict(GaoDeAreaEntity parseObject);

    ArrayList<AreaEntity> getAreaList(String adCode);
}
