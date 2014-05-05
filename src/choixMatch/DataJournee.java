/**
 * 
 */
package choixMatch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.livesoccer.R;


/**
 * @author live Soccer
 *
 */
public class DataJournee {

	
	/**
	 */
	public DataJournee() {
		
		
	}
	
	// http://www.xmlsoccer.com/FootballDataDemo.asmx/GetFixturesByDateIntervalAndLeague?league=Scottish&ApiKey=JDEJMCUNYSKUWXJTSDVLCGMEHDGPUVNURWFNRLILWEDEGUPQKR&startDateString=2012-01-01&endDateString=2015-01-01
	





	


	/**
	 * @return the equipe1
	 */
	public String getEquipe1() {
		return equipe1;
	}
	/**
	 * @param equipe1 the equipe1 to set
	 */
	public void setEquipe1(String equipe1) {
		this.equipe1 = equipe1;
	}

	/**
	 * @return the equipe2
	 */
	public String getEquipe2() {
		return equipe2;
	}

	/**
	 * @param equipe2 the equipe2 to set
	 */
	public void setEquipe2(String equipe2) {
		this.equipe2 = equipe2;
	}

	/**
	 * @return the score1
	 */
	public String getScore1() {
		return score1;
	}

	/**
	 * @param score1 the score1 to set
	 */
	public void setScore1(String score1) {
		this.score1 = score1;
	}

	/**
	 * @return the score2
	 */
	public String getScore2() {
		return score2;
	}

	/**
	 * @param score2 the score2 to set
	 */
	public void setScore2(String score2) {
		this.score2 = score2;
	}

	/**
	 * @return the etat
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * @param etat the etat to set
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * @return the periode
	 */
	public String getPeriode() {
		
		return periode;
	}

	/**
	 * @param date the periode to set
	 */
	public void setPeriode(String date) {
		String [] dateSplit = date.split("T");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateEvenement = null;
		try {
			dateEvenement = sdf.parse(dateSplit[0] + " " + dateSplit[1] + ":" + 00);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Long dateMatch = dateEvenement.getTime();
		
		CharSequence depuis = android.text.format.DateUtils.getRelativeTimeSpanString(dateMatch);
		String dateDepuis = depuis.toString();
		
		this.periode = dateDepuis;
		this.dateMatch = dateMatch;
	}

	public long getDateMatch(){
		return dateMatch;
	}

	/**
	 * @return the numJournee
	 */
	public String getNumJournee() {
		return numJournee;
	}

	/**
	 * @param numJournee the numJournee to set
	 */
	public void setNumJournee(String numJournee) {
		this.numJournee = numJournee;
	}
	
	/**
	 * Journee d'une équipe donnée 
	 * @return journee URL 
	 */
	public String getJourneeUrl(){
		
		final String journee = "http://www.xmlsoccer.com/FootballDataDemo.asmx/GetFixturesByDateIntervalAndLeague?&" +
				"league=Scottish&ApiKey=JDEJMCUNYSKUWXJTSDVLCGMEHDGPUVNURWFNRLILWEDEGUPQKR&" +
				"startDateString="+dateDebut + "&endDateString=" + dateFin ;
		
		return journee ;
	}
	
	/**
	 * Historique Journee d'une équipe donnée 
	 * @return journee URL 
	 */
	public String getHistoriqueUrl(){
		
		final String journee = "http://www.xmlsoccer.com/FootballDataDemo.asmx/GetFixturesByDateIntervalAndLeague?&" +
				"league=Scottish&ApiKey=JDEJMCUNYSKUWXJTSDVLCGMEHDGPUVNURWFNRLILWEDEGUPQKR&" +
				"startDateString="+dateDebut + "&endDateString=" + getTodayDate() ;
		
		return journee ;
	}
		
	/**
	 * Avenir Journee d'une équipe donnée 
	 * @return journee URL 
	 */
	public String getAvenirUrl(){
		
		final String journee = "http://www.xmlsoccer.com/FootballDataDemo.asmx/GetFixturesByDateIntervalAndLeague?&" +
				"league=Scottish&ApiKey=JDEJMCUNYSKUWXJTSDVLCGMEHDGPUVNURWFNRLILWEDEGUPQKR&" +
				"startDateString="+incrementDate(new Date()) + "&endDateString=" + dateFin ;
		
		return journee ;
	}
	
	/**
	 * Actuelle Journee d'une équipe donnée 
	 * @return journee URL 
	 */
	public String getActuelleUrl(){
		
		final String journee = "http://www.xmlsoccer.com/FootballDataDemo.asmx/GetFixturesByDateIntervalAndLeague?&" +
				"league=Scottish&ApiKey=JDEJMCUNYSKUWXJTSDVLCGMEHDGPUVNURWFNRLILWEDEGUPQKR&" +
				"startDateString="+getTodayDate() + "&endDateString=" + incrementDate(new Date());
		
		return journee ;
	}	

/**
 * URL de toutes les journées 
 * @param idEquipe
 * @return
 */
public String getTeamJourneeUrl(String idEquipe){
	 final String  journeeEquipe = "http://www.xmlsoccer.com/FootballDataDemo.asmx/GetFixturesByDateIntervalAndTeam?" +
	 		"ApiKey=JDEJMCUNYSKUWXJTSDVLCGMEHDGPUVNURWFNRLILWEDEGUPQKR" +
	 		"&startDateString="+dateDebut + "&endDateString=" + dateFin  +"&teamId=" +idEquipe ; 
	return journeeEquipe ;
}
	
	
	/**
	 * méthode pour URL des détail d'un match
	 * @return 
	 */
	public String getMatchDetailUrl(String idMatch){
		final String matchDetail = "http://www.xmlsoccer.com/FootballDataDemo.asmx/GetHistoricMatchesByFixtureMatchID?" +
				"ApiKey=JDEJMCUNYSKUWXJTSDVLCGMEHDGPUVNURWFNRLILWEDEGUPQKR&Id=" + idMatch ;
		return matchDetail ;
		
	}
	/**
	 * @return the idMatch
	 */
	public String getIdMatch() {
		return idMatch;
	}

	/**
	 * @param idMatch the idMatch to set
	 */
	public void setIdMatch(String idMatch) {
		this.idMatch = idMatch;
	}
	
	/**
	 * @return today date 
	 */
	public static  String getTodayDate(){
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return simple.format(date).toString() ;
	}
	
	public static String incrementDate(Date date){
		Calendar calendrier = Calendar.getInstance();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		calendrier.setTime(date);
		calendrier.add(Calendar.DATE, 1);
		Date dateInc = calendrier.getTime();
		return simple.format(dateInc).toString() ;
	}


	/**
	 * Equipe 1 
	 */
	private String equipe1 = "Celtic";
	/**
	 * Equipe 1 
	 */
	private String equipe2 = "Rangers";
	
	/**
	 * Score de l'équipe 2
	 */
	private String score1 ;
	/**
	 * score de l'équipe 1 
	 */
	private String score2 ;
	/**
	 * Etat de la journee soit passee soit en cours soit en à venir 
	 */
	private String etat ;
	/**
	 * periode de l'évènement 
	 */

	private String periode ;
	
	/**
	 * Id DU MATCH
	 */
	private String idMatch = "307523";
	
	/**
	 * date du match en format long
	 */
	private long dateMatch;

	/**
	 * titre constant des entetes de journee
	 */
	final int intitulle []= {R.string.equipe,
							R.string.score, 
							R.string.paris,
							R.drawable.barre,
							R.drawable.favoris,
							R.id.checkj1,
							R.id.checkj2,
							R.id.suivrej};
	/**
	 * Numéro de la journee 
	 */
	private String numJournee ;
	
	
	private final String dateDebut="2013-09-01";
	private final String dateFin = "2015-01-01";
	
	public  boolean buttonState = false ;
	public boolean checkBoxState1 [] ;
	public boolean checkBoxState2 [];

	
	
}
