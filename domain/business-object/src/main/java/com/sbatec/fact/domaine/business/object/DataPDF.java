package com.sbatec.fact.domaine.business.object;

import java.nio.file.Path;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter 
@Setter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataPDF {
	
	String fileName;
	Path filePath;
	byte[] fileContent;

}
