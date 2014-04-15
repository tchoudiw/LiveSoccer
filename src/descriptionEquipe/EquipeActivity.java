/**
 * 
 */
package descriptionEquipe;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.example.livesoccer.R;

import choixMatch.DataEquipe;
import choixMatch.DataJournee;
import choixMatch.JourneeAdaptateur;
import choixMatch.ScotishClassementAdapteur;
import choixMatch.ScottishEquipeAdapteur;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

/**
 * @author Live Soccer 
 *
 */
public class EquipeActivity extends Activity{
	
	private TabHost monTabHost ;
	private ScottishEquipeAdapteur mainAdapter ;
	private ScotishClassementAdapteur mainAdapterc ; 
	private ListView mainList1 ;
	private DataEquipe  equipeData  ;
	private ArrayList<DataJournee> listJournee ;
	String equipe1  ;

	
	private JourneeAdaptateur mainAdapterj;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.equipe_description_onglet); 
		
		// Récupération du TabHost 
		monTabHost =(TabHost) findViewById(R.id.TabHost02); 
		monTabHost.setup(); 
		mainList1 = (ListView)findViewById(R.id.descmatch);

		
		monTabHost.addTab(monTabHost.newTabSpec("onglet_1").setIndicator( "Match").setContent(R.id.Onglet4)); 
		monTabHost.addTab(monTabHost.newTabSpec("onglet_2").setIndicator( "Description").setContent(R.id.Onglet5));  
		
		super.onCreate(savedInstanceState);

		setListJournee(new ArrayList<DataJournee>()) ;
		
		for(int a = 0; a < 30; a++)
		{			
			
			Date date = new Date();
			String equipe2 = "equipe" + a ;
			
			String score = "1";
			//DataEquipe data = new DataEquipe() ;
			DataJournee dataj = new DataJournee() ;

			this.setEquipe1("equipe1");
			dataj.setEquipe1(equipe1) ;
			dataj.setEquipe2(equipe2) ;
			dataj.setScore1(score);
			dataj.setScore2(score);
			dataj.setPeriode(date) ;
			dataj.setNumJournee(a);
			dataj.setEtat("etat");
			listJournee.add(dataj);
			
			
		}
	

		mainAdapterj = new JourneeAdaptateur(listJournee) ;
		
		mainList1.setAdapter(mainAdapterj);

		
	}


	public void setListJournee(ArrayList<DataJournee> listJournee) {
		this.listJournee = listJournee;
	}
	public void setEquipe1(String equipe1) {
		this.equipe1 = equipe1;
	}

}
