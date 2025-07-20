package com.sbatec.fact.domaine.business.object;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

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
    BigDecimal montantPayment;
    String siret;
    String exercise;
    String monthPayment;
}
