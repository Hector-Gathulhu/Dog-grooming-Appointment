package com.grooming.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "dog")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String age;

    @ManyToOne
    @JoinColumn(name="owner_id")
    @JsonIgnoreProperties({"dogs","name", "phone", "email", "dogAppointments"})
    private Owner owner;
    @OneToMany(mappedBy = "dog",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private List<DogAppointment> dogAppointments;

}
