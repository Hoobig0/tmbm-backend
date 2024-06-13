package service.tmbmbackend.dto;

import lombok.Getter;
import service.tmbmbackend.common.Path;

@Getter
public class CCTVMetaResponse implements MetaResponse {

    private String prevUrl;
    private String nextUrl;

    public CCTVMetaResponse(CCTVZoneRequest cctvZoneRequest, boolean hasPrev, boolean hasNext) {
        if (hasPrev) {
            this.prevUrl = String.format(Path.CCTV_LIST,
                    cctvZoneRequest.getX(), cctvZoneRequest.getY(), cctvZoneRequest.getPageRequest().previous().getPageNumber(), cctvZoneRequest.getPageRequest().getPageSize());
        }
        if (hasNext) {
            this.nextUrl = String.format(Path.CCTV_LIST,
                    cctvZoneRequest.getX(), cctvZoneRequest.getY(), cctvZoneRequest.getPageRequest().next().getPageNumber(), cctvZoneRequest.getPageRequest().getPageSize());
        }
    }
}