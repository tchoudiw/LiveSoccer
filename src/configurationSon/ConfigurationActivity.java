/**
 * 
 */
package configurationSon ;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
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
			notificationSon() ;
			Notification() ;
		
		}
		
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
		
		public void notificationSon(){
		    try {
		        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		        Ringtone ring = RingtoneManager.getRingtone(getApplicationContext(), notification);
		        ring.play();
		     } catch (Exception e) {
		         e.printStackTrace();
		     }
		}
		protected void Notification() {

			 	int icon = R.drawable.configuration_son;        // icon from resources  
		        String ns = Context.NOTIFICATION_SERVICE;
		        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);        
		        CharSequence tickerText = "Hello"; // ticker-text
		        long when = System.currentTimeMillis();         
		        Context context = getApplicationContext();     
		        CharSequence contentTitle = "Hello";  
		        CharSequence contentText = "Hello";      
		        Intent notificationIntent = new Intent(this, ConfigurationActivity.class);
		        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		        Notification notification = new Notification(icon, tickerText, when);
		        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

		        // and this
		        final int HELLO_ID = 1;
		        mNotificationManager.notify(HELLO_ID, notification);
		        notification.flags |=  Notification.FLAG_AUTO_CANCEL ;
		        mNotificationManager.cancel(HELLO_ID );
			    
		     }
		

}

