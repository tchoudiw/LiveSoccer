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

	  // All static variables
    static final String URL = "http://api.androidhive.info/pizza/?format=xml";
    // XML node keys
    static final String KEY_ITEM = "Team"; // parent node
    static final String KEY_ID = "Team_Id";
    static final String KEY_NAME = "Name";
    static final String KEY_COST = "cost";
    static final String KEY_DESC = "description";
	
	private JourneeAdaptateur mainAdapterj;
	//private DataEquipe data = new DataEquipe() ;
	private XMLParser parser;

	
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
			DataEquipe dat = new DataEquipe() ;
			setListEquipe(new ArrayList<DataEquipe>());
			setListClassement(new ArrayList<DataEquipe>());
			setListJournee(new ArrayList<DataJournee>()) ;
			parser = new XMLParser();
		    String xml = parser.getXmlFromUrl(dat.getAllTeamUrl()); // getting XML
		    Document doc = parser.getDomElement(xml); // getting DOM element
		     
		     NodeList nl = doc.getElementsByTagName(KEY_ITEM);
	     
	
			for(int a = 0; a < nl.getLength(); a++)
			{			
				
				Element elt = (Element) nl.item(a);
				Date date = new Date();
				String equipe1 = "equipe" + a ;
				String equipe2 = equipe1 + a+1 ;
				String score = "1";
				DataEquipe data = new DataEquipe() ;
				DataJournee dataj = new DataJournee() ;
				
				
				data .setNomEquipe(parser.getValue(elt, KEY_NAME ));
				data.setCote(parser.getValue(elt, KEY_ID) );
				data.setiDTeam(parser.getValue(elt, KEY_ID) ); 
				data.setRang(String.valueOf(a)) ;
				//data.setPoint(parser.getValue(elt, KEY_COST)) ;
				
		
				
				
				dataj.setEquipe1(equipe1) ;
				dataj.setEquipe2(equipe2) ;
				dataj.setScore1(score);
				dataj.setScore2(score);
				dataj.setPeriode(date) ;
				dataj.setNumJournee(a);
				dataj.setEtat("etat");
				
				 
				
				
				listEquipe.add(data);
				listClassement.add(data) ;
				listJournee.add(dataj);

				
			}
			
		
			//setContentView(R.layout.main_league_onglet);
			mainAdapter = new  ScottishEquipeAdapteur(listEquipe, getApplicationContext());
			mainAdapterc = new ScotishClassementAdapteur(listClassement, getApplicationContext());
			mainAdapterj = new JourneeAdaptateur(listJournee, getApplicationContext()) ;
			
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
}
