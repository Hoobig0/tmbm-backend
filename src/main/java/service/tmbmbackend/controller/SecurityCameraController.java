package service.tmbmbackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.tmbmbackend.service.SecurityCameraService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/security-cameras")
public class SecurityCameraController {

    private final SecurityCameraService securityCameraService;

    @GetMapping("/exists")
    public boolean isCameraWithinRadius(@RequestParam double x, @RequestParam double y, @RequestParam double radius) {
        return securityCameraService.isCameraWithinRadius(x, y, radius);
    }
}
