package com.epsi.workshop.bereadii.utils;

import lombok.Getter;

@Getter
public enum AppointmentStatus {
    CONFIRMED("Confirmé"),
    CANCELED("Annulé"),
    PAST("Terminé");

    private AppointmentStatus(String description) {}
    public String getAppointmentStatusByKey(String status) throws EnumConstantNotPresentException {
        for (AppointmentStatus s : AppointmentStatus.values()) {
            if (s.name() == status) return s.name();
        }
        throw new EnumConstantNotPresentException(AppointmentStatus.class, status);
    }
}
