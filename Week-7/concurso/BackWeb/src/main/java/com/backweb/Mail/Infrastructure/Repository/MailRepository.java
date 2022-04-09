package com.backweb.Mail.Infrastructure.Repository;

import com.backweb.Mail.Domain.Mail;
import com.backweb.Trip.Domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MailRepository extends JpaRepository<Mail, UUID> {
    @Query(value = "SELECT * FROM MAIL m " +
            "WHERE m.DEPARTURE = :departure AND m.ARRIVAL = :arrival", nativeQuery = true)
    List<Mail> findByDepartureAndArrival(@Param("departure") String departure, @Param("arrival") String arrival);

    @Query(value = "SELECT * FROM MAIL m " +
            "WHERE m.DATE LIKE CONCAT(:date, '%')", nativeQuery = true)
    List<Mail> findByLocalDate(@Param("date") String date);
}
