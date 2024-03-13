package com.example.vktest.configuration;

import com.example.vktest.client.AlbumClient;
import com.example.vktest.client.PostsClient;
import com.example.vktest.client.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Bean
    public UserClient userClient(ApplicationConfiguration configuration) {
        return new UserClient(configuration);
    }

    @Bean
    public PostsClient postsClient(ApplicationConfiguration configuration) {
        return new PostsClient(configuration);
    }

    @Bean
    public AlbumClient albumClient(ApplicationConfiguration configuration) {
        return new AlbumClient(configuration);
    }
}
