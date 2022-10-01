package com.accorhotels.blelectronique.webservices;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
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
public class JiraApiHttpClientService {

	private static final Logger logger = Logger.getLogger(JiraApiHttpClientService.class);

	private String userLogin;
	private String password;
	private String urlJira;
	private String urlProxy;
	private String portProxy;
	private String token;
	private IBlService blService;
	private IAppConfigService appConfigService;
	private HttpServletRequest request;
	private String userProfile;

	public JiraApiHttpClientService(IBlService blService, IAppConfigService appConfigService,
			HttpServletRequest request) {
		this.blService = blService;
		this.appConfigService = appConfigService;
		this.request = request;
		setUserInfo();
	}

	public JiraApiHttpClientService(String userLogin, String password, String urlJira, String urlProxy,
			String portProxy, String token) {
		this.userLogin = userLogin;
		this.password = password;
		this.urlJira = urlJira;
		this.urlProxy = urlProxy;
		this.portProxy = portProxy;
		this.token = token;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrlJira() {
		return urlJira;
	}

	public void setUrlJira(String urlJira) {
		this.urlJira = urlJira;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public IBlService getBlService() {
		return blService;
	}

	public void setBlService(IBlService blService) {
		this.blService = blService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IAppConfigService getAppConfigService() {
		return appConfigService;
	}

	public void setAppConfigService(IAppConfigService appConfigService) {
		this.appConfigService = appConfigService;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	/**
	 * Set les infos de l'utilisateur et de l'environnement
	 * 
	 */
	private void setUserInfo() {
		String machineProd = null;
		String machineRecette = null;
		String serveurAdresse = null;
		String proxyExterneServer = null;
		String proxyExternePort = null;
		String jiraUserProfile = null;
		String blJiraUserLogin = null;
		String blJiraUserToken = null;
		String urlJiraServer = null;
		Const.ENVIRONNEMENT env = Const.ENVIRONNEMENT.DEV;

		
		if(appConfigService.getConfig(Const.BL_JIRA_USER_LOGIN) != null) {
			 blJiraUserLogin = appConfigService.getConfig(Const.BL_JIRA_USER_LOGIN).getCodeLibConfig();
		}
		
		if(appConfigService.getConfig(Const.BL_JIRA_USER_TOKEN) != null) {
			 blJiraUserToken = appConfigService.getConfig(Const.BL_JIRA_USER_TOKEN).getCodeLibConfig();
		}
		
		if(appConfigService.getConfig(Const.BL_URL_JIRA_SERVER) != null) {
			 urlJiraServer = appConfigService.getConfig(Const.BL_URL_JIRA_SERVER).getCodeLibConfig();
		}
		
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

		if(appConfigService.getConfig(Const.BL_URL_PROXY_EXTERNE + "_" + env.getSuffixe()) != null) {
			proxyExterneServer = appConfigService.getConfig(Const.BL_URL_PROXY_EXTERNE + "_" + env.getSuffixe())
					.getCodeLibConfig();
		}
		
		if( appConfigService.getConfig(Const.BL_PORT_PROXY_EXTERNE + "_" + env.getSuffixe()) != null) {
			 proxyExternePort = appConfigService.getConfig(Const.BL_PORT_PROXY_EXTERNE + "_" + env.getSuffixe())
					.getCodeLibConfig();
		}
		
		if(appConfigService.getConfig(Const.BL_USER_PROFILE_JIRA + "_" + env.getSuffixe()) != null) {
			 jiraUserProfile = appConfigService.getConfig(Const.BL_USER_PROFILE_JIRA + "_" + env.getSuffixe())
					.getCodeLibConfig();
		}		

		
		setUserLogin(blJiraUserLogin);
		setPassword("");
		setUrlJira(urlJiraServer);
		setUrlProxy(proxyExterneServer);
		setPortProxy(proxyExternePort);
		setToken(blJiraUserToken);
		setUserProfile(jiraUserProfile);
		
		logger.debug("Server Address : " + serveurAdresse);
		logger.debug("Url Jira Server : " + urlJiraServer);
		logger.debug("URL proxy externe Server : " + proxyExterneServer);
		logger.debug("Port proxy externe : " + proxyExternePort);
		//logger.debug("Jira User Profile : " + jiraUserProfile);
		
	}

	/**
	 * Construire objet payload pour le statut
	 * 
	 * @param issueKey ticket
	 * @param status   statut
	 * @return payload construit
	 * @throws JSONException
	 */

	String buildPayloadStatus(String issueKey, String status) throws JSONException {

		JSONObject statut = new JSONObject();
		statut.put("id", status);

		JSONArray obj = new JSONArray();
		obj.put(statut);

		JSONObject mainObj = new JSONObject();
		mainObj.put("transition", statut);

		return mainObj.toString();
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

		JSONObject mainObj = new JSONObject();

		// visibility
		JSONObject visi = new JSONObject();				
		visi.put("type", "role");
		visi.put("value", this.userProfile);

		JSONArray objv = new JSONArray();
		objv.put(visi);

		JSONArray obj = new JSONArray();
		obj.put(visi);

		JSONArray arrayContent2 = new JSONArray();
		JSONObject contentItem2 = new JSONObject();
		contentItem2.put("text", commentBody);
		contentItem2.put("type", "text");
		arrayContent2.put(contentItem2);

		JSONArray arrayContent1 = new JSONArray();
		JSONObject contentItem1 = new JSONObject();
		contentItem1.put("type", "paragraph");
		contentItem1.put("content", arrayContent2);
		arrayContent1.put(contentItem1);

		JSONObject body = new JSONObject();
		body.put("type", "doc");
		body.put("version", 1);
		body.put("content", arrayContent1);

		mainObj.put("visibility", visi);
		mainObj.put("body", body);

		return mainObj.toString();
	}

	/**
	 * Mise à jour du statut du ticket Jira
	 * 
	 * @param bl        bl électronique
	 * @param blForm    formulaire
	 * @param blService service connexion à la base de donnée
	 */
	public void updateStatutAnomalieJira(Bl bl, BlForm blForm, BlAnomalie anosJira) {

		Boolean maj = majJIRA(bl, anosJira);
		if (maj != null) {
			if (maj) {
				blForm.setHaveLog(Const.LOG_SUCCES_MAJ_JIRA);
			} else {
				blForm.setHaveLog(Const.LOG_ECHEC_MAJ_JIRA);
			}
		}
	}

	/**
	 * Mise à jour des statuts Jira
	 * 
	 * @param bl  bl
	 * @param key anomalie
	 * @return true si la mise à jour s'est bien déroulée
	 */
	private Boolean majJIRA(Bl bl, BlAnomalie key) {
		Boolean resultat = null;
		String jiraStatus = "-1";
		Etat etatEnCours = bl.getEtat();
		String issueKey = key.getAnomalieReference();
		String commentBody = Tools.buildComment(bl);

		if (etatEnCours != null && etatEnCours.getEtatLibelle() != null) {
			jiraStatus = Tools.convertBlStatusToJiraSnowStatus(etatEnCours.getEtatLibelle());
		}

		try {

			if (!jiraStatus.equals("-1")) {
				resultat = updateJiraStatus(issueKey, jiraStatus);
				resultat = resultat | addJiraComment(issueKey, commentBody);				
			} else {
				resultat = null;
			}

		} catch (Exception e) {
			logger.error("Jira rest client process workflow action error. cause: " + e.getMessage(), e);
			resultat = false;
		}
		return resultat;
	}

	/**
	 * 
	 * @param key
	 * @param jiraStatus
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public boolean updateJiraStatus(String key, String jiraStatus) throws IOException, JSONException {

		String updateUrl = Tools.convertMessagesToUtf8(this.urlJira + "/rest/api/3/issue/" + key + "/transitions");

		HttpsURLConnection updateConnexion = HttpConnexionBuilder.buildHttpConnection(this.userLogin, this.password,
				this.token, updateUrl, this.urlProxy, this.portProxy, true, Const.POST_REQUEST);
		String payload = buildPayloadStatus(key, jiraStatus);
		return HttpConnexionBuilder.sendHttpRequest(updateConnexion, payload);
	}

	/**
	 * Ajout de commentaire sur le tiket Jira
	 * 
	 * @param issueKey    numéro tiket
	 * @param commentBody commentaire
	 * @return true si OK
	 * @throws IOException
	 * @throws JSONException
	 */

	public boolean addJiraComment(String issueKey, String commentBody) throws IOException, JSONException {

		// Cobstruction de l'url du ticket JIRA
		String uri = Tools.convertMessagesToUtf8(this.urlJira + "/rest/api/3/issue/" + issueKey + "/comment");		
		// Faire la conncetion HTTP au service
		HttpsURLConnection connection = HttpConnexionBuilder.buildHttpConnection(this.userLogin, this.password,
				this.token, uri, this.urlProxy, this.portProxy, true, Const.POST_REQUEST);
		// Construire le Payload
		String payload = buildPayloadComment(issueKey, commentBody);		
		// Envoyer la requet
		return HttpConnexionBuilder.sendHttpRequest(connection, payload);
	}

}
