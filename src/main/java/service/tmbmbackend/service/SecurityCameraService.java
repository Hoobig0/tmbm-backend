package service.tmbmbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.domain.geo.Metrics;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityCameraService {

    private final RedisTemplate<String, Object> redisTemplate;

    public boolean isCameraWithinRadius(double x, double y, double radius) {
        GeoResults<RedisGeoCommands.GeoLocation<Object>> results = redisTemplate.opsForGeo()
            .radius("security_cameras", new Circle(new org.springframework.data.geo.Point(x, y), new Distance(radius, Metrics.KILOMETERS)));
        return results != null && !results.getContent().isEmpty();
    }
}
