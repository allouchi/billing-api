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
    String CLIENTS = "/clients";
    String CONSULTANTS = "/consultants";
    String COMPANIES = "/companies";
    String FACTURES = "/factures";
    String EDITIONS = "/editions";
    String PRESTATIONS = "/prestations";
    String TVAS = "/tvas";  
    String USERS = "/users";
    String BATCHS = "/batchs";
    String ROLES = "/roles";
    String LOGIN = "/login";
   
  }

  // Http status
  interface Status {
    String OK = "200";
    String UNAUTHORIZED = "401";
    String FORBIDDEN = "403";
  }
}
