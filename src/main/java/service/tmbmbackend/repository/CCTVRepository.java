package service.tmbmbackend.repository;

import org.locationtech.jts.geom.Geometry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import service.tmbmbackend.entity.CCTV;

@Repository
public interface CCTVRepository extends JpaRepository<CCTV, Long> {

    @Query("SELECT c FROM cctv as c WHERE within(c.point, :radius) = TRUE")
    Page<CCTV> findWithin(@Param("radius") Geometry radius, Pageable pageable);
}