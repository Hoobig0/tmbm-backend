package service.tmbmbackend.service.Impl;

import static service.tmbmbackend.entity.CCTV.calculateCircleRadius;
import static service.tmbmbackend.entity.CCTV.sortCCTVListByDistance;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
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
                calculateCircleRadius(cctvZoneRequest.getX(), cctvZoneRequest.getY(),
                        cctvZoneRequest.getRadius()), cctvZoneRequest.getPageRequest());

        CCTVMetaResponse cctvMetaResponse = new CCTVMetaResponse(cctvZoneRequest, cctvs.hasPrevious(), cctvs.hasNext());
        List<DataResponse> cctvDataResponses = CCTVDataResponse.toList(
                sortCCTVListByDistance(cctvZoneRequest.getX(), cctvZoneRequest.getY(),
                        cctvs.getContent().stream().collect(Collectors.toList())));
        return new Response(cctvMetaResponse, cctvDataResponses);
    }
}