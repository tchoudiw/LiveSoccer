package com.menu.livesoccer;

import historiqueMatch.HistoriqueActivity;
import mesEquipesSuivies.EquipeSuivieActivity;
import mesMatchAvenir.MatchAvenirActivity;
import mesMatchEncours.MatchEncoursActivity;
import choixMatch.ChoixLeagueActivity;
import com.example.livesoccer.R;
import configurationSon.ConfigurationActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MemuActivity extends Activity {

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
			case 0:
			{
				Intent i = new Intent(MemuActivity.this, ChoixLeagueActivity.class);
				startActivity(i);
				break;
			}
			case 1:
			{
				Intent i = new Intent(MemuActivity.this, MatchEncoursActivity.class);
				startActivity(i);
				break;
			}
			case 2:
			{
				Intent i = new Intent(MemuActivity.this, HistoriqueActivity.class);
				startActivity(i);
				break;
			}
			case 3:
			{
				Intent i = new Intent(MemuActivity.this, MatchAvenirActivity.class);
				startActivity(i);
				break;
			}
			case 4:
			{
				Intent i = new Intent(MemuActivity.this, EquipeSuivieActivity.class);
				startActivity(i);
				break;
			}
			case 5:
			{
				Intent i = new Intent(MemuActivity.this, ConfigurationActivity.class);
				startActivity(i);
				break;
			}
			
			
			default:
				//Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_SHORT).show();
					
			}
		}
	
	}	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mainAdapter = new MenuAdapter(getApplicationContext() );
		mainList = (ListView)findViewById(R.id.mainList);
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
	private MenuAdapter mainAdapter;

}
	
