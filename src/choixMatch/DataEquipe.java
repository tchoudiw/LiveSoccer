/**
 * 
 */
package choixMatch;

import java.util.Date;

import com.example.livesoccer.R;


/**
 * @author team Live Soccer
 *
 */
public class DataEquipe {

	/**
	 * Constructor
	 */
	public DataEquipe() {
		
		getAllTeamUrl() ;
	}
	
	
	/**
	 * getter 
	 * @return teamName 
	 */
	public String getNomEquipe() {
		return nomEquipe;
	}
	/**
	 * Setter
	 * @param teamName <code>String</string>
	 */
	public void setNomEquipe(String teamName) {
		this.nomEquipe = teamName;
	}
	/**
	 * getter
	 * @return <code>double</code> cote 
	 */
	public String getCote() {
		return cote;
	}
	/**
	 * setter
	 * @param cote <code>double</code> 
	 */
	public void setCote(String cote) {
		this.cote = cote;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the point
	 */
	public String getPoint() {
		return point;
	}


	/**
	 * @param point the point to set
	 */
	public void setPoint(String point) {
		this.point = point;
	}
	/**
	 * @return the rang
	 */
	public String getRang() {
		return rang;
	}


	/**
	 * @param rang the rang to set
	 */
	public void setRang(String rang) {
		this.rang = rang;
	}
	/**
	 * @return the favori
	 */
	public int getFavori() {
		return favori;
	}


	/**
	 * @param favori the favori to set
	 */
	public void setFavori(int favori) {
		this.favori = favori;
	}
	
	
	
	/**
	 * Méthode pour recevoir L'URI de qui donne  toutes les équipes 
	 * @return allTeamUrl
	 */
	public String getAllTeamUrl(){
		final String allTeamUrl = "http://www.xmlsoccer.com/FootballDataDemo.asmx/" +
				"GetAllTeams?ApiKey=JDEJMCUNYSKUWXJTSDVLCGMEHDGPUVNURWFNRLILWEDEGUPQKR";
		
		return allTeamUrl ;
	}
	
	public String getTeamUrl(String Id){
		
		final String team = "http://www.xmlsoccer.com/FootballDataDemo.asmx/" +
				"GetTeam?ApiKey=JDEJMCUNYSKUWXJTSDVLCGMEHDGPUVNURWFNRLILWEDEGUPQKR&teamName=" + Id ;
		
		return team ;
	}
	
	/**
	 * @return the iDTeam
	 */
	public String getiDTeam() {
		return iDTeam;
	}


	/**
	 * @param iDTeam the iDTeam to set
	 */
	public void setiDTeam(String iDTeam) {
		this.iDTeam = iDTeam;
	}

	/**
	 * <code>String</string> name of the team 
	 */
	private  String nomEquipe = "Rangers";
	
	/**
	 * Cote of the team 
	 */
	private String cote = "1";
	
	/**
	 * Rank of the team 
	 */
	private String rang = "1";
	
	/**
	 * Score of the the team 
	 */
	private String point = "0";
	/**
	 * favorite icon 
	 */
	private int favori = R.drawable.favoris;
	/**
	 * check box equipe 
	 */

	final int favoriAndCheck []= {R.drawable.favoris,
									R.id.checkEquipe };
	/**
	 * Date manegement 
	 */
	private Date date = null ;
	/**
	 * ville de l'équipe 
	 */
	private String iDTeam = "50"; 
	
	
	
	
	



}
