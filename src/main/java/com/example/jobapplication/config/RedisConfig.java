package com.example.jobapplication.config;

import java.time.Duration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis configuration class. Contains beans to support Redis as a cache. Please take a look at
 * application.properties files to see redis connection values.
 */
@EnableAutoConfiguration
public class RedisConfig {

  /**
   * Redis template bean.
   *
   * @param connectionFactory connectionFactory
   * @return RedisTemplate
   */
  @Bean
  public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<?, ?> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);

    return template;
  }

  /**
   * Redis Cache Manager class.
   *
   * @param connectionFactory connectionFactory
   * @return RedisCacheManager
   */
  @Bean
  public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
        .prefixCacheNameWith(this.getClass().getPackageName() + ".")
        .entryTtl(Duration.ofMinutes(5))
        .disableCachingNullValues();

    return RedisCacheManager.builder(connectionFactory)
        .cacheDefaults(config)
        .build();
  }
}
