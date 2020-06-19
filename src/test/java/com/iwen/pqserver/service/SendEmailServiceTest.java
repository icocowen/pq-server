package com.iwen.pqserver.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SendEmailServiceTest {

    @Autowired
    SendEmailService sendEmailService;

    @Test
    void sendVerifyCode() {
        sendEmailService.sendVerifyCode("1234", "164074663@qq.com");
    }
}