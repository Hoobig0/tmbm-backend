package service.tmbmbackend.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class SecurityCamera implements Serializable {

    private String id;

    private double x;

    private double y;

    public SecurityCamera() {
    }

    public SecurityCamera(String id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}