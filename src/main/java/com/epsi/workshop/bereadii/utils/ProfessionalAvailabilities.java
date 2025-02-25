package com.epsi.workshop.bereadii.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProfessionalAvailabilities {
    private Date date;
    private List<LocalDateTime> times;
}
