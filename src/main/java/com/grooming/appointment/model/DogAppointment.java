package com.grooming.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grooming.appointment.enums.BathType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "appointment")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DogAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length=10)
    private String ownerPhone;
    @Enumerated(EnumType.STRING)
    private BathType bathType;

    //Add ownerID
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnoreProperties({"dogs","name", "phone", "email", "dogAppointments"})
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "dog_id")
    @JsonIgnore
    private Dog dog;
}
