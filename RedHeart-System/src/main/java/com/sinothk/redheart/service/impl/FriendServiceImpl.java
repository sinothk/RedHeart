package com.sinothk.redheart.service.impl;

import com.sinothk.base.entity.PageData;
import com.sinothk.base.entity.ResultData;
import com.sinothk.redheart.domain.FriendEntity;
import com.sinothk.redheart.service.FriendService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("friendService")
public class FriendServiceImpl implements FriendService {
    int index = 0;

    @Override
    public ResultData<PageData<ArrayList<FriendEntity>>> getFriendsList(String username, int pageNum) {

        PageData<ArrayList<FriendEntity>> page = new PageData<>();

        if (pageNum == 1) {

            index = 0;

            page.setHasMore(true);
            page.setPageSize(10);

            ArrayList<FriendEntity> fList = new ArrayList<>();

            for (int i = 0; i < page.getPageSize(); i++) {
                index = i;

                FriendEntity friend = new FriendEntity();
                friend.setUserName("name" + i);
                friend.setAvatar("https://wx.qlogo.cn/mmhead/k947icPboBqBtOchX0uVF2iaJ1bNEXBUfOMibsKaQPXPr9YqpVTEN0I5w/0");
                fList.add(friend);
            }

            page.setData(fList);
            return ResultData.success(page);
        } else {
            page.setHasMore(false);
            page.setPageSize(10);

            ArrayList<FriendEntity> fList = new ArrayList<>();

            for (int j = 0; j < page.getPageSize(); j++) {

                index += 1;

                FriendEntity friend = new FriendEntity();
                friend.setUserName("name_" + index);
                friend.setAvatar("https://wx.qlogo.cn/mmhead/k947icPboBqBtOchX0uVF2iaJ1bNEXBUfOMibsKaQPXPr9YqpVTEN0I5w/0");
                fList.add(friend);
            }

            page.setData(fList);
            return ResultData.success(page);
        }
    }
}
