package com.grooming.appointment.service;


import com.grooming.appointment.dto.DogAppointmentDto;
import com.grooming.appointment.model.Dog;
import com.grooming.appointment.model.DogAppointment;
import com.grooming.appointment.model.Owner;
import com.grooming.appointment.repository.DogAppointmentRepository;
import com.grooming.appointment.repository.DogRepository;
import com.grooming.appointment.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogAppointmentService {

    @Autowired
    private DogAppointmentRepository dogAppointmentRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private DogRepository dogRepository;


    public DogAppointment createAppointment(DogAppointmentDto dogAppointmentDto) {

        Owner owner = ownerRepository.findById(dogAppointmentDto.ownerId())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        Dog dog = dogRepository.findById(dogAppointmentDto.dogId())
                .orElseThrow(() -> new IllegalArgumentException("Dog not found"));

        if (!owner.getPhone().equals(dogAppointmentDto.ownerPhone())) {
            throw new IllegalArgumentException("The phone provied doesn't match with the owner's information!");
        }

        if (!dog.getName().equals(dogAppointmentDto.name())) {
            throw new IllegalArgumentException("The name provied doesn't match with the owner's information!");
        }


        DogAppointment dogAppointment = new DogAppointment();
        dogAppointment.setName(dogAppointmentDto.name());
        dogAppointment.setOwnerPhone(dogAppointmentDto.ownerPhone());
        dogAppointment.setBathType(dogAppointmentDto.bathType());
        dogAppointment.setOwner(owner);
        dogAppointment.setDog(dog);
        return dogAppointmentRepository.save(dogAppointment);
    }

    public List<DogAppointment> retriveAppointment() {
        return dogAppointmentRepository.findAll();
    }

    public Optional<List<DogAppointment>> getAppointmentByName(String name) {
        return dogAppointmentRepository.findByNameContaining(name);
    }

    public DogAppointment updateAppointment(Long id, DogAppointmentDto dogUpdateDto) {
        DogAppointment dogAppointment = dogAppointmentRepository.findById(id).orElseThrow(RuntimeException::new);
        Owner owner = ownerRepository.findById(dogUpdateDto.ownerId())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        if (!owner.getPhone().equals(dogUpdateDto.ownerPhone())) {
            throw new IllegalArgumentException("The phone provied doesn't match with the owner's information!");
        }

        dogAppointment.setName(dogUpdateDto.name());
        dogAppointment.setOwnerPhone(dogUpdateDto.ownerPhone());
        dogAppointment.setBathType(dogUpdateDto.bathType());
        dogAppointment.setOwner(dogAppointment.getOwner());

        return dogAppointmentRepository.save(dogAppointment);
    }


    public DogAppointment deleteAppointment(Long id) {
        DogAppointment dogAppointment = dogAppointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The appointment doesn't exist, ID: " + id));
        dogAppointmentRepository.deleteByIdOption(dogAppointment.getId());

        return dogAppointment;
    }

}
