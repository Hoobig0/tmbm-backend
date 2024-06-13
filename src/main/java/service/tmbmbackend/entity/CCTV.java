package service.tmbmbackend.entity;

import jakarta.persistence.*;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.util.GeometricShapeFactory;

@Entity(name = "cctv")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CCTV {
    public static final double EARTHRADIUS = 6371;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    Point point;

    public CCTV(Long id, double x, double y) {
        this.point = createPoint(x, y);
        this.id = id;
    }

    private Point createPoint(double x, double y) {
        GeometryFactory geometryFactory = new GeometryFactory();
        return geometryFactory.createPoint(new Coordinate(x, y));
    }

    public static List<CCTV> sortCCTVListByDistance(double x, double y, List<CCTV> cctvList) {
        cctvList.sort(Comparator.comparingDouble(c -> calculateDistance(x, y, c.getPoint())));
        return cctvList;
    }

    private static double calculateDistance(double x, double y, Point point) {
        double lat1 = Math.toRadians(x);
        double lon1 = Math.toRadians(y);
        double lat2 = Math.toRadians(point.getX());
        double lon2 = Math.toRadians(point.getY());

        return EARTHRADIUS * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    }
    public static Geometry calculateCircleRadius(double x, double y, double radius) {
        double meterToCoordinate = (radius / 100000);

        GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
        shapeFactory.setNumPoints(32);
        shapeFactory.setCentre(new Coordinate(x, y));
        shapeFactory.setSize(meterToCoordinate * 2);
        return shapeFactory.createCircle();
    }
}