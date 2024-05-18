package service.tmbmbackend.cctv;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Entity(name = "cctv")
@Getter
@NoArgsConstructor
public class CCTV {
    @Id
    Long id;
    @Column
    Point point;

    @Builder
    public CCTV(Long id, Point point) {
        this.id = id;
        this.point = point;
    }
}