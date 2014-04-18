/**
 * 
 */
package descriptionEquipe;

import java.util.ArrayList;
import java.util.Date;

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
	
	private TabHost monTabHost ;
	private ListView mainList1 ;
	private DataEquipe data = new DataEquipe();
	private ArrayList<DataJournee> listJournee ;


	
	private JourneeAdaptateur mainAdapterj;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.equipe_description_onglet); 
		
		Bundle b = getIntent().getExtras();
		String equipe;
		TextView textEquipe = (TextView) findViewById(R.id.nomEquipe);
		
		if(b!=null) equipe= b.getString("equipe");
		else equipe="blublbulub";
		Toast.makeText(this,"equipe = "+equipe, Toast.LENGTH_LONG).show();
		textEquipe.setText(equipe);
		
		
		
		
		// Récupération du TabHost 
		monTabHost =(TabHost) findViewById(R.id.TabHost02); 
		monTabHost.setup(); 
		mainList1 = (ListView)findViewById(R.id.descmatch);

		TextView textVille = (TextView)findViewById(R.id.ville);
		TextView textEntraineur = (TextView) findViewById(R.id.entraineur);
		data.setEntraineur("Gardiola") ;
		data.setVille("London") ;
		textVille.setText(data.getVille());
		textEntraineur .setText(data.getEntraineur()); 
	 
		
		monTabHost.addTab(monTabHost.newTabSpec("onglet_4").setIndicator( "Match").setContent(R.id.Onglet4)); 
		monTabHost.addTab(monTabHost.newTabSpec("onglet_5").setIndicator( "Description").setContent(R.id.Onglet5));  
		
		super.onCreate(savedInstanceState);

		setListJournee(new ArrayList<DataJournee>()) ;
		
		for(int a = 0; a < 30; a++)
		{			
			
			Date date = new Date();
			String equipe2 = "equipe" + a ;
			
			String score = "1";
			//DataEquipe data = new DataEquipe() ;
			DataJournee dataj = new DataJournee() ;

			//this.setEquipe1("equipe1");
			dataj.setEquipe1("equipe1") ;
			dataj.setEquipe2(equipe2) ;
			dataj.setScore1(score);
			dataj.setScore2(score);
			dataj.setPeriode(date) ;
			dataj.setNumJournee(a);
			dataj.setEtat("etat");
			listJournee.add(dataj);
			
			
		}
	

		mainAdapterj = new JourneeAdaptateur(listJournee, getApplicationContext()) ;
		
		mainList1.setAdapter(mainAdapterj);

		
	}


	public void setListJournee(ArrayList<DataJournee> listJournee) {
		this.listJournee = listJournee;
	}
	

}
