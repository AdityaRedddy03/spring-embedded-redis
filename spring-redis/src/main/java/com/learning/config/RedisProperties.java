package com.learning.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedisProperties {

	@Value("${spring.redis.port}")
	private int redisPort;

	@Value("${spring.redis.host}")
	private String redisHost;

}
