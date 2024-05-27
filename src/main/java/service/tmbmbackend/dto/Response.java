package service.tmbmbackend.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class Response {
    private MetaResponse meta;
    private List<DataResponse> data;

    public Response(MetaResponse meta, List<DataResponse> data) {
        this.meta = meta;
        this.data = data;
    }
}