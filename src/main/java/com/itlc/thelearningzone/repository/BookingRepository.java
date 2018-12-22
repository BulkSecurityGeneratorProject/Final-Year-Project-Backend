package com.itlc.thelearningzone.repository;

import com.itlc.thelearningzone.domain.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Booking entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "select distinct booking from Booking booking left join fetch booking.userInfos",
        countQuery = "select count(distinct booking) from Booking booking")
    Page<Booking> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct booking from Booking booking left join fetch booking.userInfos")
    List<Booking> findAllWithEagerRelationships();

    @Query("select booking from Booking booking left join fetch booking.userInfos where booking.id =:id")
    Optional<Booking> findOneWithEagerRelationships(@Param("id") Long id);
    
    @Query(value = "select distinct booking from Booking booking left join fetch booking.userInfos where booking.startTime between :startTime and :endTime",
    		countQuery = "select count(distinct booking) from Booking booking where startTime > :startTime and startTime < :endTime ")
    Page<Booking>findAllInTimeFrame(Pageable pageable, @Param("startTime") Instant startTime, @Param("endTime") Instant endTime);

}
