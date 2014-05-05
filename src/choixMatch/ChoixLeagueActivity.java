/**
 * 
 */
package choixMatch;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ressourceAPIXml.XMLParser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.livesoccer.R;

/**
 * @author team Live Soccer
 *
 */
public class ChoixLeagueActivity extends Activity {
	private  final String myFile = "myfile.xml";


//TODO Choix competition Activity 
	/**
	 * inner CLass to handle click event on the listView 
	 * @author Team Live Soccer
	 *
	 */
	private class MainListOnItemClick implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
			switch(position)
			{
			//TODO Leaugue 1.1
				case 0:
				{
					Intent i = new Intent(ChoixLeagueActivity.this, ScottishLeagueActivity.class);
					startActivity(i);
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
		setContentView(R.layout.activity_mainleague);
		
		DataEquipe data = new DataEquipe() ;
		XMLParser parser = new XMLParser();
		String xmlString1 = parser.getXmlFromUrl(data.getAllTeamUrl()); 
		//Document doc1 = parser.getDomElement(xmlString1);
//		XMLParser.convertToXmlFile(myFile, doc1);
		//Toast.makeText(this,"date = "+ DataJournee.incrementDate(new Date()) , Toast.LENGTH_LONG).show();
		createXmlFile(myFile, xmlString1) ;
		
		
		
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
	
	
	public void createXmlFile(String fileName ,String xml){
		
		  try { 
            final String TESTSTRING = new String(xml);
           
            FileOutputStream fOut = openFileOutput(fileName,MODE_WORLD_READABLE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut); 
            osw.write(TESTSTRING);
  
            osw.flush();
            osw.close();
           
           Log.i("File Created ", "successfull");

	    } catch (IOException ioe) {
	            ioe.printStackTrace();
	    }
	}
	
	public void fileDelete(String fileName){
		deleteFile(fileName) ;
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
	




