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
	public double getCote() {
		return cote;
	}
	/**
	 * setter
	 * @param cote <code>double</code> 
	 */
	public void setCote(double cote) {
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
	public int getPoint() {
		return point;
	}


	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}
	/**
	 * @return the rang
	 */
	public int getRang() {
		return rang;
	}


	/**
	 * @param rang the rang to set
	 */
	public void setRang(int rang) {
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
	/**
	 * <code>String</string> name of the team 
	 */
	private  String nomEquipe = "";
	
	/**
	 * Cote of the team 
	 */
	private double cote = 0;
	
	/**
	 * Rank of the team 
	 */
	private int rang = 1;
	
	/**
	 * Score of the the team 
	 */
	private int point = 0;
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
	
	
	
	
	



}
