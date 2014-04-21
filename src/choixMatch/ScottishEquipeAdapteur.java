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

import com.example.livesoccer.R;

import database.DBHelper;
import descriptionEquipe.EquipeActivity;

//import ScottishLeagueActivity ;

public class ScottishEquipeAdapteur extends BaseAdapter {

	/**
	 * @param listEquipe
	 * @param contexte
	 *            TODO
	 */
	public ScottishEquipeAdapteur(ArrayList<DataEquipe> listEquipe,
			Context contexte) {

		this.listEquipe = listEquipe;
		context = contexte;
		checkBoxState = new boolean[listEquipe.size()];

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
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.equipe, parent, false);
		}

		TextView equipe = (TextView) view.findViewById(R.id.equipe);
		CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkEquipe);
		TextView cote = (TextView) view.findViewById(R.id.cote);

		final DataEquipe dataEquipe = listEquipe.get(position);
		equipe.setText(dataEquipe.getNomEquipe());
		cote.setText(String.valueOf(dataEquipe.getCote()));

		
		//Verifier si l'equipe est vafori;
		DBHelper dbh = new DBHelper(context);	  
		boolean isFavori = dbh.isFavori(dataEquipe.getNomEquipe());
		checkBox.setChecked(isFavori);
		
		view.setTag(Integer.valueOf(position));

		// rendre le check box sélectionable sur le listview
		checkBox.setTag(position);
		checkBox.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int pos = (Integer) view.getTag();
				
				final DataEquipe dataEquipe = listEquipe.get(pos);
				  
				DBHelper dbh = new DBHelper(context);

				boolean checked = ((CheckBox) view).isChecked();

				if (checked) {
					//Marquer comme favori
					dbh.addNewFavori(Integer.parseInt(dataEquipe.getiDTeam()), dataEquipe.getNomEquipe());
				} 
				else {
					//Marquer comme non favori
					dbh.deleteFavoris(Integer.parseInt(dataEquipe.getiDTeam()));
				}

			}
		});

		// if click in the listview different to checkbox
		view.setOnClickListener(new View.OnClickListener() {
			

			@Override
			public void onClick(View view) {
				int pos = (Integer) view.getTag();

				final DataEquipe dataEquipe = listEquipe.get(pos);
				//final DataJournee dataJournee = listJournee.get(pos);
				// TODO renvoyer dans la variable globale le nom de l'équipe
				Intent intent = new Intent(context, EquipeActivity.class);

				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("equipe", dataEquipe.getNomEquipe());
				intent.putExtra("id_Equipe", dataEquipe.getiDTeam());
				//intent.putExtra("id_Match", dataJournee.getIdMatch());
				
				context.startActivity(intent);

			}
		});

		return view;
	}

	
	
	/**
	 * <code>ArrayList</code> giving the liste of team properties
	 */
	public ArrayList<DataEquipe> listEquipe;
	
	/**
	 * <code>ArrayList</code> giving the liste of day playing properties
	 */
	//private ArrayList<DataJournee> listJournee;

	public int count = 0;
	Context context;
	// ScottishLeagueActivity leagueActivity = new ScottishLeagueActivity() ;
	Intent itent;

	boolean[] checkBoxState;

}
