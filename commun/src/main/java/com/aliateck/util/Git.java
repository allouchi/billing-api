package com.accorhotels.blelectronique.versionning.monGit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.log;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.GroupApi;
import org.gitlab4j.api.RepositoryFileApi;
import org.gitlab4j.api.TagsApi;
import org.gitlab4j.api.models.CompareResults;
import org.gitlab4j.api.models.Diff;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.RepositoryFile;
import org.gitlab4j.api.models.Tag;
import org.json.JSONArray;
import org.json.JSONObject;

import com.accorhotels.blelectronique.database.bean.Bl;
import com.accorhotels.blelectronique.presentation.form.BlForm;
import com.accorhotels.blelectronique.services.interfaces.IAppConfigService;
import com.accorhotels.blelectronique.services.interfaces.IBlService;
import com.accorhotels.blelectronique.util.Const;
import com.accorhotels.blelectronique.util.Tools;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author MALIANE
 *
 */
@Slf4j
public class Git {

	

	private String url;
	private String token;
	private String applicationName;
	private String tagName;
	private String urlProxy;
	private String portProxy;

	private static final String RESPONSE_STATUS = "response server status : ";
	private static final String HTTP_REQUEST_ERROR = "!!! Http request server error : ";
	private static final String GITLAB_RESPONSE_OK = "!!! Connexion à Gitlab = OK, Response server status : ";
	private static final String CONNEXION_HTTP_KO = "!!! Echec connexion HTTP";

	private IBlService blService;
	private IAppConfigService appConfigService;
	private HttpServletRequest request;

	public Git(IBlService blService, IAppConfigService appConfigService, HttpServletRequest request) {
		this.blService = blService;
		this.appConfigService = appConfigService;
		this.request = request;
		setUserInfo();
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
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

	public IAppConfigService getAppConfigService() {
		return appConfigService;
	}

	public void setAppConfigService(IAppConfigService appConfigService) {
		this.appConfigService = appConfigService;
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

	/**
	 * Rechercher les fichiers liés au tag
	 * 
	 * @param bl     bl électronique
	 * @param blForm formulaire
	 */
	public List<com.accorhotels.blelectronique.database.bean.File> getAllFileFromGitHttp(Bl bl, BlForm blForm) {

		List<com.accorhotels.blelectronique.database.bean.File> allFiles = null;
		String commitId = null;

		String appliName = bl.getApplication().getApplicationVersionning();
		String numTag = bl.getLabel();

		// Recherche de l'id du projet sélectionné
		Object projectId = findProjectId(appliName, numTag);
		// Recherche de l'avant dernier tag du tag sélectioné
		String numLastTag = findNumLastTag(projectId, numTag);
		log.debug("Dernier tag trouvé : " + numLastTag);
		if (numLastTag != null) {
			Map<String, String> diffsMap = findTagsDiff(projectId, numTag, numLastTag);

			if (diffsMap != null) {
				for (String key : diffsMap.keySet()) {
					commitId = diffsMap.get(key);
					if (commitId != null) {
						break;
					}
				}
			}
			if (diffsMap != null) {
				Set<String> keySet = diffsMap.keySet();
				String[] listeFiles = keySet.toArray(new String[keySet.size()]);

				if (listeFiles != null && listeFiles.length > 0) {
					allFiles = buildFilesPaths(listeFiles, bl, commitId);
				}
			}
		}
		return allFiles;
	}

	/**
	 * Chercher les fichiers modifiés et tagués
	 * 
	 * @param projectId
	 */
	private Map<String, String> findTagsDiff(Object projectId, String numTag, String numLastTag) {

		StringBuilder jsonResponse = new StringBuilder();
		String line = "";
		BufferedReader reader = null;
		String commitId = null;
		HttpsURLConnection connexion = null;
		List<Object> listFiles = new ArrayList<>();
		Map<String, String> commitsMap = new HashMap<>();
		Map<String, String> diffsMap = new HashMap<>();

		// URL de diffs entre les tags
		String buildUrl = this.url + "/api/v4/projects/" + projectId + "/repository/compare?from=" + numLastTag + "&to="
				+ numTag + "&private_token=" + this.token;

		try {
			connexion = Tools.buildHttpConnection(buildUrl, this.urlProxy, this.portProxy, false);
			if (connexion != null) {
				connexion.connect();
				int status = connexion.getResponseCode();

				log.debug(GITLAB_RESPONSE_OK + status);

				if (status == 200) {
					reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
					while ((line = reader.readLine()) != null) {
						jsonResponse.append(line);
					}
					// Construction de la réponse
					if (jsonResponse.toString() != null && !jsonResponse.toString().isEmpty()
							&& !jsonResponse.toString().equals("[]")) {

						JSONObject obj = new JSONObject(jsonResponse.toString());
						JSONArray arrayCommits = obj.getJSONArray("commits");

						if (arrayCommits != null && !arrayCommits.isEmpty()) {
							for (int i = 0; i < arrayCommits.length(); i++) {
								JSONObject d = arrayCommits.getJSONObject(i);
								commitsMap.put(d.get("committed_date").toString(), d.get("short_id").toString());
							}
						}

						Set<String> keySet = commitsMap.keySet();
						if (keySet != null) {
							String[] keyArray = keySet.toArray(new String[keySet.size()]);
							Arrays.sort(keyArray);
							if (!keySet.isEmpty()) {
								String dernierCommit = keyArray[keyArray.length - 1];
								commitId = commitsMap.get(dernierCommit);
							}
						}

						JSONArray arrayDiff = obj.getJSONArray("diffs");
						if (arrayDiff != null && !arrayDiff.isEmpty()) {
							for (int i = 0; i < arrayDiff.length(); i++) {
								JSONObject d = arrayDiff.getJSONObject(i);
								listFiles.add(d.get("new_path"));
								diffsMap.put(d.get("new_path").toString(), commitId);
							}
						}
					}
				} else {
					log.debug("Find diff tag method response KO : " + RESPONSE_STATUS + status);
				}
			} else {
				log.error(CONNEXION_HTTP_KO);
			}
		} catch (IOException e) {
			log.error(HTTP_REQUEST_ERROR + e.getMessage());
		} finally {

			if (connexion != null) {
				connexion.disconnect();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					log.error("Problème de fermeture de flux : " + e.getMessage());
				}
			}
		}
		return diffsMap;

	}

	/**
	 * Chercher l'avant dernier tag de celui sélectionné
	 * 
	 * @param project id
	 * @return lastTag
	 */
	private String findNumLastTag(Object projectId, String tagNum) {

		StringBuilder jsonResponse = new StringBuilder();
		String line = "";
		BufferedReader reader = null;
		HttpsURLConnection connexion = null;
		String lastTag = null;

		// l'url de recherche des tags sur git
		String buildUrl = this.url + "/api/v4/projects/" + projectId + "/repository/tags?" + "&private_token="
				+ this.token;
		try {
			connexion = Tools.buildHttpConnection(buildUrl, this.urlProxy, this.portProxy, false);
			if (connexion != null) {
				connexion.connect();
				int status = connexion.getResponseCode();

				log.debug(GITLAB_RESPONSE_OK + status);

				if (status == 200) {
					reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));

					while ((line = reader.readLine()) != null) {
						jsonResponse.append(line);
					}

					Map<String, String> mapKey = new LinkedHashMap<>();
					// Traiter le réponse du serveur
					if (jsonResponse.toString() != null && !jsonResponse.toString().isEmpty()
							&& !jsonResponse.toString().equals("[]")) {

						JSONArray jArray = new JSONArray(jsonResponse.toString());
						String dateTagSelected = null;

						for (int i = 0; i < jArray.length(); i++) {
							JSONObject data = jArray.getJSONObject(i);
							JSONObject objCommit = data.getJSONObject("commit");
							if (!data.getString("name").equals(tagNum)) {
								mapKey.put(data.getString("name"), objCommit.get("committed_date").toString());
							} else {
								dateTagSelected = objCommit.get("committed_date").toString();
							}
						}

						// Prendre le tag avec la dernière date
						LocalDateTime selectedDate = Tools.convertStringToDate(dateTagSelected);

						Iterator<String> it = mapKey.keySet().iterator();
						while (it.hasNext()) {
							String tag = it.next();
							String date = mapKey.get(tag);
							LocalDateTime dateTime = Tools.convertStringToDate(date);
							if (dateTime != null && selectedDate != null && selectedDate.isBefore(dateTime)) {
								it.remove();
							}
						}

						Set<String> keySet = mapKey.keySet();
						String[] keyArray = keySet.toArray(new String[keySet.size()]);

						if (keySet != null && !keySet.isEmpty()) {
							lastTag = keyArray[0];
						}
					}

				} else {
					log.debug("Find last num tag method response KO : " + RESPONSE_STATUS + status);
				}
			} else {
				log.error(CONNEXION_HTTP_KO);
			}
		} catch (IOException e) {
			log.error(HTTP_REQUEST_ERROR + e.getMessage());
		} finally {

			if (connexion != null) {
				connexion.disconnect();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					log.error("Problème de fermeture de flux : " + e.getMessage());
				}
			}
		}
		return lastTag;
	}

	/**
	 * Chercher le project ID
	 * 
	 * @param appliName
	 * @param tName
	 * @return
	 */
	private Object findProjectId(String appliName, String tName) {

		StringBuilder jsonResponse = new StringBuilder();
		String line = null;
		Object projectId = null;
		BufferedReader reader = null;
		HttpsURLConnection connexion = null;

		try {

			String appliGit = Tools.convertBlAppliToGitAppli(appliName);
			String appli = Tools.findGoodProject(appliGit, tName);

			// l'url de connextion pour la recherche de l'id projet
			String buildUrl = this.url + "/api/v4/search?scope=projects&search=" + appli + "&private_token="
					+ this.token;
			// Connexion à Git pour rechercher l'id du projet recherché
			connexion = Tools.buildHttpConnection(buildUrl, this.urlProxy, this.portProxy, false);

			if (connexion != null) {

				connexion.connect();
				int status = connexion.getResponseCode();

				log.debug(GITLAB_RESPONSE_OK + status);

				if (status == 200) {
					reader = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
					while ((line = reader.readLine()) != null) {
						if (!line.isEmpty()) {
							jsonResponse.append(line);
						}
					}
					// Construire la réponse
					if (jsonResponse.toString() != null && !jsonResponse.toString().equals("[]")
							&& jsonResponse.toString().length() > 0) {
						String res = jsonResponse.toString().substring(1, jsonResponse.toString().length() - 1);
						JSONObject json = new JSONObject(res);
						projectId = json.get("id");
					}
				} else {
					log.debug("Find project id method response KO : " + RESPONSE_STATUS + status);
				}
			} else {
				log.error(CONNEXION_HTTP_KO);
			}
		} catch (IOException e) {
			log.error(HTTP_REQUEST_ERROR + e.getMessage());
		} finally {

			if (connexion != null) {
				connexion.disconnect();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					log.error("Problème de fermeture de flux : " + e.getMessage());
				}
			}
		}

		log.debug("Project ID : " + projectId);

		return projectId;
	}

	/**
	 * Construire le path des fichiers
	 * 
	 * @param repo
	 * @return path file
	 */
	private List<com.accorhotels.blelectronique.database.bean.File> buildFilesPaths(String[] listFiles, Bl bl,
			String commitId) {

		final List<com.accorhotels.blelectronique.database.bean.File> allFiles = new ArrayList<>();

		log.debug("Construction de la liste des fichiers");

		for (String filePath : listFiles) {

			if (filePath != null) {
				StringBuilder rep = new StringBuilder();
				String[] tab = filePath.split("/");
				if (tab.length >= 1) {
					String fileName = tab[tab.length - 1];
					int index = tab.length - 1;
					for (int i = 0; i < index; i++) {
						String repName = tab[i];
						rep.append(repName);
						if (i < index - 1) {
							rep.append("/");
						}
					}

					if (fileName != null && !fileName.isEmpty() && rep != null && !rep.toString().isEmpty()) {
						com.accorhotels.blelectronique.database.bean.File file = new com.accorhotels.blelectronique.database.bean.File();
						file.setFileNom(fileName);
						file.setFileRepertoire(rep.toString());
						file.setBl(bl);
						file.setFileVersion(commitId);
						allFiles.add(file);
					}
				}
			}
		}
		return allFiles;
	}

	/**
	 * Set les infos de l'utilisateur et de l'environnement
	 * 
	 */
	private void setUserInfo() {
		String machineProd = null;
		String machineRecette = null;
		Const.ENVIRONNEMENT env = Const.ENVIRONNEMENT.DEV;
		String urlGitServer = null;
		String blGitUserToken = null;
		String proxyExterneServer = null;
		String proxyExternePort = null;
		String serveurAdresse = null;

		if (appConfigService != null) {
			
			if(appConfigService.getConfig(Const.BL_URL_GIT_SERVER) != null) {
				urlGitServer = appConfigService.getConfig(Const.BL_URL_GIT_SERVER).getCodeLibConfig();
			}
			if(appConfigService.getConfig(Const.BL_GIT_USER_TOKEN) != null) {
				blGitUserToken = appConfigService.getConfig(Const.BL_GIT_USER_TOKEN).getCodeLibConfig();
			}			

			log.debug("Git URL Server : " + urlGitServer);

			if (appConfigService.getConfig(Const.BL_APP_CONFIG_MACHINE_PROD) != null) {
				machineProd = appConfigService.getConfig(Const.BL_APP_CONFIG_MACHINE_PROD).getCodeLibConfig();
			}

			if (appConfigService.getConfig(Const.BL_APP_CONFIG_MACHINE_REC) != null) {
				machineRecette = appConfigService.getConfig(Const.BL_APP_CONFIG_MACHINE_REC).getCodeLibConfig();
			}		

		    serveurAdresse = "http://" + request.getServerName();
			

			if (machineProd != null && serveurAdresse.equalsIgnoreCase(machineProd)) {
				env = Const.ENVIRONNEMENT.PROD;
				log.debug("Environnement de Prod : " + request.getServerName());
			} else if (machineRecette != null && serveurAdresse.equalsIgnoreCase(machineRecette)) {
				env = Const.ENVIRONNEMENT.REC;
				log.debug("Environnement de Recette : " + serveurAdresse);
			} else {
				log.debug("Environnement de Dev : " + serveurAdresse);
			}

			if(appConfigService.getConfig(Const.BL_URL_PROXY_EXTERNE + "_" + env.getSuffixe()) != null){
				proxyExterneServer = appConfigService.getConfig(Const.BL_URL_PROXY_EXTERNE + "_" + env.getSuffixe())
						.getCodeLibConfig();
			}			
			if(appConfigService.getConfig(Const.BL_PORT_PROXY_EXTERNE + "_" + env.getSuffixe()) != null) {
				proxyExternePort = appConfigService.getConfig(Const.BL_PORT_PROXY_EXTERNE + "_" + env.getSuffixe())
						.getCodeLibConfig();
			}			
		}

		log.debug("Adress server : " + serveurAdresse);
		log.debug("Adress server proxy : " + proxyExterneServer);
		log.debug("Port server proxy : " + proxyExternePort);
		setUrlProxy(proxyExterneServer);
		setPortProxy(proxyExternePort);
		setUrl(urlGitServer);
		setToken(blGitUserToken);
	}

	/**
	 * Rechercher dans les groupes, l'id du projet passé en paramètre
	 * 
	 * @param projectName application sélectionnée
	 * @return applicationId id application
	 * @throws GitLabApiException
	 */
	private Long getIdApplication(GitLabApi gitLabApi, String projectName, String tagName) throws GitLabApiException {

		Long projectId = null;

		if (projectName != null && !projectName.equals("-1")) {
			GroupApi groupeApi = gitLabApi.getGroupApi();
			List<Group> groups = groupeApi.getGroups(projectName);
			for (Group g : groups) {
				List<Project> projects = groupeApi.getProjects(g.getId());
				for (Project p : projects) {
					String appliName = Tools.findGoodProject(projectName, tagName);
					if (p.getName().equals(appliName)) {
						projectId = p.getId();
						log.debug("Project ID : " + projectId);
						break;
					}
				}
			}
		}

		return projectId;
	}

	/**
	 * 
	 * @param gitLabApi
	 * @param appliactionId
	 * @return
	 * @throws GitLabApiException
	 */
	private Tag getLastTag(GitLabApi gitLabApi, Long appliactionId) throws GitLabApiException {

		log.debug("Recheche du dernier tag");
		Tag lastTag = null;
		TagsApi tagApi = gitLabApi.getTagsApi();
		List<Tag> tags = tagApi.getTags(appliactionId);
		if (tags != null && !tags.isEmpty()) {

			Collections.sort(tags, new Comparator<Tag>() {
				public int compare(Tag t1, Tag t2) {
					return t1.getCommit().getCommittedDate().compareTo(t2.getCommit().getCommittedDate());
				}
			});

			if (tags.size() >= 2) {
				lastTag = tags.get(tags.size() - 2);
			} else {
				lastTag = tags.get(0);
			}
		}
		return lastTag;
	}

	/**
	 * Rechercher les fichiers liés au tag
	 * 
	 * @param bl     bl électronique
	 * @param blForm formulaire
	 */
	public List<com.accorhotels.blelectronique.database.bean.File> getAllFileFromGitApi(Bl bl, BlForm blForm) {

		final List<com.accorhotels.blelectronique.database.bean.File> allFiles = new ArrayList<>();
		GitLabApi gitLabApi = null;

		String appliName = bl.getApplication().getApplicationVersionning();
		String tName = bl.getLabel();

		try {
			// Se connecter à gitLab avec url et token
			log.debug("Se connecter à Gitlab");
			gitLabApi = new GitLabApi(this.url, this.token);

			// faire la correspondance entre les noms des applis sur git et les noms sur
			// l'appli BL
			// Electronique
			String appliGit = Tools.convertBlAppliToGitAppli(appliName);
			if (!appliGit.equals("-1")) {
				Long appliactionId = getIdApplication(gitLabApi, appliGit, tName);
				RepositoryFileApi fileApi = gitLabApi.getRepositoryFileApi();
				if (appliactionId != null) {
					Tag lastTag = getLastTag(gitLabApi, appliactionId);
					if (lastTag != null) {
						log.debug("Tag trouvé numéro " + lastTag.getName());
						CompareResults resultat = gitLabApi.getRepositoryApi().compare(appliactionId, lastTag.getName(),
								tName);
						if (resultat != null) {
							List<Diff> difs = resultat.getDiffs();
							// String appliOrBdd = Tools.isAppliOrBdd(appliGit, tName);
							if (difs != null && !difs.isEmpty()) {
								for (Diff d : difs) {
									if (d != null) {
										pathsBuilder(fileApi, appliactionId, d, bl, "master", allFiles);
									}
								}
							}
						}
					}
				}
			}
		} catch (GitLabApiException e) {
			log.debug("Un problème de connextion à Gitlab : " + e.getMessage());
		} finally {
			if (gitLabApi != null) {
				gitLabApi.close();
			}
		}
		return allFiles;
	}

	/**
	 * 
	 * @param fileApi
	 * @param appliactionId
	 * @param appliGit
	 * @param tName
	 * @param d
	 * @throws GitLabApiException
	 */
	private void pathsBuilder(RepositoryFileApi fileApi, Long appliactionId, Diff d, Bl bl, String appliOrBdd,
			List<com.accorhotels.blelectronique.database.bean.File> allFiles) throws GitLabApiException {
		com.accorhotels.blelectronique.database.bean.File file = null;

		RepositoryFile repo = fileApi.getFile(appliactionId, d.getNewPath(), appliOrBdd);
		String path = buildFilePath(repo);
		if (path != null && !path.equals("")) {
			file = new com.accorhotels.blelectronique.database.bean.File();
			file.setFileNom(repo.getFileName());
			file.setFileRepertoire(path);
			file.setFileVersion(repo.getCommitId().substring(0, 8));
			file.setBl(bl);
			allFiles.add(file);
		}
	}

	/**
	 * Construire le path du fichier
	 * 
	 * @param repo
	 * @return path file
	 */
	private String buildFilePath(RepositoryFile repo) {
		String pathFile = null;
		String fileName = repo.getFileName();
		String fileRep = repo.getFilePath();
		if (fileName != null && fileRep != null) {
			pathFile = fileRep.replaceAll(fileName, "");
		}
		return pathFile;
	}

}
