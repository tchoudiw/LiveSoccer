package choixMatch;


import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import com.example.livesoccer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;

public class ScottishLeagueActivity extends Activity{

	
	private TabHost monTabHost ;
	private ScottishEquipeAdapteur mainAdapter ;
	private ScotishClassementAdapteur mainAdapterc ; 
	private ListView mainList1 ;
	private ListView mainList2 ;
	private ListView mainList3 ; 
	private DataEquipe  equipeData  ;
	private ArrayList<DataEquipe> listEquipe ;
	private ArrayList<DataEquipe> listClassement ;
	private ArrayList<DataJournee> listJournee ;
	 Intent itent; 
	//= new Intent(ChoixLeagueActivity.this, ScottishLeagueActivity.class);
	//startActivity(i);

	
	private JourneeAdaptateur mainAdapterj;


	private class MainListOnItemClick implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
			switch(position)
			{
			//TODO Leaugue 1.1
				case 0:
				{
				Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_SHORT).show();
					break;
				}
				
				default:
					Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_SHORT).show();
					
			}
		}
	
	}	
	

	

		@Override 
		public void onCreate(Bundle savedInstanceState) { 
			super.onCreate(savedInstanceState); 
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
				data.setCote(random_int) ;
				data.setRang(a) ;
				data.setPoint(point) ;
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
				point-- ;
				
			}
			
		
			
			//setContentView(R.layout.main_league_onglet);
			mainAdapter = new  ScottishEquipeAdapteur(listEquipe, getApplicationContext());
			mainAdapterc = new ScotishClassementAdapteur(listClassement);
			mainAdapterj = new JourneeAdaptateur(listJournee) ;
			
			mainList1.setAdapter(mainAdapter);
			mainList2.setAdapter(mainAdapterc);
			mainList3.setAdapter(mainAdapterj);
			//mainList1.setOnItemClickListener(new MainListOnItemClick());
//			mainList2.setOnItemClickListener(new MainListOnItemClick());
//			mainList3.setOnItemClickListener(new MainListOnItemClick());
		
			
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
			itent = new Intent(ScottishLeagueActivity.this, ChoixLeagueActivity.class);
			return itent;
		}
}
