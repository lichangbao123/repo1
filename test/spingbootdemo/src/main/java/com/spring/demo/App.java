package com.spring.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
/**
 * Hello world!
 *
 */
@SpringBootApplication(exclude={
		RedisAutoConfiguration.class,
		RedisRepositoriesAutoConfiguration.class
})
@MapperScan("com.spring.demo.mapper")
@EnableCaching
public class App extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}
}
