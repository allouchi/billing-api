package com.accorhotels.blelectronique.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;


import org.json.JSONArray;
import org.json.JSONObject;

import com.accorhotels.blelectronique.util.Tools;
import com.aliateck.fact.application.rest.controllers.tva.TvaController;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @author MALIANE
 *
 */
@Slf4j
public class HttpConnexionBuilder {

	
	private static final String HTTP_REQUEST_RESPONSE = "HTTP Request response statut : ";

	/**
	 * Constructeur
	 */
	private HttpConnexionBuilder() {
	}

	/**
	 * Retourne une connection vers le serveur jira ou snow
	 * 
	 * @param urlServer
	 * @return connection
	 * @throws IOException
	 */
	public static HttpsURLConnection buildHttpConnection(String login, String password, String token, String urlServer,
			String urlProxy, String proxyPort, boolean isJira, String httpMethod) throws IOException {

		HttpsURLConnection connection = Tools.buiLdProxyConnexion(urlServer, urlProxy, proxyPort, true);

		if (connection != null) {
			if (isJira) {
				connection.setRequestMethod(httpMethod);				
			} else {
				connection.setRequestMethod(httpMethod);				
			}

			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");
			
			String userAccessToken = Tools.getAuthorizationString(login, password, token, isJira);
			connection.setRequestProperty("Authorization", "Basic " + userAccessToken);
		}
		return connection;
	}

	/**
	 * Méthode permettant d'envoyer la requete au serveur
	 * 
	 * @param connection
	 * @param payload
	 * @return true si tout va bien
	 */
	public static boolean sendHttpRequest(HttpsURLConnection connection, String payload) {
		boolean resultat = false;

		try {

			connection.setDoOutput(true);
			connection.getOutputStream().write(payload.getBytes());

			int status = connection.getResponseCode();			
			
			switch (status) {
			case 200:
				resultat = true;
				log.debug(HTTP_REQUEST_RESPONSE + status + " OK Issue Updated");
				break;
			case 201:
				log.debug(HTTP_REQUEST_RESPONSE + status + " OK Issue Updated");
				resultat = true;
				break;
			case 204:				
				log.debug(HTTP_REQUEST_RESPONSE + status + " OK Issue Updated");
				resultat = true;
				break;
			case 400:
				log.debug(HTTP_REQUEST_RESPONSE + status + " Bad Request");
				break;
			case 401:
				log.debug(HTTP_REQUEST_RESPONSE + status + " Unauthorized");
				break;
			case 404:
				log.debug(HTTP_REQUEST_RESPONSE + status + " Ticket Not found");
				break;
			case 500:
				log.debug(HTTP_REQUEST_RESPONSE + status);
				break;
			case 405:
				log.debug(HTTP_REQUEST_RESPONSE + status + " Method Not Allowed");
				break;
			case 505:
				log.debug(HTTP_REQUEST_RESPONSE + status + " HTTP Version not supported");
				break;
			default:
				log.debug(HTTP_REQUEST_RESPONSE + status + " Http request error");
			}

		} catch (Exception e) {
			log.error("Http request error : " + e.getMessage());
			resultat = false;
		} finally {
			connection.disconnect();
		}

		return resultat;
	}

	/**
	 * Reccherche du Sys_ID
	 * 
	 * @param connection
	 * @return ID
	 */
	public static String searchSysIdInHttpGetRequest(HttpsURLConnection connection) {
		String resultat = null;
		StringBuilder json = new StringBuilder();
		String line = "";
		BufferedReader reader = null;

		try {

			connection.setDoOutput(true);
			connection.connect();
			int status = connection.getResponseCode();

			if (status == 200) {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					json.append(line);
				}				
				
				//log.debug("Json response  :  " + json);	
				
				JSONObject objet = new JSONObject(json.toString());				
				JSONArray array = objet.getJSONArray("result");
				if(array != null && array.length() > 0) {
					for(int i = 0 ; i < array.length() ; i++){
						resultat = array.getJSONObject(i).getString("sys_id");
						if(resultat != null) {
							break;
						}
					}
				}								
								
			} else {
				log.debug("Response server status  :  " + status);
			}

		} catch (Exception e) {
			log.error("Cannot parse Json Response : " + e.getMessage());

		} finally {
			connection.disconnect();
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					log.error("Cannot close flux : " + e.getMessage());
				}
			}
		}
		return resultat;
	}
}
