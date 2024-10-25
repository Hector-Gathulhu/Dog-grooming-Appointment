package com.grooming.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grooming.appointment.enums.BathType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity(name = "appointment")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DogAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnoreProperties({"dogs", "name", "phone", "email", "dogAppointments"})
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "dog_id")
    @JsonIgnoreProperties({"owner","dogAppointments"})
    private Dog dog;

    @Enumerated(EnumType.STRING)
    private BathType bathType;

    @JoinColumn(name = "date_appoint")
    private LocalDateTime dateTime;
}
