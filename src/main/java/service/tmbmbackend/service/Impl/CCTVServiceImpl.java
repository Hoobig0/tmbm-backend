package service.tmbmbackend.service.Impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.util.GeometricShapeFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.tmbmbackend.dto.CCTVMetaResponse;
import service.tmbmbackend.dto.CCTVDataResponse;
import service.tmbmbackend.dto.DataResponse;
import service.tmbmbackend.dto.Response;
import service.tmbmbackend.service.CCTVService;
import service.tmbmbackend.dto.CCTVZoneRequest;
import service.tmbmbackend.entity.CCTV;
import service.tmbmbackend.repository.CCTVRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CCTVServiceImpl implements CCTVService {
    private final CCTVRepository cctvRepository;
    @Override
    public Response getAllCCTVs(CCTVZoneRequest cctvZoneRequest) {
        Page<CCTV> cctvs = cctvRepository.findWithin(
                calculateCircleRadius(cctvZoneRequest.getX(), cctvZoneRequest.getY(), cctvZoneRequest.getRadius()), cctvZoneRequest.getPageRequest());
        List<DataResponse> dataResponses = CCTVDataResponse.toList(
                sortCCTVListByDistance(cctvZoneRequest.getX(), cctvZoneRequest.getY(),
                        cctvs.getContent().stream().collect(Collectors.toList())));
        return new Response(new CCTVMetaResponse(cctvZoneRequest, cctvs.hasPrevious(), cctvs.hasNext()), dataResponses);
    }

    private static List<CCTV> sortCCTVListByDistance(double x, double y, List<CCTV> cctvList) {
        cctvList.sort(Comparator.comparingDouble(c -> calculateDistance(x, y, c.getPoint())));
        return cctvList;
    }

    private static double calculateDistance(double x, double y, Point point) {
        double earthRadius = 6371;

        double lat1 = Math.toRadians(x);
        double lon1 = Math.toRadians(y);
        double lat2 = Math.toRadians(point.getX());
        double lon2 = Math.toRadians(point.getY());

        return earthRadius * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    }

    private Geometry calculateCircleRadius(double x, double y, double radius) {
        double meterToCoordinate = (radius / 100000);

        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setNumPoints(32);
        shapeFactory.setCentre(new Coordinate(x, y));
        shapeFactory.setSize(meterToCoordinate * 2);
        return shapeFactory.createCircle();
    }
}