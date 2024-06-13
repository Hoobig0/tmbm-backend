package service.tmbmbackend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import service.tmbmbackend.entity.CCTV;

@DataJpaTest
class CCTVRepositoryTest {
    @Autowired
    CCTVRepository cctvRepository;

    @BeforeEach
    void setUp() {
        cctvRepository.deleteAll();
    }

    @DisplayName("CCTV가 주어진 반경 내에 있는 경우, CCTV 데이터를 반환한다.")
    @Test
    void findWithin() {
        //given
        Polygon radius = createRadius();

        //when
        CCTV savedCctv = new CCTV(1L, 7, 5);
        cctvRepository.save(savedCctv);

        //then
        List<CCTV> cctvList = cctvRepository.findWithin(radius, PageRequest.of(0, 10));
        assertThat(cctvList.stream().collect(Collectors.toList()).get(0).getId()).isEqualTo(savedCctv.getId());
    }

    @DisplayName("CCTV가 주어진 반경 내에 없는 경우, Null 값을 반환한다.")
    @Test
    void cannotFindWithin() {
        //given
        Polygon radius = createRadius();

        //when
        CCTV savedCctv = new CCTV(1L, 11, 11);
        cctvRepository.save(savedCctv);

        //then
        List<CCTV> cctvList = cctvRepository.findWithin(radius, PageRequest.of(0, 10));
        List<CCTV> actualResult = cctvList.stream().collect(Collectors.toList());

        List<CCTV> expectedResult = new ArrayList<>();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Polygon createRadius() {
        Coordinate[] coordinates = new Coordinate[5];
        coordinates[0] = new Coordinate(0, 0);
        coordinates[1] = new Coordinate(0, 10);
        coordinates[2] = new Coordinate(10, 10);
        coordinates[3] = new Coordinate(10, 0);
        coordinates[4] = new Coordinate(0, 0);

        GeometryFactory geometryFactory = new GeometryFactory();
        return geometryFactory.createPolygon(coordinates);
    }
}