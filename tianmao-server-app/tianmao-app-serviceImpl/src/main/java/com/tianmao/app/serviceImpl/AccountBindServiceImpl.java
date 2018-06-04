package com.tianmao.app.serviceImpl;

import com.tianmao.common.HttpCode;
import com.tianmao.model.user.AccountBind;
import com.tianmao.service.app.AccountBindService;
import com.tianmao.type.user.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tianmao.app.mapper.AccountBindMapper;
import com.tianmao.service.exception.BaseServiceException;

@RestController
public class AccountBindServiceImpl implements AccountBindService {
    @Autowired
    private AccountBindMapper accountBindMapper;
    @Override
    public void create( @RequestBody AccountBind accountBind) {
        AccountBind dbAccountBind = accountBindMapper.getAccountBindByOpenId(accountBind.getOpenid(), accountBind.getAccountType());
        if (dbAccountBind != null) {
            throw new BaseServiceException(HttpCode.WEIXIN_ACCOUNT_ALREADY_BIND, "微信账号已经绑定");
        }
        dbAccountBind = accountBindMapper.getAccountBindByUserId(accountBind.getUserId(), accountBind.getAccountType());
        if (dbAccountBind != null) {
            throw new BaseServiceException(HttpCode.MOBILE_ALREADY_BIND, "该手机已经被绑定过");
        }
         accountBindMapper.create(accountBind);
    }

    @Override
    public AccountBind getAccountBindByOpenId(String openid,  @RequestBody AccountType accountType) {
        return accountBindMapper.getAccountBindByOpenId(openid, accountType);
    }
}