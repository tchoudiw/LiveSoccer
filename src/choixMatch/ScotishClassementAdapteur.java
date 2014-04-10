/**
 * 
 */
package choixMatch;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.livesoccer.R;

/**
 * @author team Live Soccer
 *
 */
public class ScotishClassementAdapteur extends BaseAdapter{
	
	/**
	 * @param listclasement
	 */
	public ScotishClassementAdapteur (ArrayList<DataEquipe> listclasement) {
		
		this.listClassement = listclasement;
	}
	
	
	@Override
	public int getCount() {
		return listClassement.size();
	}
	@Override
	public Object getItem(int position) {
		
		return listClassement.get(position);
	}
	@Override
	public long getItemId(int position) {
		
		return position;
	}
	@Override
	public View getView(int position, View view, final ViewGroup parent) {
		 if (view == null) {
	            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
	            view = inflater.inflate(R.layout.classement, parent, false);
	        }

		
		final DataEquipe dataEquipe = listClassement.get(position);
		TextView textRang = (TextView)view.findViewById(R.id.rangc);
		textRang.setText(String.valueOf(dataEquipe.getRang()));
		
		TextView textEquipe = (TextView)view.findViewById(R.id.equipec);
		textEquipe.setText(dataEquipe.getNomEquipe());
		
		ImageView icon = (ImageView)view.findViewById(R.id.favorisc);
		icon.setImageResource(dataEquipe.getFavori());

		TextView textPoint = (TextView)view.findViewById(R.id.pointc);
		textPoint.setText(String.valueOf(dataEquipe.getPoint()));
		//view.findViewById(R.id.leagueInner).setVisibility(View.VISIBLE);
		

		
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
     * <code>ArrayList</code> giving the liste of team properties 
     */
    public ArrayList<DataEquipe> listClassement;
	

}
