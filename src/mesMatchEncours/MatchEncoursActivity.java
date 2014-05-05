package mesMatchEncours;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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
import android.widget.TextView;
import choixMatch.ChoixLeagueActivity;
import choixMatch.DataEquipe;
import choixMatch.DataJournee;
import choixMatch.JourneeAdaptateur;
import choixMatch.ScotishClassementAdapteur;
import choixMatch.ScottishEquipeAdapteur;

import com.example.livesoccer.R;

/**
 * 
 * @author team Live Soccer
 *
 */
public class MatchEncoursActivity extends Activity {
		private TabHost mmecTabHost01 ;
		private ListView mainList1 ;
		private ListView mainList2 ;
		private ListView mainList3 ; 
		static final String RACINE_TEAM = "Team"; // parent node
		static final String TEAM_ID = "Team_Id";
		static final String TEAM_NAME = "Name";
		private static final String RACINE_MATCH = "Match";
		private static final String EQUIPE_1 = "HomeTeam";
		private static final String EQUIPE_2 = "AwayTeam";
		private static final String SCORE_1 = "HomeGoals";
		private static final String SCORE_2 = "AwayGoals";
		private static final String DATE = "Date";
		private static final String ID_MATCH = "Id";
		private static final String ROUND = "Round";
		private JourneeAdaptateur mainAdapterSuivie;
		//private DataEquipe data = new DataEquipe() ;
		private XMLParser parser;
		private DataEquipe data;
		private DataJournee dataj;
		private JourneeAdaptateur mainAdapterMatch;
		private ArrayList<DataJournee> listMatch;
		private ArrayList<DataJournee> listEquipeSuivie ;
		private ArrayList<DataJournee> listPari;
		private JourneeAdaptateur mainAdapterPari;
		private ArrayList<String> listMatchSuivie = new ArrayList<String>();
		private ArrayList<String> listMatchPari = new ArrayList<String>();
		private ArrayList<String> listMatchEquipeSuivie = new ArrayList<String>();
				
		@Override 
		public void onCreate(Bundle savedInstanceState) { 
			super.onCreate(savedInstanceState); 
			getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
			setContentView(R.layout.mmec_onglet); 
				
			// Récupération du TabHost 
			mmecTabHost01 =(TabHost) findViewById(R.id.mmec_TabHost01); 
			mmecTabHost01.setup(); 
			
			mainList1 = (ListView)findViewById(R.id.mmec_journeeF);
			mainList2 = (ListView)findViewById(R.id.mmec_journeeS);
			mainList3 = (ListView)findViewById(R.id.mmec_journeeP);
			
			mmecTabHost01.addTab(mmecTabHost01.newTabSpec("onglet_1").setIndicator( "Matchs").setContent(R.id.mmec_Onglet1)); 
			mmecTabHost01.addTab(mmecTabHost01.newTabSpec("onglet_2").setIndicator( "Equipes Suivies").setContent(R.id.mmec_Onglet2)); 
			mmecTabHost01.addTab(mmecTabHost01.newTabSpec("onglet_3").setIndicator( "Mes Paris").setContent(R.id.mmec_Onglet3)); 
			TextView textEquipe1 = (TextView) findViewById(R.id.equiped1);
			

			listMatchEquipeSuivie.add("Hearts");	
			listMatchEquipeSuivie.add("Ross County");
			listMatchSuivie.add("324786");	
			listMatchSuivie.add("324787");
			listMatchPari.add("324773");	
			listMatchPari.add("324782");
	
			this.dataj = new DataJournee() ;
			//dataj.incrementDate(new Date());
			setListJournee(new ArrayList<DataJournee>()) ;
			setListMatch(new ArrayList<DataJournee> ()) ;
			setListPari(new ArrayList<DataJournee> ());
			parser = new XMLParser();
			String xmlString2 = parser.getXmlFromUrl(dataj.getActuelleUrl());
		    Document doc2 = parser.getDomElement(xmlString2);
		    NodeList nl2 = doc2.getElementsByTagName(RACINE_MATCH);
		   // NodeList nl1 = doc1.getElementsByTagName(RACINE_TEAM);
			buidJournee( nl2) ;
			mainAdapterPari = new JourneeAdaptateur(listPari, getApplicationContext(), false) ;
			mainAdapterMatch = new JourneeAdaptateur(listMatch, getApplicationContext(), false) ;
			mainAdapterSuivie = new JourneeAdaptateur(listEquipeSuivie, getApplicationContext(), false) ;
			
			mainList1.setAdapter(mainAdapterMatch);
			mainList2.setAdapter(mainAdapterSuivie);//bon
			mainList3.setAdapter(mainAdapterPari);
		}
			
			@Override
			public boolean onCreateOptionsMenu(Menu menu) {
				// Inflate the menu; this adds items to the action bar if it is present.
				getMenuInflater().inflate(R.menu.main, menu);
				return true;
			}
						
			private void buidJournee(NodeList nl){
				
				for(int a = 0; a < nl.getLength(); a++){			
					Element elt2 = (Element) nl.item(a);
					this.dataj = new DataJournee() ;
					for(String equipe : listMatchEquipeSuivie){
						
						if(parser.getValue(elt2,EQUIPE_1).equals(equipe) || parser.getValue(elt2,EQUIPE_2).equals(equipe)){
							setAffiche(elt2, listEquipeSuivie);
						}
				
					}
					for(String id: listMatchSuivie){
						
						if(parser.getValue(elt2,ID_MATCH ).equals(id) ){
							setAffiche(elt2, listMatch);
						}
					}
					for(String id: listMatchPari){
						
						if(parser.getValue(elt2,ID_MATCH ).equals(id) ){
							setAffiche(elt2, listPari);
						}
					}
				}
			}

			/**
			 * @param elt2
			 * @param listej TODO
			 */
			public void setAffiche(Element elt2, ArrayList<DataJournee> listej) {
				dataj.setEquipe1(parser.getValue(elt2,EQUIPE_1)) ;
				dataj.setEquipe2(parser.getValue(elt2,EQUIPE_2)) ;
				dataj.setScore1(parser.getValue(elt2,SCORE_1));
				dataj.setScore2(parser.getValue(elt2,SCORE_2));
				dataj.setIdMatch(parser.getValue(elt2,ID_MATCH));
				//String dt = setDateFormat(parser.getValue(elt1,DATE));
				dataj.setPeriode(parser.getValue(elt2,DATE)) ;
				String jour = "Journeé N° "+ parser.getValue(elt2,ROUND)  ;
				dataj.setNumJournee(jour);
				dataj.setEtat("etat");
				listej.add(dataj);
			}
			
			/**
			 * @param listMatchPari the listMatchPari to set
			 */
			public void setListMatchPari(ArrayList<String> listMatchPari) {
				this.listMatchPari = listMatchPari;
			}

				/**
			 * @param listMatchSuivie the listMatchSuivie to set
			 */
			public void setListMatchSuivie(ArrayList<String> listMatchSuivie) {
				this.listMatchSuivie = listMatchSuivie;
			}

				/**
			 * @param listPari the listPari to set
			 */
			public void setListPari(ArrayList<DataJournee> listPari) {
				this.listPari = listPari;
			}

				/**
			 * @param listEquipeSuivie the listEquipeSuivie to set
			 */
			public void setListEquipeSuivie(ArrayList<String> listEquipeSuivie) {
				this.listMatchEquipeSuivie = listEquipeSuivie;
			}
			
			/**
			 * @param listMatch the listMatch to set
			 */
			public void setListMatch(ArrayList<DataJournee> listMatch) {
				this.listMatch = listMatch;
			}

			/**
			 * @param listJournee
			 */
			public void setListJournee(ArrayList<DataJournee> listJournee) {
				this.listEquipeSuivie = listJournee;
			}
}

