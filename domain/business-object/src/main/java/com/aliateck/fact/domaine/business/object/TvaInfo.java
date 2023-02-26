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
public class TvaInfo {

    Double totalTvaPaye;
    Double totalTvaRestant;
    Double totalHT;
}
