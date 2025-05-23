package com.aliateck.fact.domaine.business.object;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tva {

    Long id;
    String datePayment;
    Float montantPayment;
    String siret;
    String exercise;
    String monthPayment;
}
