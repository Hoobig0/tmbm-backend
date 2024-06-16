package service.tmbmbackend.dto;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;

@Getter
public class CCTVZoneRequest {
    private static final String INVALID_LOCATION_MESSAGE = "사용자 위치 데이터가 잘못되었습니다.";
    private double x;
    private double y;
    private double radius;
    private PageRequest pageRequest;

    public CCTVZoneRequest(String x, String y, Integer radius, Integer page, Integer size) {
        this.x = parseValue(x, INVALID_LOCATION_MESSAGE);
        this.y = parseValue(y, INVALID_LOCATION_MESSAGE);
        this.radius = radius;
        this.pageRequest = PageRequest.of(page, size);
    }
    private double parseValue(String value, String errorMessage) {
        if (isVaild(value)) {
            return Double.parseDouble(value);
        } else {
            throw new IllegalArgumentException(errorMessage);
        }
    }
    private boolean isVaild(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }
}
