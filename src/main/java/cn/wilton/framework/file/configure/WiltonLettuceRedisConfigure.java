package cn.wilton.framework.file.configure;

import cn.wilton.framework.file.common.service.RedisService;
import cn.wilton.framework.file.common.service.impl.RedisServiceImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 自定义了一个泛型为<String, Object>的RedisTemplate，
 * 指定key序列化策略采用StringRedisSerializer，
 * value序列化策略采用Jackson2JsonRedisSerializer，
 * 其内部采用ObjectMapper
 * @Description
 * @Author: Ranger
 * @Date: 2021/1/15 10:50
 * @Email: wilton.icp@gmail.com
 */
public class WiltonLettuceRedisConfigure {

    @Bean
    @ConditionalOnClass(RedisOperations.class) //只有当项目里存在RedisOperations类的时候（即引入了spring-boot-starter-data-redis依赖的时候），我们自定义的RedisTemplateBean才会被注册到IOC容器中
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用 String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的 key也采用 String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用 jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的 value序列化方式采用 jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    @Bean
    @ConditionalOnBean(name = "redisTemplate")
    public RedisService redisService() {
        return new RedisServiceImpl();
    }
}
