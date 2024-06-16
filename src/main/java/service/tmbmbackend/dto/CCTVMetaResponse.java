package service.tmbmbackend.dto;

import lombok.Getter;
import service.tmbmbackend.common.Path;

@Getter
public class CCTVMetaResponse implements MetaResponse {

    private String prevUrl;
    private String nextUrl;

    public CCTVMetaResponse(CCTVZoneRequest cctvZoneRequest, int totalCount) {
        int currentPage = cctvZoneRequest.getPageRequest().getPageNumber();
        int pageSize = cctvZoneRequest.getPageRequest().getPageSize();
        int nowCount = currentPage * pageSize;

        if (currentPage != 0) {
            this.prevUrl = createUrl(Path.CCTV_LIST, cctvZoneRequest.getX(),
                    cctvZoneRequest.getY(), currentPage - 1, pageSize);
        }
        if (totalCount > nowCount) {
            this.nextUrl = createUrl(Path.CCTV_LIST, cctvZoneRequest.getX(),
                    cctvZoneRequest.getY(), currentPage + 1, pageSize);
        }
    }
    private String createUrl(String cctvUrl, double x, double y, int page, int size) {
        return String.format(cctvUrl, x, y, page, size);
    }
}