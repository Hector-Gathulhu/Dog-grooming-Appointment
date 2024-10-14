package com.grooming.appointment.dto;


import com.grooming.appointment.enums.BathType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DogAppointmentDto(
        @NotBlank(message = "Name")
        @Size(min = 3)
        String name,
        @NotBlank(message = "Phone")
        @Size(min = 10, max = 10)
        @Pattern(regexp = "\\d{10}")
        String ownerPhone,
        @NotNull(message = "Bath type must be in UPPERCASE")
        BathType bathType,
        @NotNull(message = "Owner ID is required")
        Long ownerId,
        @NotNull
        Long dogId


) {
}
