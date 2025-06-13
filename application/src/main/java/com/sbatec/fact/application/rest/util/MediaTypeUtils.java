package com.sbatec.fact.application.rest.util;


import jakarta.servlet.ServletContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;

import java.net.MalformedURLException;
import java.nio.file.Path;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class MediaTypeUtils {

    private MediaTypeUtils() {

    }

    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {

        String mineType = servletContext.getMimeType(fileName);
        try {
            return MediaType.parseMediaType(mineType);
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public static Resource loadFileAsResource(Path filePath) {

        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());

        } catch (MalformedURLException ex) {
            log.debug("File not found : " + ex.getMessage());
        }
        return resource;

    }

}
