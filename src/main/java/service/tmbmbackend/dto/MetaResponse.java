package service.tmbmbackend.dto;

import lombok.Getter;

@Getter
public class MetaResponse {
    private String prev;
    private String next;

    public MetaResponse(String prev, String next) {
        this.prev = prev;
        this.next = next;
    }
}
