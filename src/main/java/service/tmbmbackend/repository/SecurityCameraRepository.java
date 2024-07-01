package service.tmbmbackend.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Repository;
import service.tmbmbackend.entity.SecurityCamera;

@Repository
@RequiredArgsConstructor
public class SecurityCameraRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public void save(SecurityCamera camera) {
        redisTemplate.opsForGeo().add("security_cameras", new Point(camera.getX(), camera.getY()), camera.getId());
        redisTemplate.opsForHash().put("camera_locations", camera.getId(), camera);
    }
}