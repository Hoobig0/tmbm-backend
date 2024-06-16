package service.tmbmbackend.service;

import service.tmbmbackend.dto.CCTVZoneRequest;
import service.tmbmbackend.dto.Response;

public interface CCTVService {
    Response getAllCCTVs(CCTVZoneRequest userLocation);
}