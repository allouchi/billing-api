package com.accorhotels.blelectronique.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.accorhotels.blelectronique.database.bean.Anomalie;
import com.accorhotels.blelectronique.database.bean.Bl;
import com.accorhotels.blelectronique.presentation.form.BlForm;

/**
 * 
 * @author MALIANE
 *
 */
public class Tools {

	private Tools() {
	}

	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static DateFormat dateFormatHHmm = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private static DateFormat dateFormatHHmmss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static final Logger logger = Logger.getLogger(Tools.class);

	// Génère password aléatoire
	public static String generatePassword() {
		Random r = new Random();
		int nbCharPassword = r.nextInt(7) + 8;
		int x = 0;
		String result = "";
		for (int i = 0; i < nbCharPassword; i++) {
			if (r.nextInt() % 3 != 0) {
				x = r.nextInt(26) + 65;
			} else {
				x = r.nextInt(10) + 48;
			}
			result += (char) x;
		}
		return result;
	}

	// Première lettre majuscule le reste en minuscule
	public static String capitalize(String s) {
		if (s.length() == 0) {
			return s;
		}
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	// Retourne la date passé en paramètre au format dd/MM/yyyy
	// et en String
	public static String formatDate(Date date) {
		String dateFormatter;
		if (date == null) {
			dateFormatter = "";
		} else {
			dateFormatter = dateFormat.format(date);
		}

		return dateFormatter;
	}

	public static Date stringToDate(String sDate) throws ParseException {
		return dateFormat.parse(sDate);
	}

	// Retourne la date du jour en String
	public static String getDate() {
		String dateFormatter;
		dateFormatter = dateFormat.format(new Date());
		return dateFormatter;
	}

	// Retourne true si la date existe et false si elle n'existe pas
	public static boolean dateExiste(String date) {
		boolean resultat = false;
		try {
			if (!date.trim().isEmpty()) {
				dateFormat.setLenient(false);
				dateFormat.parse(date);
				resultat = true;
			}
		} catch (ParseException e) {
			logger.error("dateExiste(date) : " + e.getMessage());
			logger.error("Date erreur = " + date);
		}
		return resultat;
	}

	// Retourne l'heure du jour en String
	public static String getHour() {
		String dateFormatter;
		dateFormatter = new SimpleDateFormat("HH:mm").format(new Date());
		return dateFormatter;
	}

	// Retourne la date du Jour au format yyMMdd
	public static String getDateForNumeroBL() {
		String dateFormatter;
		dateFormatter = new SimpleDateFormat("yyMMdd").format(new Date());
		return dateFormatter;
	}

	// Retourne la date passé en paramètre au format dd/MM/yyyy hh:mm
	// et en String
	public static String formatDateHHmm(Date date) {
		String dateFormatter;
		if (date == null) {
			dateFormatter = "";
		} else {
			dateFormatter = dateFormatHHmm.format(date);
		}

		return dateFormatter;
	}

	// Retourne la date passé en paramètre au format dd/MM/yyyy hh:mm:ss
	// et en String
	public static String formatDateHHmmss(Date date) {
		String dateFormatter;
		if (date == null) {
			dateFormatter = "";
		} else {
			dateFormatter = dateFormatHHmmss.format(date);
		}

		return dateFormatter;
	}

	// Temps d'éxécution d'une page
	public static String getTimeExec(Date dateDebut, Date dateFin) {
		String resultat;
		Date duree = new Date(System.currentTimeMillis()); // Pour calculer la différence
		duree.setTime(dateFin.getTime() - dateDebut.getTime()); // Calcul de la différence
		long secondes = duree.getTime() / 1000;
		long min = secondes / 60;
		long mili = duree.getTime() % 1000;
		secondes %= 60;
		resultat = min + " Minutes " + secondes + " Secondes " + mili + " Milisecondes.";

		return resultat;
	}

	// Vérifie si la chaine est bien numérique
	public static boolean isNumeric(String string) {
		boolean resultat = false;
		try {
			Integer.parseInt(string);
			resultat = true;
		} catch (Exception nfe) {
			logger.error("isNumeric(string) : " + nfe.getMessage());
			resultat = false;
		}
		return resultat;
	}

	// Longueur d'une chaine en utf-8
	public static int lengthUtfHuit(String chaine) {
		int resultat;
		try {
			resultat = chaine.getBytes("UTF-8").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("lengthUtfHuit(string) : " + e.getMessage());
			resultat = 0;
		}
		return resultat;
	}

	// Substring de la chaine à la longueur demandé en utf-8 et en partant de 0
	public static String cutUtfHuit(String s, int n) {
		byte[] utf8;
		try {
			utf8 = s.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return s.substring(0, n);
		}
		int n16 = 0;
		boolean extraLong = false;
		int i = 0;

		if (utf8.length < n) {
			n = utf8.length;
		}
		while (i < n) {
			n16 += (extraLong) ? 2 : 1;
			extraLong = false;
			if ((utf8[i] & 0x80) == 0) {
				i += 1;
			} else if ((utf8[i] & 0xC0) == 0x80) {
				i += 2;
			} else if ((utf8[i] & 0xE0) == 0xC0) {
				i += 3;
			} else {
				i += 4;
				extraLong = true;
			}
		}
		return s.substring(0, n16);
	}

	// Renvoi true si la longueur de la chaine en UTF 8 est supérieur à la valeur
	// passé en paramètre
	public static boolean isLengthUtfHuitSuperior(String chaine, int longueur) {
		boolean resultat = false;
		try {
			if (chaine.getBytes("UTF-8").length > longueur) {
				resultat = true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("isLengthUtfHuitSuperior(string, int) : " + e.getMessage());
		}
		return resultat;
	}

	// Trouve la valeur du numero de l'anomalie
	public static String findValeurAnomalie(String valeur, List<Anomalie> lesAnomalieBl) {
		String resultat = "";
		for (int i = 0; i < lesAnomalieBl.size(); i++) {
			if (lesAnomalieBl.get(i).getAnomalieId().toString().equals(valeur)) {
				resultat = lesAnomalieBl.get(i).getAnomalieLibelle();
			}
		}
		return resultat;
	}

	// Return true si l'anomalie renseigné n'est pas vide
	private static boolean isAnomalieGood(String monAnomalie, String anomalieRef) {
		boolean resultat = true;
		if (!anomalieRef.trim().isEmpty() && monAnomalie.equals("null")) {
			resultat = false;
		}
		return resultat;
	}

	// Return true si l'anomalie renseigné n'est pas vide
	private static boolean isAnomalieGood2(String monAnomalie, String anomalieRef) {
		boolean resultat = true;
		if (anomalieRef.trim().isEmpty() && !monAnomalie.equals("null")) {
			resultat = false;
		}
		return resultat;
	}

	// return true si l'anomalie est bien numérique pour les anomalies de type HPQC
	// ou T-track
	private static boolean isAnomalieNumeric(String monAnomalie, String anomalieRef) {
		boolean resultat = true;
		if (monAnomalie.equals("0") || monAnomalie.equals("1")) { // Si l'anomalie sélectionner est T-track ou HPQC
			if (anomalieRef.trim().isEmpty() || !Tools.isNumeric(anomalieRef.trim())) { // Si la référence est vide ou
																						// n'est pas un entier
				resultat = false;
			}
		}
		return resultat;
	}

	// Return la liste des anomalies qui ne sont pas numérique
	public static List<String> lesAnomalieNonNumeric(BlForm blForm) {
		List<String> resultat = new ArrayList<>();

		if (!isAnomalieNumeric(blForm.getAnomalie_0(), blForm.getAnomalieReference_0())) {
			resultat.add(blForm.getAnomalieReference_0());
		}
		if (!isAnomalieNumeric(blForm.getAnomalie_1(), blForm.getAnomalieReference_1())) {
			resultat.add(blForm.getAnomalieReference_1());
		}
		if (!isAnomalieNumeric(blForm.getAnomalie_2(), blForm.getAnomalieReference_2())) {
			resultat.add(blForm.getAnomalieReference_2());
		}
		if (!isAnomalieNumeric(blForm.getAnomalie_3(), blForm.getAnomalieReference_3())) {
			resultat.add(blForm.getAnomalieReference_3());
		}
		if (!isAnomalieNumeric(blForm.getAnomalie_4(), blForm.getAnomalieReference_4())) {
			resultat.add(blForm.getAnomalieReference_4());
		}
		if (!isAnomalieNumeric(blForm.getAnomalie_5(), blForm.getAnomalieReference_5())) {
			resultat.add(blForm.getAnomalieReference_5());
		}
		if (!isAnomalieNumeric(blForm.getAnomalie_6(), blForm.getAnomalieReference_6())) {
			resultat.add(blForm.getAnomalieReference_6());
		}
		if (!isAnomalieNumeric(blForm.getAnomalie_7(), blForm.getAnomalieReference_7())) {
			resultat.add(blForm.getAnomalieReference_7());
		}
		if (!isAnomalieNumeric(blForm.getAnomalie_8(), blForm.getAnomalieReference_8())) {
			resultat.add(blForm.getAnomalieReference_8());
		}
		if (!isAnomalieNumeric(blForm.getAnomalie_9(), blForm.getAnomalieReference_9())) {
			resultat.add(blForm.getAnomalieReference_9());
		}
		return resultat;
	}

	// return la liste des anomalies qui sont trop grande
	public static List<String> lesAnomalieLongueur(BlForm blForm) {
		List<String> resultat = new ArrayList<>();
		if (Tools.isLengthUtfHuitSuperior(blForm.getAnomalieReference_0(), Const.LONGUEUR_CHAMPS_ANOMALIE)) {
			resultat.add(blForm.getAnomalieReference_0());
		}
		if (Tools.isLengthUtfHuitSuperior(blForm.getAnomalieReference_1(), Const.LONGUEUR_CHAMPS_ANOMALIE)) {
			resultat.add(blForm.getAnomalieReference_1());
		}
		if (Tools.isLengthUtfHuitSuperior(blForm.getAnomalieReference_2(), Const.LONGUEUR_CHAMPS_ANOMALIE)) {
			resultat.add(blForm.getAnomalieReference_2());
		}
		if (Tools.isLengthUtfHuitSuperior(blForm.getAnomalieReference_3(), Const.LONGUEUR_CHAMPS_ANOMALIE)) {
			resultat.add(blForm.getAnomalieReference_3());
		}
		if (Tools.isLengthUtfHuitSuperior(blForm.getAnomalieReference_4(), Const.LONGUEUR_CHAMPS_ANOMALIE)) {
			resultat.add(blForm.getAnomalieReference_4());
		}
		if (Tools.isLengthUtfHuitSuperior(blForm.getAnomalieReference_5(), Const.LONGUEUR_CHAMPS_ANOMALIE)) {
			resultat.add(blForm.getAnomalieReference_5());
		}
		if (Tools.isLengthUtfHuitSuperior(blForm.getAnomalieReference_6(), Const.LONGUEUR_CHAMPS_ANOMALIE)) {
			resultat.add(blForm.getAnomalieReference_6());
		}
		if (Tools.isLengthUtfHuitSuperior(blForm.getAnomalieReference_7(), Const.LONGUEUR_CHAMPS_ANOMALIE)) {
			resultat.add(blForm.getAnomalieReference_7());
		}
		if (Tools.isLengthUtfHuitSuperior(blForm.getAnomalieReference_8(), Const.LONGUEUR_CHAMPS_ANOMALIE)) {
			resultat.add(blForm.getAnomalieReference_8());
		}
		if (Tools.isLengthUtfHuitSuperior(blForm.getAnomalieReference_9(), Const.LONGUEUR_CHAMPS_ANOMALIE)) {
			resultat.add(blForm.getAnomalieReference_9());
		}
		return resultat;
	}

	// Return la liste des anomalies qui ne sont pas correctement renseigné
	public static List<String> lesAnomalieNonGood(BlForm blForm) {
		List<String> resultat = new ArrayList<>();
		if (!isAnomalieGood(blForm.getAnomalie_0(), blForm.getAnomalieReference_0())) {
			resultat.add(blForm.getAnomalieReference_0());
		}
		if (!isAnomalieGood(blForm.getAnomalie_1(), blForm.getAnomalieReference_1())) {
			resultat.add(blForm.getAnomalieReference_1());
		}
		if (!isAnomalieGood(blForm.getAnomalie_2(), blForm.getAnomalieReference_2())) {
			resultat.add(blForm.getAnomalieReference_2());
		}
		if (!isAnomalieGood(blForm.getAnomalie_3(), blForm.getAnomalieReference_3())) {
			resultat.add(blForm.getAnomalieReference_3());
		}
		if (!isAnomalieGood(blForm.getAnomalie_4(), blForm.getAnomalieReference_4())) {
			resultat.add(blForm.getAnomalieReference_4());
		}
		if (!isAnomalieGood(blForm.getAnomalie_5(), blForm.getAnomalieReference_5())) {
			resultat.add(blForm.getAnomalieReference_5());
		}
		if (!isAnomalieGood(blForm.getAnomalie_6(), blForm.getAnomalieReference_6())) {
			resultat.add(blForm.getAnomalieReference_6());
		}
		if (!isAnomalieGood(blForm.getAnomalie_7(), blForm.getAnomalieReference_7())) {
			resultat.add(blForm.getAnomalieReference_7());
		}
		if (!isAnomalieGood(blForm.getAnomalie_8(), blForm.getAnomalieReference_8())) {
			resultat.add(blForm.getAnomalieReference_8());
		}
		if (!isAnomalieGood(blForm.getAnomalie_9(), blForm.getAnomalieReference_9())) {
			resultat.add(blForm.getAnomalieReference_9());
		}
		return resultat;
	}

	// Return la liste des anomalies qui ne sont pas correctement renseigné
	public static List<String> lesAnomalieNonGood2(BlForm blForm) {
		List<String> resultat = new ArrayList<>();
		if (!isAnomalieGood2(blForm.getAnomalie_0(), blForm.getAnomalieReference_0())) {
			resultat.add(blForm.getAnomalie_0());
		}
		if (!isAnomalieGood2(blForm.getAnomalie_1(), blForm.getAnomalieReference_1())) {
			resultat.add(blForm.getAnomalie_1());
		}
		if (!isAnomalieGood2(blForm.getAnomalie_2(), blForm.getAnomalieReference_2())) {
			resultat.add(blForm.getAnomalie_2());
		}
		if (!isAnomalieGood2(blForm.getAnomalie_3(), blForm.getAnomalieReference_3())) {
			resultat.add(blForm.getAnomalie_3());
		}
		if (!isAnomalieGood2(blForm.getAnomalie_4(), blForm.getAnomalieReference_4())) {
			resultat.add(blForm.getAnomalie_4());
		}
		if (!isAnomalieGood2(blForm.getAnomalie_5(), blForm.getAnomalieReference_5())) {
			resultat.add(blForm.getAnomalie_5());
		}
		if (!isAnomalieGood2(blForm.getAnomalie_6(), blForm.getAnomalieReference_6())) {
			resultat.add(blForm.getAnomalie_6());
		}
		if (!isAnomalieGood2(blForm.getAnomalie_7(), blForm.getAnomalieReference_7())) {
			resultat.add(blForm.getAnomalie_7());
		}
		if (!isAnomalieGood2(blForm.getAnomalie_8(), blForm.getAnomalieReference_8())) {
			resultat.add(blForm.getAnomalie_8());
		}
		if (!isAnomalieGood2(blForm.getAnomalie_9(), blForm.getAnomalieReference_9())) {
			resultat.add(blForm.getAnomalie_9());
		}
		return resultat;
	}

	/**
	 * Faire la correspondance entre les statuts BL et les statuts Jira & Snow
	 * 
	 * @param blStatus
	 * @return jira & snow status
	 */
	public static String convertBlStatusToJiraSnowStatus(String blStatus) {

		String jiraSnowStatus = "-1";
		StatutEnum status = StatutEnum.valueOfLabel(blStatus);

		if (status != null) {
			switch (status) {
			case DEMANDE:
				jiraSnowStatus = "-1";
				break;
			case ENCOURS:
				// jiraStatus = "21";
				jiraSnowStatus = "-1";
				break;
			case ENATTENTE:
				jiraSnowStatus = "-1";
				break;
			case FINALISE:
				jiraSnowStatus = "-1";
				break;
			case SAISI:
				jiraSnowStatus = "-1";
				break;
			case INTEGRE:
				jiraSnowStatus = "51";
				break;
			case FERMER:
				jiraSnowStatus = "-1";
				break;
			case REALISE:
				jiraSnowStatus = "-1";
				break;
			case TESTE:
				jiraSnowStatus = "-1";
				break;
			default:
				jiraSnowStatus = "-1";
			}
		}

		return jiraSnowStatus;

	}

	/**
	 * Faire la correspondance entre les applications BL et GIT
	 * 
	 * @param blAppli
	 * @return git application
	 */
	public static String convertBlAppliToGitAppli(String blAppli) {

		String gitAppli = "-1";
		ApplicationEnum appli = ApplicationEnum.valueOfLabel(blAppli);

		if (appli != null) {
			switch (appli) {
			case TEST:
				gitAppli = "appli-test";
				break;
			case BLIV:
				gitAppli = "bliv";
				break;
			case SARL:
				gitAppli = "sarl";
				break;
			case SET:
				gitAppli = "set";
				break;
			case OPLA:
				gitAppli = "opla";
				break;
			case REFDTS:
				gitAppli = "refdts";
				break;
			case HRDATA:
				gitAppli = "hrdata";
				break;
			case ACTEUR2:
				gitAppli = "acteur2";
				break;
			case GMAN:
				gitAppli = "gman";
				break;
			case DVD:
				gitAppli = "open";
				break;
			case AGAT:
				gitAppli = "agat";
				break;
			case ASR:
				gitAppli = "asr";
				break;
			case PUMA:
				gitAppli = "gaia";
				break;
			case SCORINGRM:
				gitAppli = "scoringRm";
				break;
			case SCORINGTARS:
				gitAppli = "scoringTars";
				break;
			case HREP:
				gitAppli = "hrep";
				break;
			case ISYS:
				gitAppli = "isys";
				break;
			case AIOR12:
				gitAppli = "aior12";
				break;
			case EASYCONSULTING:
				gitAppli = "easy-consulting";
				break;
			default:
				gitAppli = "-1";
			}
		}

		return gitAppli;
	}

	/**
	 * 
	 * @param projectName
	 * @param tagName
	 * @return
	 */
	public static String findGoodProject(String projectName, String tagName) {

		String appliName = "-1";
		if (tagName != null && !tagName.equals("") && tagName.length() == 5) {
			String appliOrBdd = tagName.substring(2, 3);
			if (appliOrBdd.equalsIgnoreCase("w")) {
				appliName = projectName + "-web";
			} else if (appliOrBdd.equalsIgnoreCase("s") || appliOrBdd.equalsIgnoreCase("b")) {
				appliName = projectName + "-db";
			}
		}

		logger.debug("L'application concernés : " + appliName);

		return appliName;
	}

	/**
	 * 
	 * @param tagName
	 * @return
	 */
	public static String isAppliOrBdd(String projectName, String tagName) {
		String appliOrBdd = "-1";
		if (tagName != null && !tagName.equals("") && tagName.length() == 5) {
			appliOrBdd = tagName.substring(2, 3);
			if (appliOrBdd != null) {
				if (appliOrBdd.equalsIgnoreCase("w")) {
					appliOrBdd = projectName + "_rec";
				} else if (appliOrBdd.equalsIgnoreCase("s")) {
					appliOrBdd = "master";
				}
			}
		}
		return appliOrBdd;
	}

	/**
	 * Formatage du commentaire
	 * 
	 * @param bl bl
	 * @return commentaire formaté
	 */
	public static String buildComment(Bl bl) {
		String commentaire = bl.getCommentaire() != null ? bl.getCommentaire() + "\n" : "Pas de commentaire saisi";
		StringBuilder comment = new StringBuilder();
		comment.append("Numéro BL : " + bl.getNumeroBl() + "\n");
		comment.append("Date mise à jour : " + formatDate(bl.getUpdateDate()) + "\n");
		comment.append("Commentaire : " + commentaire);
		return comment.toString();
	}

	/**
	 * 
	 * Permet de convertir les messages en UTF8
	 * 
	 * @param codeMessageToConvert le code du message à convertir
	 * @return message en UTF8
	 *
	 */
	public static String convertMessagesToUtf8(String codeMessageToConvert) {

		String utf8 = null;
		if (codeMessageToConvert != null) {
			utf8 = new String(codeMessageToConvert.getBytes(StandardCharsets.UTF_8));
		}
		return utf8;
	}

	/**
	 * This forms the Base64 encoded string using the username and token provided by
	 * the user. This is required for HTTP Basic Authentication.
	 * 
	 * @param login login
	 * @param token token
	 * @return auth
	 */
	public static String getAuthorizationString(String login, String password, String token, boolean isJira) {
		String userLogin = null;
		String authString = null;
		Charset charset = StandardCharsets.UTF_8;
		if (isJira) {
			userLogin = login + ":" + token;
			byte[] authEncodedBytes = Base64.getEncoder().encode(userLogin.getBytes(charset));
			authString = new String(authEncodedBytes);
		} else {
			String userCredentials = login + ":" + password;
			byte[] authEncodedBytes = Base64.getEncoder().encode(userCredentials.getBytes(charset));
			authString = new String(authEncodedBytes);
		}
		return authString;
	}

	/**
	 * This forms the Base64 encoded string using the username and token provided by
	 * the user. This is required for HTTP Basic Authentication.
	 * 
	 * @param login login
	 * @param token token
	 * @return auth
	 */
	public static String getAuthorizationString(String login, String token) {
		String userLogin = null;
		String authString = null;
		Charset charset = StandardCharsets.UTF_8;
		userLogin = login + ":" + token;
		byte[] authEncodedBytes = Base64.getEncoder().encode(userLogin.getBytes(charset));
		authString = new String(authEncodedBytes);
		return authString;
	}

	/**
	 * Retourne une connection vers le serveur Git
	 * 
	 * @param urlServer
	 * @return connection
	 * @throws IOException
	 */
	public static HttpsURLConnection buildHttpConnection(String buildUrl, String urlProxy, String proxyPort,
			boolean avecProxy) throws IOException {

		HttpsURLConnection connection = buiLdProxyConnexion(buildUrl, urlProxy, proxyPort, avecProxy);
		if (connection != null) {
			connection.setRequestMethod(Const.GET_REQUEST);
			connection.setRequestProperty("Accept", Const.APPLICATION_RESPONSE);
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			connection.setRequestProperty("charset", Const.GENERAL_ENCODING);
			connection.setDoOutput(true);
		}
		return connection;
	}

	/**
	 * Date dernier commit
	 * 
	 * @param strDate
	 * @return date
	 */
	public static LocalDateTime convertStringToDate(String strDate) {

		if (strDate != null && strDate.length() >= 20) {
			strDate = strDate.substring(0, 19);
			return LocalDateTime.parse(strDate);
		}
		return null;
	}

	/**
	 * Connexion via Proxy
	 * 
	 * @param urlServer
	 * @param urlProxy
	 * @param proxyPort
	 * @return
	 * @throws IOException
	 */
	public static HttpsURLConnection buiLdProxyConnexion(String urlServer, String urlProxy, String proxyPort,
			boolean avecProxy) throws IOException {
		HttpsURLConnection connection = null;

		URL url = new URL(urlServer);
		if (avecProxy) {
			Proxy proxy = (StringUtils.isNotEmpty(urlProxy))
					? new Proxy(Proxy.Type.HTTP, new InetSocketAddress(urlProxy, Integer.parseInt(proxyPort)))
					: null;
			connection = (proxy != null) ? (HttpsURLConnection) url.openConnection(proxy)
					: (HttpsURLConnection) url.openConnection();

		} else {
			connection = (HttpsURLConnection) url.openConnection();
		}
		return connection;
	}
}