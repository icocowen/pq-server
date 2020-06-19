package com.iwen.pqserver.utils;

import cn.hutool.crypto.digest.DigestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

@Component
@Slf4j
public class EncodeHelper {

    @Value("${secure.password.salt}")
    private String salt;


    public String encodePassword(String origin) {
        return DigestUtil.sha1Hex(salt + origin + salt);
    }


    public String generateVerifyCode() {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            int i1 = random.nextInt(10);
            sb.append(i1);
        }
        return sb.toString();
    }



}
