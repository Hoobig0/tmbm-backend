package service.tmbmbackend.dto;

import lombok.Getter;

@Getter
public class CCTVZoneRequest {
    private double x;
    private double y;
    private double radius;

    public CCTVZoneRequest(String x, String y, Integer radius) {
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.radius = radius;
    }
}
