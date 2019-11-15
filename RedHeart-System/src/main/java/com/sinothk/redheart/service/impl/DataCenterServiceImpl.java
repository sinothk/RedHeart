package com.sinothk.redheart.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.DataCenterEntity;
import com.sinothk.redheart.domain.UserEntity;
import com.sinothk.redheart.domain.UserLoginAO;
import com.sinothk.redheart.repository.DataCenterMapper;
import com.sinothk.redheart.service.DataCenterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("dataCenterService")
public class DataCenterServiceImpl implements DataCenterService {

    @Resource(name = "dataCenterMapper")
    private DataCenterMapper dataCenterMapper;

    @Override
    public ResultData<PageData<UserLoginAO>> getTodayLoginUserPageList(int currPage, int pageSize) {
        try {
            Page<UserEntity> pageVo = new Page<>(currPage, pageSize);
            IPage<UserLoginAO> pageInfo = dataCenterMapper.getTodayLoginUserPageList(pageVo);

            PageData<UserLoginAO> pageEntity = new PageData<>();
            pageEntity.setPageSize(pageSize);
            pageEntity.setPageNum(currPage);

            pageEntity.setData(pageInfo.getRecords());
            pageEntity.setTotal((int) pageInfo.getTotal());
            int currSize = currPage * pageSize;
            pageEntity.setHasMore(currSize < pageInfo.getTotal());

            return ResultData.success(pageEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<PageData<UserLoginAO>> getWeekLoginUserPageList(int currPage, int pageSize) {
        try {
            Page<UserEntity> pageVo = new Page<>(currPage, pageSize);
            IPage<UserLoginAO> pageInfo = dataCenterMapper.getWeekLoginUserPageList(pageVo);

            PageData<UserLoginAO> pageEntity = new PageData<>();
            pageEntity.setPageSize(pageSize);
            pageEntity.setPageNum(currPage);

            pageEntity.setData(pageInfo.getRecords());
            pageEntity.setTotal((int) pageInfo.getTotal());
            int currSize = currPage * pageSize;
            pageEntity.setHasMore(currSize < pageInfo.getTotal());

            return ResultData.success(pageEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<PageData<UserLoginAO>> getThisMonthLoginUserPageList(int currPage, int pageSize) {
        try {
            Page<UserEntity> pageVo = new Page<>(currPage, pageSize);
            IPage<UserLoginAO> pageInfo = dataCenterMapper.getThisMonthLoginUserPageList(pageVo);

            PageData<UserLoginAO> pageEntity = new PageData<>();
            pageEntity.setPageSize(pageSize);
            pageEntity.setPageNum(currPage);

            pageEntity.setData(pageInfo.getRecords());
            pageEntity.setTotal((int) pageInfo.getTotal());
            int currSize = currPage * pageSize;
            pageEntity.setHasMore(currSize < pageInfo.getTotal());

            return ResultData.success(pageEntity);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }

    @Override
    public ResultData<List<DataCenterEntity>> getYearLoginUserPageList(String yearStr) {
        try {
            List<DataCenterEntity> yearDataList = dataCenterMapper.getYearLoginUserPageList(yearStr);

            // 转为Map
            HashMap<String, Integer> tempHashMap = new HashMap<>();
            for (DataCenterEntity yearData : yearDataList) {
                tempHashMap.put(yearData.getMouthNum(), yearData.getTotal());
            }

            // 循环查找不存在的月份
            for (int i = 1; i < 13; i++) {
                String monthStr;
                if (i < 10) {
                    monthStr = "0" + i;
                } else {
                    monthStr = "" + i;
                }
                // 不存在则设置为0
                tempHashMap.putIfAbsent(monthStr, 0);

//                Integer v = tempHashMap.get(monthStr);
//                if (v == null) {
//                    tempHashMap.put(monthStr, 0);
//                }
            }

            Iterator iter = tempHashMap.entrySet().iterator();
            yearDataList.clear();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = String.valueOf(entry.getKey());
                yearDataList.add(new DataCenterEntity(key, tempHashMap.get(key)));
            }

            Collections.sort(yearDataList);
            return ResultData.success(yearDataList);
        } catch (Exception e) {
            return ResultData.error(e.getCause().getMessage());
        }
    }
}
