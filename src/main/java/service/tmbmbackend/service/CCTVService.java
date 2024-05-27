package service.tmbmbackend.service;

import java.util.List;
import service.tmbmbackend.dto.CCTVZoneRequest;
import service.tmbmbackend.dto.DataResponse;

public interface CCTVService {
    List<DataResponse> getAllCCTVs(CCTVZoneRequest userLocation, Integer page, Integer size);
}