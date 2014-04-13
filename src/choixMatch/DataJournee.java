/**
 * 
 */
package choixMatch;

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
	
	
	
	public String getEquipe1() {
		return equipe1;
	}
	public void setEquipe1(String equipe1) {
		this.equipe1 = equipe1;
	}
	public String getEquipe2() {
		return equipe2;
	}
	public void setEquipe2(String equipe2) {
		this.equipe2 = equipe2;
	}
	public String getScore1() {
		return score1;
	}
	public void setScore1(String score1) {
		this.score1 = score1;
	}
	public String getScore2() {
		return score2;
	}
	public void setScore2(String score2) {
		this.score2 = score2;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Date getPeriode() {
		return periode;
	}
	public void setPeriode(Date periode) {
		this.periode = periode;
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
	public void setNumJournee(int  numJournee) {
		this.numJournee = "Journee N° "+ numJournee;
	}



	/**
	 * Equipe 1 
	 */
	private String equipe1 ;
	/**
	 * Equipe 1 
	 */
	private String equipe2 ;
	
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


	private Date periode ;

	/**
	 * titre constant des entetes de journee
	 */
	final int intitulle []= {R.string.equipe,
							R.string.score, 
							R.string.paris,
							R.drawable.barre,
							R.drawable.favoris};
	/**
	 * Numéro de la journee 
	 */
	private String numJournee ;
	
	
}
