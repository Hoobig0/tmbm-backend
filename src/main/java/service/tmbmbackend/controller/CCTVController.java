package service.tmbmbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
        return ResponseEntity.ok(cctvService.getAllCCTVs(new CCTVZoneRequest(x, y, radius, page, size)));
    }
}