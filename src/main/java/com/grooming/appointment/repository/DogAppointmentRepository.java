package com.grooming.appointment.repository;


import com.grooming.appointment.model.DogAppointment;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogAppointmentRepository extends JpaRepository<DogAppointment,Long> {

    //JPQL(Java Persistence Query Language)
    @Query("SELECT d FROM appointment d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Optional<List<DogAppointment>> findByNameContaining(String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM appointment d WHERE d.id = :id")
    void deleteByIdOption(Long id);
}
