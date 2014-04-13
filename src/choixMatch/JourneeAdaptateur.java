/**
 * 
 */
package choixMatch;

import java.text.DateFormat;
import java.util.ArrayList;

import com.example.livesoccer.R;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author live soccer
 *
 */
public class JourneeAdaptateur extends BaseAdapter{
	 /**
     * <code>ArrayList</code> giving the liste of team properties 
     */
    public ArrayList<DataJournee> listMatch;
    
    private DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
    

	/**
	 * @param listMatch
	 */
	public JourneeAdaptateur(ArrayList<DataJournee> listmatch) {
		this.listMatch =  listmatch;
	}
	
	@Override
	public int getCount() {
		return listMatch .size();
	}
	@Override
	public Object getItem(int position) {
		
		return listMatch .get(position);
	}
	@Override
	public long getItemId(int position) {
		
		return position;
	}
	@Override
	public View getView(int position, View view, final ViewGroup parent) {
		 if (view == null) {
	            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
	            view = inflater.inflate(R.layout.journee, parent, false);
	        }

		
		final DataJournee dataJournee = listMatch.get(position);
		TextView textJournee = (TextView)view.findViewById(R.id.journeej);
		textJournee.setText(dataJournee.getNumJournee());

		TextView textEtat = (TextView)view.findViewById(R.id.etatj);
		textEtat.setText(dataJournee.getEtat());
		
		TextView textEquipe = (TextView)view.findViewById(R.id.equipej);
		textEquipe.setText(dataJournee.intitulle[0]);
		
		TextView textScore = (TextView)view.findViewById(R.id.scorej);
		textScore .setText(dataJournee.intitulle[1]);
		
		TextView textParis = (TextView)view.findViewById(R.id.parisj);
		textParis .setText(dataJournee.intitulle[2]);
		
		ImageView icon = (ImageView)view.findViewById(R.id.barrej);
		icon.setImageResource(dataJournee.intitulle[3]);
		
		TextView textEquipe1 = (TextView)view.findViewById(R.id.equipej1);
		textEquipe1.setText(dataJournee.getEquipe1());
		
		TextView textEquipe2 = (TextView)view.findViewById(R.id.equipej2);
		textEquipe2.setText(dataJournee.getEquipe2());
		
		ImageView icon1 = (ImageView)view.findViewById(R.id.favorisj1);
		icon1.setImageResource(dataJournee.intitulle[4]);
		
		ImageView icon2 = (ImageView)view.findViewById(R.id.favorisj2);
		icon2.setImageResource(dataJournee.intitulle[4]);
		
		TextView textScore1 = (TextView)view.findViewById(R.id.scorej1);
		textScore1.setText(dataJournee.getScore1());
		
		TextView textScore2 = (TextView)view.findViewById(R.id.scorej2);
		textScore2.setText(dataJournee.getScore2());
		
		TextView textPeriode = (TextView)view.findViewById(R.id.periodej);
		textPeriode .setText(df.format(dataJournee.getPeriode()));
		
		//+dataJournee.getPeriode().compareTo(date) ;
		CheckBox check1 = (CheckBox)view.findViewById(R.id.checkj1);
		CheckBox check2 = (CheckBox)view.findViewById(R.id.checkj2);
		
		Button suivre = (Button)view.findViewById(R.id.suivrej);
//		
		if(position%9 == 0){
			view.findViewById(R.id.journeej).setVisibility(View.VISIBLE);	
		}
		else {
			view.findViewById(R.id.journeej).setVisibility(View.INVISIBLE);			
		}
//		
		view.findViewById(R.id.suivrej).setBackgroundColor(Color.argb(255, 50, 200, 0));
		
//		TextView textCote = (TextView)view.findViewById(R.id.cote);
//		textCote.setText(String.valueOf(dataJournee.getCote()));
		//view.findViewById(R.id.leagueInner).setVisibility(View.VISIBLE);
		
		
//		if(position % 2 == 0)
//			view.setBackgroundColor(Color.argb(255, 20, 20, 20));
//		else
//			view.setBackgroundColor(Color.BLACK);
		
		check1.setTag(position) ;
		check2.setTag(position) ;
	
		// rendre le check box sélectionable sur le listview 
		check1.setOnClickListener( new View.OnClickListener() {
	         @Override
	         public void onClick(View view) {
	        	 boolean checked = ((CheckBox) view).isChecked();
	    
	        	 if (checked ){
	        		// TODO add team  to following team list 
	        		 Toast.makeText(parent.getContext(), "doit agir sur la liste equipe suivie: " +" Paris", Toast.LENGTH_SHORT).show();
	        	 }
		         else {
		        	  // TODO remove team  to following team list 
		         }
	         }     
	     });
		
		check2.setOnClickListener( new View.OnClickListener() {
	         @Override
	         public void onClick(View view) {
	        	 boolean checked = ((CheckBox) view).isChecked();
	    
	        	 if (checked ){
	        		// TODO add team  to following team list 
	        		 Toast.makeText(parent.getContext(), "doit agir sur la liste equipe suivie: " +" Paris", Toast.LENGTH_SHORT).show();
	        	 }
		         else {
		        	  // TODO remove team  to following team list 
		         }
	         }     
	     });
		
		// if click in the listview different to checkbox 
		 view.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	            	//TODO redirect to equipe page 
	                Toast.makeText(parent.getContext(), "doit ouvrir la page : " + dataJournee.getNumJournee(), Toast.LENGTH_SHORT).show();
	            }
	        });

		    return view;
	}
	
	
	
	

}
