package service.tmbmbackend.cctv;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

@Entity(name = "cctv")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CCTV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @Column
    Point point;

    public CCTV(Long id, double x, double y) {
        GeometryFactory geometryFactory = new GeometryFactory();
        this.point = geometryFactory.createPoint(new Coordinate(x, y));
        this.id = id;
    }
}