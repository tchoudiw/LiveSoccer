package choixMatch;

import java.util.ArrayList;

import com.example.livesoccer.R;
import android.widget.Toast;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ScottishEquipeAdapteur extends BaseAdapter{

	
	/**
	 * @param listEquipe
	 */
	public ScottishEquipeAdapteur(ArrayList<DataEquipe> listEquipe) {
		
		this.listEquipe = listEquipe;
	}
	
	
	@Override
	public int getCount() {
		return listEquipe.size();
	}
	@Override
	public Object getItem(int position) {
		
		return listEquipe.get(position);
	}
	@Override
	public long getItemId(int position) {
		
		return position;
	}
	@Override
	public View getView(int position, View view, final ViewGroup parent) {
		 if (view == null) {
	            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
	            view = inflater.inflate(R.layout.equipe, parent, false);
	        }

		
		final DataEquipe dataEquipe = listEquipe.get(position);
		TextView textEquipe = (TextView)view.findViewById(R.id.equipe);
		textEquipe.setText(dataEquipe.getNomEquipe());
		
		CheckBox check = (CheckBox)view.findViewById(R.id.checkEquipe);

		TextView textCote = (TextView)view.findViewById(R.id.cote);
		textCote.setText(String.valueOf(dataEquipe.getCote()));
		//view.findViewById(R.id.leagueInner).setVisibility(View.VISIBLE);
		
		
//		if(position % 2 == 0)
//			view.setBackgroundColor(Color.argb(255, 20, 20, 20));
//		else
//			view.setBackgroundColor(Color.BLACK);
		
		check.setTag(position) ;
	
		// rendre le check box sélectionable sur le listview 
		check.setOnClickListener( new View.OnClickListener() {
	         @Override
	         public void onClick(View view) {
	        	 boolean checked = ((CheckBox) view).isChecked();
	    
	        	 if (checked ){
	        		// TODO add team  to following team list 
	        		 Toast.makeText(parent.getContext(), "doit agir sur la liste equipe suivie: " + TAG, Toast.LENGTH_SHORT).show();
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
	                Toast.makeText(parent.getContext(), "doit ouvrir la page : " + dataEquipe.getNomEquipe(), Toast.LENGTH_SHORT).show();
	            }
	        });

		    return view;
	}
	
	
	
	/**
	 * Tag of the class
	 */
	private static final String TAG = ScottishEquipeAdapteur.class.getSimpleName();
    /**
     * <code>ArrayList</code> giving the liste of team properties 
     */
    public ArrayList<DataEquipe> listEquipe;
	
    public  int count = 0 ;

}
