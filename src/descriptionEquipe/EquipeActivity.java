/**
 * 
 */
package descriptionEquipe;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ressourceAPIXml.XMLParser;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import choixMatch.DataEquipe;
import choixMatch.DataJournee;
import choixMatch.JourneeAdaptateur;

import com.example.livesoccer.R;

/**
 * @author Live Soccer 
 *
 */
public class EquipeActivity extends Activity{
	
	private static final String RACINE = "Team";
	private static final String PAYS = "Country";
	private static final String STADE = "Stadium";
	private static final String WEBSITE = "Website";
	private TabHost monTabHost ;
	private ListView mainList1 ;
	private DataEquipe data = new DataEquipe();
	private DataJournee dataj = new DataJournee();
	private ArrayList<DataJournee> listJournee ;
	
	private static final String RACINE_MATCH = "Match";
	private static final String EQUIPE_1 = "HomeTeam";
	private static final String EQUIPE_2 = "AwayTeam";
	private static final String SCORE_1 = "HomeGoals";
	private static final String SCORE_2 = "AwayGoals";
	private static final String DATE = "Date";
	private static final String ID_MATCH = "Id";
	private static final String ROUND = "Round";

	
	private JourneeAdaptateur mainAdapterj;
	private XMLParser parser;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.equipe_description_onglet); 
		monTabHost =(TabHost) findViewById(R.id.TabHost02); 
		monTabHost.setup(); 
		mainList1 = (ListView)findViewById(R.id.descmatch);
		monTabHost.addTab(monTabHost.newTabSpec("onglet_4").setIndicator( "Match").setContent(R.id.Onglet4)); 
		monTabHost.addTab(monTabHost.newTabSpec("onglet_5").setIndicator( "Description").setContent(R.id.Onglet5)); 
		
	
		Bundle b = getIntent().getExtras();
		String equipe = "" ;
		String  IdEquipe = "" ;
		TextView textEquipe = (TextView) findViewById(R.id.nomEquipe);
		TextView textPays = (TextView) findViewById(R.id.textpays);
		TextView textStade = (TextView) findViewById(R.id.textstade);
		TextView textWebSite = (TextView) findViewById(R.id.textwebsite);
		
		if(b!=null) {
			 equipe= b.getString("equipe");
			 IdEquipe = b.getString("id_Equipe") ;
		}
		else {
			equipe="blublbulub";}
		
		Toast.makeText(this,"equipe = "+equipe, Toast.LENGTH_LONG).show();
		
		setListJournee(new ArrayList<DataJournee>()) ;
		parser = new XMLParser();
	    String xmlEquipe = parser.getXmlFromUrl(data.getTeamUrl(IdEquipe)); // getting XML
	    String xmlJournee = parser.getXmlFromUrl(dataj.getTeamJourneeUrl(IdEquipe));
	    Document doc = parser.getDomElement(xmlEquipe);
	    
	    // getting DOM element
	    NodeList nl = doc.getElementsByTagName(RACINE);
	    
	    
	    Element elt = (Element) nl.item(0);
	    textEquipe.setText(equipe);
		textPays.setText(parser.getValue(elt, PAYS ));
		textStade.setText(parser.getValue(elt, STADE )); 
		textWebSite.setText(parser.getValue(elt, WEBSITE )); 
		
		//setListJournee(new ArrayList<DataJournee>()) ;
		Document doc2 = parser.getDomElement(xmlJournee);
		NodeList nl2 = doc2.getElementsByTagName(RACINE_MATCH);
		buidJournee( nl2) ;
		mainAdapterj = new JourneeAdaptateur(listJournee, getApplicationContext(), false) ;
		
		mainList1.setAdapter(mainAdapterj);

		
	}

	
	private void buidJournee(NodeList nl){
		
		for(int a = 0; a < nl.getLength(); a++){			
			
			Element elt2 = (Element) nl.item(a);
			 this.dataj = new DataJournee() ;


			dataj.setEquipe1(parser.getValue(elt2,EQUIPE_1)) ;
			dataj.setEquipe2(parser.getValue(elt2,EQUIPE_2)) ;
			dataj.setScore1(parser.getValue(elt2,SCORE_1));
			dataj.setScore2(parser.getValue(elt2,SCORE_2));
			dataj.setIdMatch(parser.getValue(elt2,ID_MATCH));
			//String dt = setDateFormat(parser.getValue(elt1,DATE));
			dataj.setPeriode(parser.getValue(elt2,DATE)) ;
			String jour = "Journeé N° "+ parser.getValue(elt2,ROUND)  ;
			dataj.setNumJournee(jour);
			dataj.setEtat("etat");
				

			listJournee.add(dataj);

			
		}
		
	}
	
	
	

	public void setListJournee(ArrayList<DataJournee> listJournee) {
		this.listJournee = listJournee;
	}
	

}
