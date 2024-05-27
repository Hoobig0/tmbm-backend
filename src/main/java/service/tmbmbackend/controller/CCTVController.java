package service.tmbmbackend.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.tmbmbackend.dto.DataResponse;
import service.tmbmbackend.dto.MetaResponse;
import service.tmbmbackend.dto.CCTVZoneRequest;
import service.tmbmbackend.service.CCTVService;
import service.tmbmbackend.dto.Response;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cctv")
public class CCTVController {
    private final CCTVService cctvService;
    @GetMapping("/list")
    public ResponseEntity<Response> getAllCCTVs(
            @RequestParam("userX") String x, @RequestParam("userY") String y,
            @RequestParam(required = false, defaultValue = "500") Integer radius,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size){
        CCTVZoneRequest cctvZoneRequest = new CCTVZoneRequest(x, y, radius);
        List<DataResponse> cctvs = cctvService.getAllCCTVs(cctvZoneRequest, page, size);

        String prevPageUrl = String.format("/api/v1/cctv/list?userX=%s&userY=%s&page=%d&size=%d", x, y, page - 1, size);
        String nextPageUrl = String.format("/api/v1/cctv/list?userX=%s&userY=%s&page=%d&size=%d", x, y, page + 1, size);
        MetaResponse meta = new MetaResponse(prevPageUrl, nextPageUrl);
        Response response = new Response(meta, cctvs);
        return ResponseEntity.ok(response);
    }
}