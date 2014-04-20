package choixMatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.livesoccer.R;

/**
 * Class qui gère l'onglet activité des équipes 
 * @author team Live Soccer
 *
 */
public class ScottishEquipeActivity extends Activity{

	private class MainListOnItemClick implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
			switch(position)
			{
			//TODO Leaugue 1.1
				case 0:
				{
					Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_SHORT).show();
					//Intent i = new Intent(ChoixLeagueActivity.this, ScottishLeagueActivity.class);
					//startActivity(i);
					break;
				}
				
				default:
					Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_SHORT).show();
					
			}
		}
	
	}	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_league_onglet);
		mainAdapter = new ScottishEquipeAdapteur(null, null );
		mainList = (ListView)findViewById(R.id.listequipe);
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
	private ScottishEquipeAdapteur mainAdapter;


}
