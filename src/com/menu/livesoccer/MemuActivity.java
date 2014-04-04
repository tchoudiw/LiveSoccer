package com.menu.livesoccer;


import java.util.ArrayList;

import com.example.livesoccer.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MemuActivity extends Activity {

	private class MainListOnItemClick implements OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
//			switch(position)
//			{
//			case 30:
//			{
//				Intent i = new Intent(MainActivityCustom.this, MainActivityArray.class);
//				startActivity(i);
//				break;
//			}
//			case 31:
//			{
//				Intent i = new Intent(MainActivityCustom.this, MainActivityHashMap.class);
//				startActivity(i);
//				break;
//			}
//			case 32:
//			{
//				Intent i = new Intent(MainActivityCustom.this, MainActivityHashMapBinder.class);
//				startActivity(i);
//				break;
//			}
//			default:
				Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_SHORT).show();
					
			}
		}
	
	
	/**
	 * mainList <code>ListView</code> 
	 */
	private ListView mainList;
	/**
	 * Menu adapter 
	 */
	private MenuAdapter mainAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		GregorianCalendar gc = new GregorianCalendar();
//		
//		Random r = new Random();
//		
		int dataMenu[]  = {
				R.string.choix_match,
				R.string.live_match,
				R.string.historiques_match,
				R.string.futurs_match,
				R.string.mes_equipes,
				R.string.configuration_sons
			};

		
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
	

}
