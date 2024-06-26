package service.tmbmbackend.repository;

import java.util.List;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service.tmbmbackend.entity.CCTV;

@Repository
public interface CCTVRepository extends JpaRepository<CCTV, Long> {

    @Query("SELECT c FROM cctv as c WHERE within(c.point, :radius) = TRUE")
    List<CCTV> findWithin(@Param("radius") Geometry radius, Pageable pageable);

    @Query("SELECT count(*) FROM cctv as c WHERE within(c.point, :radius) = TRUE")
    int findTotalCCTVCount(@Param("radius") Geometry radius);
}