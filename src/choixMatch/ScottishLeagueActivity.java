package choixMatch;


import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ressourceAPIXml.XMLParser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.livesoccer.R;

public class ScottishLeagueActivity extends Activity{

	
	private TabHost monTabHost ;
	//= getTabHost();
	//TabHost tabHost = getTabHost();
	TabHost.TabSpec spec;
	Intent intent;
	private ScottishEquipeAdapteur mainAdapter ;
	private ScotishClassementAdapteur mainAdapterc ; 
	private ListView mainList1 ;
	private ListView mainList2 ;
	private ListView mainList3 ; 
	private ArrayList<DataEquipe> listEquipe ;
	private ArrayList<DataEquipe> listClassement ;
	private ArrayList<DataJournee> listJournee ;
	Intent itent ;

    // XML node keys
    static final String RACINE_TEAM = "Team"; // parent node
    static final String TEAM_ID = "Team_Id";
    static final String TEAM_NAME = "Name";
	private static final String RACINE_MATCH = "Match";
	private static final String EQUIPE_1 = "HomeTeam";
	private static final String EQUIPE_2 = "AwayTeam";
	private static final String SCORE_1 = "HomeGoals";
	private static final String SCORE_2 = "AwayGoals";
	private static final String DATE = "Date";
	private static final String ID_MATCH = "Id";
	private static final String ROUND = "Round";
   
	
	private JourneeAdaptateur mainAdapterj;
	//private DataEquipe data = new DataEquipe() ;
	private XMLParser parser;
	private DataEquipe data;
	private DataJournee dataj;

	
		@Override 
		public void onCreate(Bundle savedInstanceState) { 
			super.onCreate(savedInstanceState); 
			getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
			setContentView(R.layout.main_league_onglet); 
			
			
			// Récupération du TabHost 
			monTabHost =(TabHost) findViewById(R.id.TabHost01); 
			monTabHost.setup(); 
			mainList1 = (ListView)findViewById(R.id.listequipe);
			mainList2 = (ListView)findViewById(R.id.listclassement);
			mainList3 = (ListView)findViewById(R.id.journee);
			
			monTabHost.addTab(monTabHost.newTabSpec("onglet_1").setIndicator( "Equipes").setContent(R.id.Onglet1)); 
			monTabHost.addTab(monTabHost.newTabSpec("onglet_2").setIndicator( "Classement").setContent(R.id.Onglet2)); 
			monTabHost.addTab(monTabHost.newTabSpec("onglet_3").setIndicator( "Journées").setContent(R.id.Onglet3)); 
			
			//super.onCreate(savedInstanceState);
			data = new DataEquipe() ;
			this.dataj = new DataJournee() ;
			setListEquipe(new ArrayList<DataEquipe>());
			setListClassement(new ArrayList<DataEquipe>());
			setListJournee(new ArrayList<DataJournee>()) ;
			parser = new XMLParser();
			String xmlString2 = parser.getXmlFromUrl(dataj.getJourneeUrl());
			String xmlString1 = parser.getXmlFromUrl(data.getAllTeamUrl()); // getting XML
		    
		    
		    Document doc1 = parser.getDomElement(xmlString1); // getting DOM element
		    Document doc2 = parser.getDomElement(xmlString2);
		   
		    NodeList nl2 = doc2.getElementsByTagName(RACINE_MATCH);
		    NodeList nl1 = doc1.getElementsByTagName(RACINE_TEAM);
		    
		    
		   
		    buildEquipeEtClassement( nl1);
			buidJournee( nl2) ;
			
		
			//setContentView(R.layout.main_league_onglet);
			mainAdapter = new  ScottishEquipeAdapteur(listEquipe, getApplicationContext());
			mainAdapterc = new ScotishClassementAdapteur(listClassement, getApplicationContext());
			mainAdapterj = new JourneeAdaptateur(listJournee, getApplicationContext(), false) ;
			
			mainList1.setAdapter(mainAdapter);
			mainList2.setAdapter(mainAdapterc);
			mainList3.setAdapter(mainAdapterj);
			
		}
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
			
		
		
		public void setListEquipe(ArrayList<DataEquipe> listEquipe) {
			this.listEquipe = listEquipe;
		}
		
		public void setListClassement(ArrayList<DataEquipe> listClassement) {
			this.listClassement = listClassement;
		}
		public void setListJournee(ArrayList<DataJournee> listJournee) {
			this.listJournee = listJournee;
		}
	
		public  Intent getItent() {
			itent = new Intent(this, ChoixLeagueActivity.class);
			return itent;
		}
		
		private void buildEquipeEtClassement(NodeList nl){
			for(int a = 0; a < nl.getLength(); a++){			
				
				Element elt = (Element) nl.item(a);
				this.data = new DataEquipe() ;
				data .setNomEquipe(parser.getValue(elt, TEAM_NAME ));
				data.setCote(parser.getValue(elt, TEAM_ID) );
				data.setiDTeam(parser.getValue(elt, TEAM_ID) ); 
				data.setRang(String.valueOf(a)) ;
				listEquipe.add(data);
				listClassement.add(data) ;
			
			}
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
		
		public String setDateFormat(String date){
			
			// Le temps d'observation est donné sous forme d'une "époque UNIX", le nombre de secondes depuis le 1er janvier 1970
			long epoch = Long.parseLong(date);
			// getRelativeTimeSpanString transforme un temps en milisecondes en un temps relatif, par exemple "il y a une heure"
			CharSequence depuis = android.text.format.DateUtils.getRelativeTimeSpanString(epoch);
			
			return depuis.toString() ;
			
		}
		
}
