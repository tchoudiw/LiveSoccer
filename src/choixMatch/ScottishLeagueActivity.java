package choixMatch;


import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import com.example.livesoccer.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;

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
	private DataEquipe  equipeData  ;
	private ArrayList<DataEquipe> listEquipe ;
	private ArrayList<DataEquipe> listClassement ;
	private ArrayList<DataJournee> listJournee ;
	Intent itent ;
	//= new Intent(ChoixLeagueActivity.this, ScottishLeagueActivity.class);
	//startActivity(i);

	
	private JourneeAdaptateur mainAdapterj;


//	private class MainListOnItemClick implements OnItemClickListener{
//		@Override
//		public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
//			switch(position)
//			{
//			//TODO Leaugue 1.1
//				case 0:
//				{
//				Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_SHORT).show();
//					break;
//				}
//				
//				default:
//					Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_SHORT).show();
//					
//			}
//			
//			
//		}
//	
//	}	
	

	

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
//			Button bout = (Button)findViewById(R.id.suivrej);
//			bout.setBackgroundColor(Color.argb(255, 50, 200, 0));
//			bout.setTextColor(Color.argb(255, 255, 255, 255));
//			 LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//	         View view = inflater.inflate(R.layout.journee, null);
//	         Button bout = (Button)view.findViewById(R.id.suivrej);
//	         bout.setBackgroundColor(Color.argb(255, 50, 200, 0));
			
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
			mainAdapterc = new ScotishClassementAdapteur(listClassement, getApplicationContext());
			mainAdapterj = new JourneeAdaptateur(listJournee, getApplicationContext()) ;
			
			mainList1.setAdapter(mainAdapter);
			mainList2.setAdapter(mainAdapterc);
			mainList3.setAdapter(mainAdapterj);
			//mainList1.setOnItemClickListener(new MainListOnItemClick());
//			mainList2.setOnItemClickListener(new MainListOnItemClick());
//			mainList3.setOnItemClickListener(new MainListOnItemClick());
		
			
		}
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
		
//		 public void myClickHandler(View v) 
//		    {
//		    	  
//		    	//reset all the listView items background colours before we set the clicked one..
////		    	ListView lvItems = getListView();
////		    	for (int i=0; i<lvItems.getChildCount(); i++) 
////		    	{
////					lvItems.getChildAt(i).setBackgroundColor(Color.BLUE);		
////				}
////		    	
//		    	
//		    	//get the row the clicked button is in
//		    	LinearLayout vwParentRow = (LinearLayout)v.getParent();
//		 		
//		    	TextView child = (TextView)vwParentRow.getChildAt(0);
//		    	Button btnChild = (Button)vwParentRow.getChildAt(1);
//		    	btnChild.setText(child.getText());
//		    	btnChild.setText("I've been clicked!");
//		    	
//				int c = Color.CYAN;
//				
//				vwParentRow.setBackgroundColor(c); 
//				vwParentRow.refreshDrawableState();
//		    	
//		    }
//		
		
		
		
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
