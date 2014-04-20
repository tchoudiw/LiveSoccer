package choixMatch;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.livesoccer.R;

import descriptionEquipe.EquipeActivity;
//import ScottishLeagueActivity ;

public class ScottishEquipeAdapteur extends BaseAdapter{

	
	/**
	 * @param listEquipe
	 * @param contexte TODO
	 */
	public ScottishEquipeAdapteur(ArrayList<DataEquipe> listEquipe, Context contexte) {
		
		this.listEquipe = listEquipe;
		context = contexte ;
		checkBoxState=new boolean[listEquipe.size()];
		
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
	public View getView(final int position, View view, final ViewGroup parent) {

		 if (view == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.equipe, parent, false);
		 }
			TextView equipe = (TextView)view.findViewById(R.id.equipe);
			CheckBox checkBox = (CheckBox)view.findViewById(R.id.checkEquipe);
			TextView cote = (TextView)view.findViewById(R.id.cote);
		 
		final DataEquipe dataEquipe = listEquipe.get(position);	
		equipe.setText(dataEquipe.getNomEquipe());
		cote.setText(String.valueOf(dataEquipe.getCote()));
		
		checkBox.setChecked(checkBoxState[position]);
		//view.findViewById(R.id.leagueInner).setVisibility(View.VISIBLE);

		view.setTag(Integer.valueOf(position));

	
		// rendre le check box sélectionable sur le listview 
		checkBox.setTag(position) ;
		checkBox.setOnClickListener( new View.OnClickListener() {
	         @Override
	         public void onClick(View view) {
	        	 boolean checked = ((CheckBox) view).isChecked();
	        	 int pos = (Integer)view.getTag();
	    
	        	 if (checked ){
	        		// TODO add team  to following team list 
	        		 checkBoxState[pos]=true;
	        		 Toast.makeText(parent.getContext(), "doit agir sur la liste equipe suivie: " + TAG, Toast.LENGTH_SHORT).show();
	        	 }
		         else {
		        	  // TODO remove team  to following team list 
		        	 checkBoxState[pos]=false;
		         }
	        		 
	         }     
	     });
		
		// if click in the listview different to checkbox 
		 view.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
		        	int pos = (Integer)view.getTag();

	        		final DataEquipe dataEquipe = listEquipe.get(pos);	

	            	//TODO renvoyer dans la variable globale le nom de l'équipe 
	            	Intent intent= new Intent(context, EquipeActivity.class);
	                
	            	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        		intent.putExtra("equipe", dataEquipe.getNomEquipe());
	        		intent.putExtra("id", dataEquipe.getiDTeam());

	            	context.startActivity(intent);
	            	
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
     Context context ;
   //ScottishLeagueActivity leagueActivity =  new ScottishLeagueActivity() ;
   Intent itent ;
   
   boolean[] checkBoxState;

}
