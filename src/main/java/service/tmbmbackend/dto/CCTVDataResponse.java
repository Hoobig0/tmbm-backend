package service.tmbmbackend.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.locationtech.jts.geom.Point;
import service.tmbmbackend.entity.CCTV;

@Getter
public class CCTVDataResponse implements DataResponse {

    private Long cctvId;
    private String x;
    private String y;

    public CCTVDataResponse(Long cctvId, Point point) {
        this.cctvId = cctvId;
        this.x = Double.toString(point.getX());
        this.y = Double.toString(point.getY());
    }

    public static CCTVDataResponse from(CCTV cctv) {
        return new CCTVDataResponse(cctv.getId(), cctv.getPoint());
    }

    public static List<DataResponse> toList(List<CCTV> cctvs) {
        return cctvs.stream().map(CCTVDataResponse::from).collect(Collectors.toList());
    }
}
