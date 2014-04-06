package choixMatch;


import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import com.example.livesoccer.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;

public class ScottishLeagueActivity extends Activity{

	
	private TabHost monTabHost;
	private ScottishEquipeAdapteur mainAdapter;
	private ListView mainList1;
	private ListView mainList2;
	private ListView mainList3; 
	private DataEquipe  equipeData ;
	private ArrayList<DataEquipe> listEquipe;


	private class MainListOnItemClick implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
			switch(position)
			{
			//TODO Leaugue 1.1
//				case 0:
//				{
//					Intent i = new Intent(ChoixLeagueActivity.this, ScottishLeagueActivity.class);
//					startActivity(i);
//					break;
//				}
				
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
			mainList2 = (ListView)findViewById(R.id.classement);
			mainList3 = (ListView)findViewById(R.id.journee);
			
			monTabHost.addTab(monTabHost.newTabSpec("onglet_1").setIndicator( "Equipes").setContent(R.id.Onglet1)); 
			monTabHost.addTab(monTabHost.newTabSpec("onglet_2").setIndicator( "Classement").setContent(R.id.Onglet2)); 
			monTabHost.addTab(monTabHost.newTabSpec("onglet_3").setIndicator( "Journées").setContent(R.id.Onglet3)); 
			
			super.onCreate(savedInstanceState);
			
			Random r = new Random();
			
			setListEquipe(new ArrayList<DataEquipe>());
			for(int a = 0; a < 30; a++)
			{			
				int random_int = r.nextInt(4);
				String nomEquipe = "teamSottsh" + a ;
				
				listEquipe.add(new DataEquipe(nomEquipe, random_int));
			}
			
			
			//setContentView(R.layout.main_league_onglet);
			mainAdapter = new  ScottishEquipeAdapteur(listEquipe);
			
			mainList1.setAdapter(mainAdapter);
			mainList2.setAdapter(mainAdapter);
			mainList3.setAdapter(mainAdapter);
//			mainList1.setOnItemClickListener(new MainListOnItemClick());
//			mainList2.setOnItemClickListener(new MainListOnItemClick());
//			mainList3.setOnItemClickListener(new MainListOnItemClick());
		
			
		}

		public void setListEquipe(ArrayList<DataEquipe> listEquipe) {
			this.listEquipe = listEquipe;
		}

	

}
