package com.grooming.appointment.dto;


import com.grooming.appointment.enums.BathType;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record DogAppointmentDto(
        @NotNull
        Long dogId,
        @NotNull(message = "Owner ID is required")
        Long ownerId,
        @NotNull(message = "Bath type must be in UPPERCASE")
        BathType bathType,
        @NotNull
        @DateTimeFormat
        LocalDateTime dateTime
) {
}
