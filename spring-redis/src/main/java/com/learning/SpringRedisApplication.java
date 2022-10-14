package com.learning;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import redis.embedded.RedisServer;

@SpringBootApplication
@EnableCaching
public class SpringRedisApplication {

	private RedisServer redisServer;
	
	@Value("${spring.redis.port}")
	private int redisPort;

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisApplication.class, args);
	}

	@PostConstruct
	public void startRedis() throws IOException {
		redisServer = new redis.embedded.RedisServer(redisPort);
		redisServer.start();
	}

	@PreDestroy
	public void stopRedis() {
		redisServer.stop();
	}

}
