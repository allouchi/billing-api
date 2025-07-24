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
public class TvaInfo {
    BigDecimal totalTvaPaye;
    BigDecimal totalTvaRestant;
    BigDecimal totalTva;
    BigDecimal totalTvaNet;
    BigDecimal totalTTC;
    BigDecimal totalCAHorsTaxe;
}
