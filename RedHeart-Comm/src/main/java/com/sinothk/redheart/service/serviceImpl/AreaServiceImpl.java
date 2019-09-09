package com.sinothk.redheart.service.serviceImpl;

import com.sinothk.redheart.domain.AreaEntity;
import com.sinothk.redheart.domain.GaoDeAreaEntity;
import com.sinothk.redheart.repository.AreaMapper;
import com.sinothk.redheart.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("areaService")
public class AreaServiceImpl implements AreaService {

    @Resource(name = "areaMapper")
    private AreaMapper areaMapper;


    @Override
    public void add(GaoDeAreaEntity areaEntity) {

        if (areaEntity.getDistricts().size() > 0) {

            for (AreaEntity item : areaEntity.getDistricts()) {
                areaMapper.insert(item); // 1

                for (AreaEntity item2 : item.getDistricts()) { // 2
                    areaMapper.insert(item2);

                    for (AreaEntity item3 : item2.getDistricts()) { // 3
                        areaMapper.insert(item3);
                    }
                }
            }
        }
    }
}
