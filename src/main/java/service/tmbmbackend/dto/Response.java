package service.tmbmbackend.dto;

import java.util.List;
import lombok.Getter;
import service.tmbmbackend.entity.CCTV;

@Getter
public class Response {
    private MetaResponse meta;
    private List<CCTVDataResponse> data;

    public Response(MetaResponse meta, List<CCTVDataResponse> data) {
        this.meta = meta;
        this.data = data;
    }
}