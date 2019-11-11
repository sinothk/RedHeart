package com.sinothk.redheart.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinothk.base.utils.StringUtil;
import com.sinothk.redheart.domain.AreaEntity;
import com.sinothk.redheart.domain.GaoDeAreaEntity;
import com.sinothk.redheart.repository.AreaMapper;
import com.sinothk.redheart.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("areaService")
public class AreaServiceImpl implements AreaService {

    @Resource(name = "areaMapper")
    private AreaMapper areaMapper;


    @Override
    public void add(GaoDeAreaEntity areaEntity) {

//        if (areaEntity.getDistricts().size() > 0) {
//
//            for (AreaEntity item : areaEntity.getDistricts()) {
//
//                item.setParent("100000");
//                areaMapper.insert(item); // 1
//
//                for (AreaEntity item2 : item.getDistricts()) { // 2
//
//                    item2.setParent(item.getAdcode());
//                    areaMapper.insert(item2);
//
//                    for (AreaEntity item3 : item2.getDistricts()) { // 3
//                        item3.setParent(item2.getAdcode());
//                        areaMapper.insert(item3);
//                    }
//                }
//            }
//        }
    }

    @Override
    public ArrayList<AreaEntity> findAllDistricts() {
        QueryWrapper<AreaEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AreaEntity::getLevel, "district");
        return (ArrayList<AreaEntity>) areaMapper.selectList(wrapper);
    }

    @Override
    public void initDistrict(GaoDeAreaEntity parseObject) {
//        if (parseObject.getDistricts().size() > 0) {
//
//            for (AreaEntity item : parseObject.getDistricts()) {
//
//                for (AreaEntity item2 : item.getDistricts()) { // 2
//                    item2.setParent(item.getAdcode());
//                    areaMapper.insert(item2);
//                }
//            }
//        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<AreaEntity> getAreaList(String adCode) {
        try {
            QueryWrapper<AreaEntity> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(AreaEntity::getParent, adCode).groupBy(AreaEntity::getCenter).orderByAsc(AreaEntity::getAdCode, AreaEntity::getId); //groupBy(AreaEntity::getCenter).
            List<AreaEntity> list = areaMapper.selectList(wrapper);
            if (list == null) {
                return new ArrayList<>();
            } else {
                return (ArrayList<AreaEntity>) list;
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
