package service.tmbmbackend.service;

import java.util.List;
import service.tmbmbackend.dto.CCTVZoneRequest;
import service.tmbmbackend.dto.DataResponse;
import service.tmbmbackend.dto.Response;

public interface CCTVService {
    Response getAllCCTVs(CCTVZoneRequest userLocation);
}