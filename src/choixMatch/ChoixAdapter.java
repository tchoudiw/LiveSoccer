package choixMatch;

import com.example.livesoccer.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChoixAdapter extends BaseAdapter{


	Context context ;
	/**
	 * Constructor 
	 * @param context TODO
	 */
	public ChoixAdapter(Context contexte) {
		context = contexte ;
		//this.inflater = LayoutInflater.from(context);
	}
	
	
	
	@Override
	public int getCount() {
		return dataLeague.length;
	}
	@Override
	public Object getItem(int position) {
		
		return dataLeague[position];
	}
	@Override
	public long getItemId(int position) {
		
		return position;
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if(view == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.league, parent, false);
		}
		
		//else
		//{
			TextView titleMenu = (TextView)view.findViewById(R.id.texteLeague);
			ImageView icon = (ImageView)view.findViewById(R.id.leagueIcon);

			titleMenu.setText(dataLeague[position]);
			
			icon.setImageResource(leagueIcons[position]);
			
			//view.findViewById(R.id.overrideText).setVisibility(View.INVISIBLE);
			view.findViewById(R.id.leagueInner).setVisibility(View.VISIBLE);
		//}
		
		if(position % 2 == 0)
			view.setBackgroundColor(Color.argb(255, 20, 20, 20));
		else
			view.setBackgroundColor(Color.BLACK);
		
		return view;
	}
	
	
	
	

	/**
	 * Liste des <code>String</code> pour menu de l'acueil 
	 */
	private int   dataLeague[]  = {
			R.string.scottish,
			R.string.french,
			R.string.england,
			R.string.germany,
			R.string.italy,
			R.string.spain
		};
	/**
	 * Liste des imamage .png des menu d'accueil 
	 */
	private int leagueIcons[] = {
			R.drawable.scottish,
			R.drawable.france,
			R.drawable.england,
			R.drawable.germany,
			R.drawable.italy,
			R.drawable.spain
		};

}
