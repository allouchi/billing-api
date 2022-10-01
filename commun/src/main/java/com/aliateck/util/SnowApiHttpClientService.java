package com.accorhotels.blelectronique.webservices;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.accorhotels.blelectronique.database.bean.Bl;
import com.accorhotels.blelectronique.database.bean.BlAnomalie;
import com.accorhotels.blelectronique.database.bean.Etat;
import com.accorhotels.blelectronique.presentation.form.BlForm;
import com.accorhotels.blelectronique.services.interfaces.IAppConfigService;
import com.accorhotels.blelectronique.services.interfaces.IBlService;
import com.accorhotels.blelectronique.util.Const;
import com.accorhotels.blelectronique.util.Tools;

/**
 * 
 * @author MALIANE
 *
 */
public class SnowApiHttpClientService {

	private static final Logger logger = Logger.getLogger(SnowApiHttpClientService.class);

	private String userLogin;
	private String password;
	private String urlSnow;
	private String urlProxy;
	private String portProxy;
	private IBlService blService;
	private IAppConfigService appConfigService;
	private HttpServletRequest request;

	public SnowApiHttpClientService(IBlService blService, IAppConfigService appConfigService,
			HttpServletRequest request) {
		this.blService = blService;
		this.appConfigService = appConfigService;
		this.request = request;
		setUserInfo();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUrlSnow() {
		return urlSnow;
	}

	public void setUrlSnow(String urlSnow) {
		this.urlSnow = urlSnow;
	}

	public String getUrlProxy() {
		return urlProxy;
	}

	public void setUrlProxy(String urlProxy) {
		this.urlProxy = urlProxy;
	}

	public String getPortProxy() {
		return portProxy;
	}

	public void setPortProxy(String portProxy) {
		this.portProxy = portProxy;
	}

	public IBlService getBlService() {
		return blService;
	}

	public void setBlService(IBlService blService) {
		this.blService = blService;
	}

	public IAppConfigService getAppConfigService() {
		return appConfigService;
	}

	public void setAppConfigService(IAppConfigService appConfigService) {
		this.appConfigService = appConfigService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * Set les infos de l'utilisateur
	 * 
	 */
	private void setUserInfo() {
		String machineProd = null;
		String machineRecette = null;
		String serveurAdresse = null;
		String proxyExternePort = null;
		String blSnowUserLogin = null;
		String blSnowUserPassword = null;
		String urlSnowServer = null;
		String proxyExterneServer = null;

		Const.ENVIRONNEMENT env = Const.ENVIRONNEMENT.DEV;

		if (appConfigService.getConfig(Const.BL_APP_CONFIG_MACHINE_PROD) != null) {
			machineProd = appConfigService.getConfig(Const.BL_APP_CONFIG_MACHINE_PROD).getCodeLibConfig();
		}

		if (appConfigService.getConfig(Const.BL_APP_CONFIG_MACHINE_REC) != null) {
			machineRecette = appConfigService.getConfig(Const.BL_APP_CONFIG_MACHINE_REC).getCodeLibConfig();
		}

		serveurAdresse = "http://" + request.getServerName();

		if (machineProd != null && serveurAdresse.equalsIgnoreCase(machineProd)) {
			env = Const.ENVIRONNEMENT.PROD;
			logger.debug("Environnement de Prod : " + request.getServerName());
		} else if (machineRecette != null && serveurAdresse.equalsIgnoreCase(machineRecette)) {
			env = Const.ENVIRONNEMENT.REC;
			logger.debug("Environnement de Recette : " + serveurAdresse);
		} else {
			logger.debug("Environnement de Dev : " + serveurAdresse);
		}

		if(appConfigService.getConfig(Const.BL_SNOW_USER_LOGIN + "_" + env.getSuffixe()) != null) {
			blSnowUserLogin = appConfigService.getConfig(Const.BL_SNOW_USER_LOGIN + "_" + env.getSuffixe())
					.getCodeLibConfig();
		}
		
		if(appConfigService.getConfig(Const.BL_SNOW_USER_PASSWORD + "_" + env.getSuffixe()) != null) {
			blSnowUserPassword = appConfigService.getConfig(Const.BL_SNOW_USER_PASSWORD + "_" + env.getSuffixe())
					.getCodeLibConfig();	
		}
		
        if(appConfigService.getConfig(Const.BL_URL_SNOW_SERVER + "_" + env.getSuffixe()) != null) {
        	 urlSnowServer = appConfigService.getConfig(Const.BL_URL_SNOW_SERVER + "_" + env.getSuffixe())
    				.getCodeLibConfig();
        }
		
        if(appConfigService.getConfig(Const.BL_URL_PROXY_EXTERNE + "_" + env.getSuffixe()) != null) {
        	 proxyExterneServer = appConfigService.getConfig(Const.BL_URL_PROXY_EXTERNE + "_" + env.getSuffixe())
    				.getCodeLibConfig();
        }
         
        if(appConfigService.getConfig(Const.BL_PORT_PROXY_EXTERNE + "_" + env.getSuffixe()) != null) {
        	 proxyExternePort = appConfigService.getConfig(Const.BL_PORT_PROXY_EXTERNE + "_" + env.getSuffixe())
    				.getCodeLibConfig();
        }		

		setUserLogin(blSnowUserLogin);
		setPassword(blSnowUserPassword);
		setUrlSnow(urlSnowServer);
		setUrlProxy(proxyExterneServer);
		setPortProxy(proxyExternePort);
		
		
		logger.debug("Server Address : " + serveurAdresse);
		//logger.debug("User Login : " + blSnowUserLogin);
		//logger.debug("User Login password : " + blSnowUserPassword);
		logger.debug("Url service now Server : " + urlSnowServer);
		logger.debug("URL proxy externe Server : " + proxyExterneServer);
		logger.debug("Port proxy externe : " + proxyExternePort);
	}

	/**
	 * Mise à jour du ticket issue de Snow
	 * 
	 * @param bl     bl électronique
	 * @param blForm formulaire
	 */
	public void updateStatutAnomalieSnow(Bl bl, BlForm blForm, BlAnomalie anosSnow) {
		// On met à jour SNOW
		Boolean maj = majSNOW(bl, anosSnow);
		if (maj != null) {
			if (maj) {
				blForm.setHaveLog(Const.LOG_SUCCES_MAJ_SNOW);
			} else {
				blForm.setHaveLog(Const.LOG_ECHEC_MAJ_SNOW);
			}
		}
	}

	/**
	 * Construire objet payload pour le commentaire
	 * 
	 * @param issueKey ticket
	 * @param status   statut
	 * @return payload construit
	 * @throws JSONException
	 */

	String buildPayloadComment(String issueKey, String commentBody) throws JSONException {
		JSONObject comment = new JSONObject();
		comment.put("comments", commentBody);
		return comment.toString();
	}

	/**
	 * 
	 * @param issueKey    numéro de ticket
	 * @param commentBody commentaire
	 * @return true si tout va bien
	 * @throws IOException
	 * @throws JSONException
	 */
	public boolean addSnowComment(String issueKey, String commentBody) throws IOException, JSONException {

		String uri = Tools.convertMessagesToUtf8(this.urlSnow + Const.GET_RESQUEST_URL + issueKey);		
	

		HttpsURLConnection connection = HttpConnexionBuilder.buildHttpConnection(this.userLogin, this.password, "", uri,
				this.urlProxy, this.portProxy, false, Const.GET_REQUEST);

		// Chercher le sys_id à partir du numéro de ticket
		String sysId = HttpConnexionBuilder.searchSysIdInHttpGetRequest(connection);		
				
		// L'url avec une méthode put pour mettre à jour les infos sur le serveur
		uri = Tools.convertMessagesToUtf8(this.urlSnow + Const.PUT_RESQUEST_URL + sysId);		
		
		// Construire la connection http
		connection = HttpConnexionBuilder.buildHttpConnection(this.userLogin, this.password, "", uri, this.urlProxy,
				this.portProxy, false, Const.PUT_REQUEST);

		// Construire le commentaire
		String payload = buildPayloadComment(issueKey, commentBody);		
		
		// Envoyer la requete
		return HttpConnexionBuilder.sendHttpRequest(connection, payload);

	}

	/**
	 * Ajout d'un commentaire dans snow
	 * 
	 * @param bl  bl
	 * @param key anomalie
	 * @return true si la mise à jour s'est bien déroulée
	 */
	// Met à jour Snow
	private Boolean majSNOW(Bl bl, BlAnomalie key) {
		Boolean resultat = null;
		String snowStatus = "-1";
		Etat etatEnCours = bl.getEtat();
		String issueKey = key.getAnomalieReference();
		String commentBody = Tools.buildComment(bl);
		if (etatEnCours != null && etatEnCours.getEtatLibelle() != null) {
			snowStatus = Tools.convertBlStatusToJiraSnowStatus(etatEnCours.getEtatLibelle());
		}		

		try {

			if (!snowStatus.equals("-1")) {
				resultat = addSnowComment(issueKey, commentBody);				
			} else {
				resultat = null;
			}

		} catch (Exception e) {
			logger.error("Snow rest client error. cause: " + e.getMessage(), e);
			resultat = false;
		}
		return resultat;
	}

}
