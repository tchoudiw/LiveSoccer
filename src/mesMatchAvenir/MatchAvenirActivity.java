package mesMatchAvenir;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ressourceAPIXml.XMLParser;
import choixMatch.ChoixLeagueActivity;
import choixMatch.DataEquipe;
import choixMatch.DataJournee;
import choixMatch.JourneeAdaptateur;
import choixMatch.ScotishClassementAdapteur;
import choixMatch.ScottishEquipeAdapteur;

import com.example.livesoccer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.ListView;
import android.widget.TabHost;

/**
 * @author team Live Soccer
 *
 */
public class MatchAvenirActivity extends Activity {

	private TabHost mavTabHost01 ;
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
		setContentView(R.layout.mav_onglet); 
			
		// Récupération du TabHost 
		mavTabHost01 =(TabHost) findViewById(R.id.mav_TabHost01); 
		mavTabHost01.setup(); 
		
		mainList1 = (ListView)findViewById(R.id.mav_journeeF);
		mainList2 = (ListView)findViewById(R.id.mav_journeeS);
		mainList3 = (ListView)findViewById(R.id.mav_journeeP);
		
		mavTabHost01.addTab(mavTabHost01.newTabSpec("onglet_1").setIndicator( "Matchs").setContent(R.id.mav_Onglet1)); 
		mavTabHost01.addTab(mavTabHost01.newTabSpec("onglet_2").setIndicator( "Equipes Suivies").setContent(R.id.mav_Onglet2)); 
		mavTabHost01.addTab(mavTabHost01.newTabSpec("onglet_3").setIndicator( "Mes Paris").setContent(R.id.mav_Onglet3)); 
		
		
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
			dataj.setEtat("etat");
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
		

}

