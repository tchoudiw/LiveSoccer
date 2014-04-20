/**
 * 
 */
package descriptionEquipe;

import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ressourceAPIXml.XMLParser;

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
	
	private static final String RACINE = "Team";
	private static final String PAYS = "Country";
	private static final String STADE = "Stadium";
	private static final String WEBSITE = "Website";
	private TabHost monTabHost ;
	private ListView mainList1 ;
	private DataEquipe data = new DataEquipe();
	private ArrayList<DataJournee> listJournee ;


	
	private JourneeAdaptateur mainAdapterj;
	private XMLParser parser;
	
	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.equipe_description_onglet); 
		monTabHost =(TabHost) findViewById(R.id.TabHost02); 
		monTabHost.setup(); 
		mainList1 = (ListView)findViewById(R.id.descmatch);
		monTabHost.addTab(monTabHost.newTabSpec("onglet_4").setIndicator( "Match").setContent(R.id.Onglet4)); 
		monTabHost.addTab(monTabHost.newTabSpec("onglet_5").setIndicator( "Description").setContent(R.id.Onglet5)); 
		
	
		Bundle b = getIntent().getExtras();
		String equipe = "" ;
		String  Id = "" ;
		TextView textEquipe = (TextView) findViewById(R.id.nomEquipe);
		TextView textPays = (TextView) findViewById(R.id.textpays);
		TextView textStade = (TextView) findViewById(R.id.textstade);
		TextView textWebSite = (TextView) findViewById(R.id.textwebsite);
		if(b!=null) {
			equipe= b.getString("equipe");
			Id = b.getString("id") ;
		}
		else {
			equipe="blublbulub";}
		
		Toast.makeText(this,"equipe = "+equipe, Toast.LENGTH_LONG).show();
		
		parser = new XMLParser();
	    String xml = parser.getXmlFromUrl(data.getTeamUrl(Id)); // getting XML
	    Document doc = parser.getDomElement(xml); // getting DOM element
	    NodeList nl = doc.getElementsByTagName(RACINE);
	    Element elt = (Element) nl.item(0);


	    textEquipe.setText(equipe);
		textPays.setText(parser.getValue(elt, PAYS ));
		textStade.setText(parser.getValue(elt, STADE )); 
		textWebSite.setText(parser.getValue(elt, WEBSITE )); 
		

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
