/**
 * 
 */
package configurationSon ;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.livesoccer.R;

/**
 * @author team Live Soccer
 *
 */
public class ConfigurationActivity extends Activity {



//TODO Configuration activity 



@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.configuration_son);


}


@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
}



}

