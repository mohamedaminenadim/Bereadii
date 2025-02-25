package com.epsi.workshop.bereadii.entity;

import com.epsi.workshop.bereadii.utils.ProfessionalAvailabilities;
import com.epsi.workshop.bereadii.utils.ProfessionalService;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfessionalUserEntity extends UserEntity {
    private ProfessionalService professionalService;
    private ProfessionalAvailabilities professionalAvailabilities;
}
