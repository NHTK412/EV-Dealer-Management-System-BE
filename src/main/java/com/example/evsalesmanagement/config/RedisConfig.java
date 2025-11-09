package com.example.evsalesmanagement.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

        @Bean
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
                RedisTemplate<String, Object> template = new RedisTemplate<>();
                template.setConnectionFactory(connectionFactory);

                // Dùng JSON
                GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();

                template.setKeySerializer(new StringRedisSerializer());

                template.setValueSerializer(serializer); // STRING

                template.setHashKeySerializer(new StringRedisSerializer()); // HSET

                template.setHashValueSerializer(serializer); // HSET

                template.afterPropertiesSet();

                return template;
        }

        @Bean
        public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
                RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(30)) // thời gian sống của cache
                                .serializeKeysWith(
                                                SerializationPair.fromSerializer(new StringRedisSerializer()))
                                .serializeValuesWith(
                                                SerializationPair.fromSerializer(
                                                                new GenericJackson2JsonRedisSerializer()));

                return RedisCacheManager.builder(connectionFactory)
                                .cacheDefaults(config)
                                .build();
        }

}
