package service.tmbmbackend.service.Impl;

import static service.tmbmbackend.entity.CCTV.calculateCircleRadius;
import static service.tmbmbackend.entity.CCTV.sortCCTVListByDistance;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Geometry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.tmbmbackend.dto.CCTVMetaResponse;
import service.tmbmbackend.dto.CCTVDataResponse;
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
        Geometry circleRadius = calculateCircleRadius(cctvZoneRequest.getX(),
                cctvZoneRequest.getY(), cctvZoneRequest.getRadius());

        List<CCTV> cctvs = cctvRepository.findWithin(circleRadius, cctvZoneRequest.getPageRequest());
        int totalCount = cctvRepository.findTotalCCTVCount(circleRadius);

        CCTVMetaResponse cctvMetaResponse = new CCTVMetaResponse(cctvZoneRequest, totalCount);
        List<CCTVDataResponse> cctvDataResponse = CCTVDataResponse.toList(
                sortCCTVListByDistance(cctvZoneRequest.getX(), cctvZoneRequest.getY(), cctvs));
        return new Response(cctvMetaResponse, cctvDataResponse);
    }
}