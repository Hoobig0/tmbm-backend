package service.tmbmbackend.dto;

import lombok.Getter;

@Getter
public class CCTVMetaResponse implements MetaResponse {

    private String prevUrl;
    private String nextUrl;

    public CCTVMetaResponse(CCTVZoneRequest cctvZoneRequest, boolean hasPrev, boolean hasNext) {
        String baseUrl = "/api/v1/cctv/list?userX=%s&userY=%s&page=%d&size=%d";
        if (hasPrev) {
            this.prevUrl = String.format(baseUrl,
                    cctvZoneRequest.getX(), cctvZoneRequest.getY(), cctvZoneRequest.getPageRequest().previous().getPageNumber(), cctvZoneRequest.getPageRequest().getPageSize());
        }
        if (hasNext) {
            this.nextUrl = String.format(baseUrl,
                    cctvZoneRequest.getX(), cctvZoneRequest.getY(), cctvZoneRequest.getPageRequest().next().getPageNumber(), cctvZoneRequest.getPageRequest().getPageSize());
        }
    }
}