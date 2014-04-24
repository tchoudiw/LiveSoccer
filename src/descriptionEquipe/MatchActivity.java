/**
 * 
 */
package descriptionEquipe;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ressourceAPIXml.XMLParser;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import choixMatch.DataJournee;

import com.example.livesoccer.R;

/**
 * @author pc-user
 *
 */
public class MatchActivity extends Activity{
	

	private DataJournee dataj = new DataJournee();
	
	private static final String RACINE_MATCH = "Match";
	private static final String EQUIPE_1 = "HomeTeam";
	private static final String EQUIPE_2 = "AwayTeam";
	private static final String SCORE_1 = "HomeGoals";
	private static final String SCORE_2 = "AwayGoals";
	private static final String DATE = "Date";


	private static final String BUT_1 = "HomeGoals";

	private static final String BUT_2 = "AwayGoals";

	private static final String CARTONJ_1 = "HomeTeamYellowCardDetails";

	private static final String CARTONJ_2 = "AwayTeamYellowCardDetails";

	private static final String CARTONR_1 = "HomeTeamRedCardDetails";

	private static final String CARTONR_2 = "AwayTeamRedCardDetails";

	private XMLParser parser;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.detail_match); 
		
		Bundle b = getIntent().getExtras();
		//String match = "" ;
		String  iDMatch = "307523" ;
		String equipe1 = ""; String moment = ""; long periode = 0;
		String equipe2 = "";		
		TextView textEquipe1 = (TextView) findViewById(R.id.equiped1);
		TextView textEquipe2 = (TextView) findViewById(R.id.equiped2);
		TextView textScore1 = (TextView) findViewById(R.id.scored1);
		TextView textScore2 = (TextView) findViewById(R.id.scored2);
		TextView textBut1= (TextView) findViewById(R.id.butd1);
		TextView textBut2= (TextView) findViewById(R.id.butd2);
		TextView textCartonj1= (TextView) findViewById(R.id.cartonjd1);
		TextView textCartonj2= (TextView) findViewById(R.id.cartonjd2);
		TextView textCartonr1= (TextView) findViewById(R.id.cartonrd1);
		TextView textCartonr2= (TextView) findViewById(R.id.cartonrd2);
		TextView textDate = (TextView) findViewById(R.id.dated);
		if(b!=null) {
			 iDMatch  = b.getString("id_match") ;
			 equipe1 = b.getString("equipe1");
			 equipe2 = b.getString("equipe2");
			 moment = b.getString("date");
			 periode = b.getLong("periode");
			 
		}
		else {
			iDMatch = "307523" ;
		}

		if( System.currentTimeMillis() <= periode){
			
			 textEquipe1.setText(equipe1);
			 textEquipe2.setText(equipe2);
			 textDate.setText(moment);
	
		}
		else{
			parser = new XMLParser();
		    String xmlJournee = parser.getXmlFromUrl(dataj.getMatchDetailUrl(iDMatch));
		    Document doc = parser.getDomElement(xmlJournee);   
		    // getting DOM element
		    NodeList nl = doc.getElementsByTagName(RACINE_MATCH );
		    
		    Element elt = (Element) nl.item(0);
		    textEquipe1.setText(parser.getValue(elt, EQUIPE_1 ));
		    textEquipe2.setText(parser.getValue(elt, EQUIPE_2 ));
			textScore1.setText(parser.getValue(elt, SCORE_1));
			textScore2.setText(parser.getValue(elt, SCORE_2));
			textBut1.setText(parser.getValue(elt, BUT_1));
			textBut2.setText(parser.getValue(elt, BUT_2));
			textCartonj1.setText(parser.getValue(elt, CARTONJ_1));
			textCartonj2.setText(parser.getValue(elt, CARTONJ_2));
			textCartonr1.setText(parser.getValue(elt, CARTONR_1));
			textCartonr2.setText(parser.getValue(elt, CARTONR_2));
			dataj.setPeriode(parser.getValue(elt, DATE));
			String date = dataj.getPeriode();
			textDate.setText(date);

		}
		
	}


}
