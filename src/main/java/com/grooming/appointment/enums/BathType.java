package com.grooming.appointment.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BathType {
    BASIC,
    DELUXE,
    PREMIUM;

    @JsonCreator
    public static BathType fromString(String enume) {
        try {
            return BathType.valueOf(enume.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid bath type. Select Basic, Deluxe or Premium. Error: "+enume );
        }
    }
}
