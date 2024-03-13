package com.example.vktest;

import com.example.vktest.configuration.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfiguration.class)
public class VkTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkTestApplication.class, args);
	}

}
