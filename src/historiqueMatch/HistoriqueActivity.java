package historiqueMatch;

import com.example.livesoccer.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ressourceAPIXml.XMLParser;
import android.content.Intent;
import android.view.Window;
import android.widget.ListView;
import android.widget.TabHost;

import choixMatch.ChoixLeagueActivity;
import choixMatch.DataEquipe;
import choixMatch.DataJournee;
import choixMatch.JourneeAdaptateur;
import choixMatch.ScotishClassementAdapteur;
import choixMatch.ScottishEquipeAdapteur;

/**
 * 
 * @author team Live Soccer
 *
 */
public class HistoriqueActivity extends Activity {
private TabHost hmTabHost01 ;
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

		  // All static variables
	static final String URL = "http://api.androidhive.info/pizza/?format=xml";
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

	private JourneeAdaptateur mainAdapterj;
	//private DataEquipe data = new DataEquipe() ;
	private XMLParser parser;
	private DataEquipe data;
	private DataJournee dataj;
		
		@Override 
		public void onCreate(Bundle savedInstanceState) { 
			super.onCreate(savedInstanceState); 
			getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
			setContentView(R.layout.historique_match); 
				
			// Récupération du TabHost 
			hmTabHost01 =(TabHost) findViewById(R.id.hm_TabHost01); 
			hmTabHost01.setup(); 
			
			mainList1 = (ListView)findViewById(R.id.hm_journeeF);
			mainList2 = (ListView)findViewById(R.id.hm_journeeS);
			mainList3 = (ListView)findViewById(R.id.hm_journeeP);
			
			hmTabHost01.addTab(hmTabHost01.newTabSpec("onglet_1").setIndicator( "Matchs").setContent(R.id.hm_Onglet1)); 
			hmTabHost01.addTab(hmTabHost01.newTabSpec("onglet_2").setIndicator( "Equipes Suivies").setContent(R.id.hm_Onglet2)); 
			hmTabHost01.addTab(hmTabHost01.newTabSpec("onglet_3").setIndicator( "Mes Paris").setContent(R.id.hm_Onglet3)); 
			
			
			super.onCreate(savedInstanceState);
			
			Random r = new Random();
			
			setListEquipe(new ArrayList<DataEquipe>());
			setListClassement(new ArrayList<DataEquipe>());
			setListJournee(new ArrayList<DataJournee>()) ;
			int point = 80 ;
			for(int a = 0; a < 30; a++)
			{			
				
				int random_int = r.nextInt(8);
				String nomEquipe = "teamSottsh" + a ;
				Date date = new Date();
				String equipe1 = "equipe" + a ;
				String equipe2 = equipe1 + a+1 ;
				String score = "1";
				DataEquipe data = new DataEquipe() ;
				DataJournee dataj = new DataJournee() ;
				data.setNomEquipe(nomEquipe);
				//data.setCote(random_int) ;
				//data.setRang(a) ;
				//data.setPoint(point) ;
				dataj.setEquipe1(equipe1) ;
				dataj.setEquipe2(equipe2) ;
				dataj.setScore1(score);
				dataj.setScore2(score);
				dataj.setPeriode("2014-05-10T03:15:00-08:00") ;
				dataj.setNumJournee("Journeé N° "+ String.valueOf(a));
				//dataj.setEtat("etat");
				//listEquipe.add(data);
				//listClassement.add(data) ;
				listJournee.add(dataj);
				point-- ;
				
			}
			
			//mainAdapter = new  ScottishEquipeAdapteur(listEquipe, getApplicationContext());
			//mainAdapterc = new ScotishClassementAdapteur(listClassement, getApplicationContext());
			mainAdapterj = new JourneeAdaptateur(listJournee, getApplicationContext()) ;
			
			mainList1.setAdapter(mainAdapterj);
			mainList2.setAdapter(mainAdapterj);
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
					//data.setCote(parser.getValue(elt, TEAM_ID) );
					data.setiDTeam(parser.getValue(elt, TEAM_ID) ); 
					//data.setRang(String.valueOf(a)) ;
					listEquipe.add(data);
					//listClassement.add(data) ;
				
				}
			}
			private void buidJournee(NodeList nl){
				
				for(int a = 0; a < nl.getLength(); a++){			
					
					Element elt2 = (Element) nl.item(a);
					Date date = new Date();
		
					 this.dataj = new DataJournee() ;

					dataj.setEquipe1(parser.getValue(elt2,EQUIPE_1)) ;
					dataj.setEquipe2(parser.getValue(elt2,EQUIPE_2)) ;
			     	dataj.setScore1(parser.getValue(elt2,SCORE_1));
					dataj.setScore2(parser.getValue(elt2,SCORE_2));
					//String dt = setDateFormat(parser.getValue(elt1,DATE));
					dataj.setPeriode(parser.getValue(elt2,DATE)) ;
					dataj.setNumJournee(String.valueOf(a));
					//dataj.setEtat("etat");
						
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

