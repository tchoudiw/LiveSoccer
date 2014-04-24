package mesEquipesSuivies;


import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ressourceAPIXml.XMLParser;

import choixMatch.ChoixAdapter;
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


public class EquipeSuivieActivity extends Activity {

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
    static final String RACINE_TEAM = "Team"; // parent node
    static final String TEAM_ID = "Team_Id";
    static final String TEAM_NAME = "Name";
	
   
	

	private DataEquipe data;
	

//TODO equipe suivies Activity
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.equipe_suivie_main); 
		
		
		// Récupération du TabHost 
		//monTabHost =(TabHost) findViewById(R.id.TabHost01); 
		//monTabHost.setup(); 
		//mainList1 = (ListView)findViewById(R.id.listequipe);
		mainList2 = (ListView)findViewById(R.id.listsuivie);
		//mainList3 = (ListView)findViewById(R.id.journee);
		
//		monTabHost.addTab(monTabHost.newTabSpec("onglet_1").setIndicator( "Equipes").setContent(R.id.Onglet1)); 
//		monTabHost.addTab(monTabHost.newTabSpec("onglet_2").setIndicator( "Classement").setContent(R.id.Onglet2)); 
//		monTabHost.addTab(monTabHost.newTabSpec("onglet_3").setIndicator( "Journées").setContent(R.id.Onglet3)); 
		
		//super.onCreate(savedInstanceState);
		data = new DataEquipe() ;
		
		
		//setListEquipe(new ArrayList<DataEquipe>());
		setListClassement(new ArrayList<DataEquipe>());
		//setListJournee(new ArrayList<DataJournee>()) ;
		int point = 80 ;
		for(int a = 0; a < 30; a++)
		{			
			
			//int random_int = r.nextInt(8);
			String nomEquipe = "teamSottsh" + a ;
			//Date date = new Date();
//			String equipe1 = "equipe" + a ;
//			String equipe2 = equipe1 + a+1 ;
			//String score = "1";
			//DataEquipe data = new DataEquipe() ;
			//DataJournee dataj = new DataJournee() ;
			data.setNomEquipe(nomEquipe);
			//data.setCote(random_int) ;
			data.setRang(String.valueOf(a)) ;
			data.setPoint(String.valueOf(point)) ;
//			dataj.setEquipe1(equipe1) ;
//			dataj.setEquipe2(equipe2) ;
//			dataj.setScore1(score);
//			dataj.setScore2(score);
//			dataj.setPeriode("2014-05-10T03:15:00-08:00") ;
//			dataj.setNumJournee("Journeé N° "+ String.valueOf(a));
//			dataj.setEtat("etat");
			//listEquipe.add(data);
			listClassement.add(data) ;
			//listJournee.add(dataj);
			point-- ;
		}
	
		//setContentView(R.layout.main_league_onglet);
		//mainAdapter = new  ScottishEquipeAdapteur(listEquipe, getApplicationContext());
		mainAdapterc = new ScotishClassementAdapteur(listClassement, getApplicationContext());
		//mainAdapterj = new JourneeAdaptateur(listJournee, getApplicationContext()) ;
		
		//mainList1.setAdapter(mainAdapter);
		mainList2.setAdapter(mainAdapterc);
		//mainList3.setAdapter(mainAdapterj);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
		
	
	

	
	public void setListClassement(ArrayList<DataEquipe> listClassement) {
		this.listClassement = listClassement;
	}
	


}


