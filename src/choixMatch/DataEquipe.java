/**
 * 
 */
package choixMatch;

/**
 * @author team Live Soccer
 *
 */
public class DataEquipe {

	/**
	 * Constructor
	 * @param teamName
	 * @param cote
	 */
	public DataEquipe(String teamName, double cote) {
		this.teamName = teamName;
		this.cote = cote;
	}
	
	
	/**
	 * getter 
	 * @return teamName 
	 */
	public String getTeamName() {
		return teamName;
	}
	/**
	 * Setter
	 * @param teamName <code>String</string>
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
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
	 * <code>String</string> name of the team 
	 */
	private  String teamName ;
	
	/**
	 * Cote of the team 
	 */
	private double cote ;



}
