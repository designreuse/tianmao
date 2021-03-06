package com.tianmao.service.app.mapper;

import com.tianmao.service.model.user.UserMessage;
import org.apache.ibatis.annotations.Param;
import com.tianmao.service.mybatis.BaseMapper;

import java.util.List;

public interface UserMessageMapper extends BaseMapper<UserMessage> {


    /**
     * 通过用户id获取没有读的数据
     * @param userId
     * @return
     */
    List<UserMessage> getUserMessageById(Long userId);

    /**
     * 通过is数组更新已读数据
     * @param ids
     * @return
     */
    int updateMessageByids(@Param("ids") String ids);
}
