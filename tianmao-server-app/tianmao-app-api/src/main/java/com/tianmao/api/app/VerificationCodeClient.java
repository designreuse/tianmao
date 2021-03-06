package com.tianmao.api.app;

import com.tianmao.service.model.sencode.VerificationCode;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${server.app.name}")
public interface VerificationCodeClient {

    @RequestMapping(value = "/vercode/verification")
    void verification(@RequestParam("username") String username, @RequestParam("code") Integer code);

    @RequestMapping(value = "/vercode/destroy")
    void destroy(@RequestParam("username") String username, @RequestParam("code") Integer code);

    @RequestMapping(value = "/vercode/getByUsername")
    VerificationCode getByUsername(@RequestParam("username") String username);

    @RequestMapping(value = "/vercode/create")
    void create(VerificationCode verificationCode);
}
