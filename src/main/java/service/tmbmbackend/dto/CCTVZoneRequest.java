package service.tmbmbackend.dto;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;

@Getter
public class CCTVZoneRequest {
    private double x;
    private double y;
    private double radius;
    private PageRequest pageRequest;

    public CCTVZoneRequest(String x, String y, Integer radius, Integer page, Integer size) {
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.radius = radius;
        this.pageRequest = PageRequest.of(page, size);
    }
}
