/**
 * 
 */
package choixMatch;


import com.example.livesoccer.R;
import com.menu.livesoccer.MenuAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * @author team Live Soccer
 *
 */
public class ChoixLeagueActivity extends Activity {



//TODO Choix competition Activity 
	/**
	 * inner CLass to handle click event on the listView 
	 * @author Team Live Soccer
	 *
	 */
	private class MainListOnItemClick implements OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
			switch(position)
			{
			//TODO Leaugue 1.1
//			case 0:
//			{
//				Intent i = new Intent(ChoixLeagueActivity.this, ScottishActivity.class);
//				startActivity(i);
//				break;
//			}
			
			default:
				Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_SHORT).show();
					
			}
		}
	
	}	
	
	
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
	private ChoixAdapter mainAdapter;

}
	




