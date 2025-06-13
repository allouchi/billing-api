package com.sbatec.fact.infrastructure.repository.edition.commun;

import java.io.File;
import java.io.IOException;



public interface IExcelGenerator {

    public File generate() throws IOException;

}
