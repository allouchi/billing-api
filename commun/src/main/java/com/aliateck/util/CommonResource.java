package com.aliateck.util;

/**
 * Common resource constants
 *
 * @author X205786
 * @since 11/11/2020
 */
public interface CommonResource {

    // All http operations managed
    interface RequestType {
        String POST = "POST";
        String GET = "GET";
        String PUT = "PUT";
        String DELETE = "DELETE";
    }

    // Path for all exposed resources
    interface Resource {
        String CLIENTS = "/api/clients";
        String CONSULTANTS = "/api/consultants";
        String COMPANIES = "/api/companies";
        String FACTURES = "/api/factures";
        String EDITIONS = "/api/editions";
        String PRESTATIONS = "/api/prestations";
        String TVAS = "/api/tvas";
        String TVASINFO = "/tvasInfo";
        String EXERCISES = "/api/tvas";
        String USERS = "/api/users";
        String BATCHS = "/api/batchs";
        String ROLES = "/api/roles";
        String API = "/api";

    }

    // Http status
    interface Status {
        String OK = "200";
        String UNAUTHORIZED = "401";
        String FORBIDDEN = "403";
    }
}
