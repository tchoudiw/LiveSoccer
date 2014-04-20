/**
 * 
 */
package choixMatch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
		
		initialiseUrl() ;
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
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}


	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @return the entraineur
	 */
	public String getEntraineur() {
		return entraineur;
	}


	/**
	 * @param entraineur the entraineur to set
	 */
	public void setEntraineur(String entraineur) {
		this.entraineur = entraineur;
	}
	
	
	private void initialiseUrl(){
		final String allTeamUrl = "http://www.xmlsoccer.com/FootballDataDemo.asmx/" +
				"GetAllTeams?ApiKey=JDEJMCUNYSKUWXJTSDVLCGMEHDGPUVNURWFNRLILWEDEGUPQKR";
		
		urlContent.put("allTeam", allTeamUrl) ;
	}
	/**
	 * <code>String</string> name of the team 
	 */
	private  String nomEquipe = "";
	
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
	private int checkEquipe = R.id.checkEquipe;
	final int favoriAndCheck []= {R.drawable.favoris,
									R.id.checkEquipe };
	/**
	 * Date manegement 
	 */
	private Date date = null ;
	/**
	 * ville de l'équipe 
	 */
	private String ville ; 
	/**
	 * nom de l'entraineur 
	 */
	private String entraineur ;
	
	Map<String, String > urlContent = new HashMap<String , String>();
	
	
	
	



}
