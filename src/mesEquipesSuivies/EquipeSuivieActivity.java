package mesEquipesSuivies;


import java.util.ArrayList;

import choixMatch.ChoixAdapter;
import choixMatch.DataEquipe;
import choixMatch.ScottishEquipeAdapteur;

import com.example.livesoccer.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;


public class EquipeSuivieActivity extends Activity {



//TODO equipe suivies Activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainleague);
		mainAdapter = new ChoixAdapter(getApplicationContext() );
		mainList = (ListView)findViewById(R.id.leagueList);
		mainList.setAdapter(mainAdapter);
		mainList.setOnItemClickListener(new MainListOnItemClick());
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * mainList <code>ListView</code> 
	 */
	private ListView mainList;
	/**
	 * Menu adapter 
	 */
	//private ChoixAdapter mainAdapter;
	private ArrayList<DataEquipe> listEquipe ;
	private ScottishEquipeAdapteur mainAdapter ;
	private DataEquipe data;

}


