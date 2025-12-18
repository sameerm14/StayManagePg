package com.pg.StayManage.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", System.getenv("djzaagoyb"),
            "api_key", System.getenv("684577884617557"),
            "api_secret", System.getenv("KR0TngVe-HS6oGBQT8hiV74zIJE")
        ));
    }
}
