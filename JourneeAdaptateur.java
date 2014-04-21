/**
 * 
 */
package choixMatch;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.livesoccer.R;

import database.DBHelper;

/**
 * @author live soccer
 * 
 */
public class JourneeAdaptateur extends BaseAdapter {
	/**
	 * <code>ArrayList</code> giving the liste of team properties
	 */
	public ArrayList<DataJournee> listMatch;

	private DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

	private Context context;

	private boolean[] checkBoxState1;

	private boolean[] checkBoxState2;

	/**
	 * @param context
	 *            TODO
	 * @param listMatch
	 */
	public JourneeAdaptateur(ArrayList<DataJournee> listmatch, Context context) {
		this.listMatch = listmatch;
		this.context = context;
		checkBoxState1=new boolean[listmatch.size()] ;
		checkBoxState2=new boolean[listmatch.size()] ;
		// buttonState = new boolean[listmatch.size()];
		
	}

	

	@Override
	public int getCount() {
		return listMatch.size();
	}

	@Override
	public Object getItem(int position) {

		return listMatch.get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(int position, View view, final ViewGroup parent) {
		DataJournee dataJournee = listMatch.get(position);

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.journee, parent, false);
			//dataJournee.buttonState = false;

		}
		// else{
		// viewHolder=(ViewHolder) view.getTag();
		// }
		
		TextView textJournee = (TextView) view.findViewById(R.id.journeej);
		TextView textEtat = (TextView) view.findViewById(R.id.etatj);
		TextView textEquipe = (TextView) view.findViewById(R.id.equipej);
		TextView textScore = (TextView) view.findViewById(R.id.scorej);
		TextView textParis = (TextView) view.findViewById(R.id.parisj);
		ImageView icon = (ImageView) view.findViewById(R.id.barrej);
		TextView textEquipe1 = (TextView) view.findViewById(R.id.equipej1);
		TextView textEquipe2 = (TextView) view.findViewById(R.id.equipej2);
		ImageView icon1 = (ImageView) view.findViewById(R.id.favorisj1);
		ImageView icon2 = (ImageView) view.findViewById(R.id.favorisj2);
		TextView textScore2 = (TextView) view.findViewById(R.id.scorej2);
		TextView textScore1 = (TextView) view.findViewById(R.id.scorej1);
		CheckBox check1 = (CheckBox) view.findViewById(R.id.checkj1);
		CheckBox check2 = (CheckBox) view.findViewById(R.id.checkj2);
		TextView textPeriode = (TextView) view.findViewById(R.id.periodej);
		Button suivre = (Button) view.findViewById(R.id.suivrej);

		view.setTag(Integer.valueOf(position));

		
		DBHelper dbh = new DBHelper(context);
		
		textJournee.setText(dataJournee.getNumJournee());
		textEtat.setText(dataJournee.getEtat());
		textEquipe.setText(dataJournee.intitulle[0]);
		textScore.setText(dataJournee.intitulle[1]);
		textParis.setText(dataJournee.intitulle[2]);
		icon.setImageResource(dataJournee.intitulle[3]);
		textEquipe1.setText(dataJournee.getEquipe1());
		textEquipe2.setText(dataJournee.getEquipe2());

		
		boolean equipe1_favori = dbh.isFavori(dataJournee.getEquipe1());
		boolean equipe2_favori = dbh.isFavori(dataJournee.getEquipe2());
		
		//Controle d'icone favori d'equipe 1
		if(equipe1_favori){
			icon1.setImageResource(dataJournee.intitulle[4]);
			view.findViewById(R.id.favorisj1).setVisibility(View.VISIBLE);
		}
		else{
			view.findViewById(R.id.favorisj1).setVisibility(View.INVISIBLE);
		}

		//Controle d'icone favori d'equipe 2
		if(equipe2_favori){
			icon2.setImageResource(dataJournee.intitulle[4]);
			view.findViewById(R.id.favorisj2).setVisibility(View.VISIBLE);
		}
		else{
			view.findViewById(R.id.favorisj2).setVisibility(View.INVISIBLE);
		}

		//Controle du bouton suivre
		if(equipe1_favori || equipe2_favori){
			view.findViewById(R.id.suivrej).setVisibility(View.INVISIBLE);
		}
		else{
			view.findViewById(R.id.suivrej).setVisibility(View.VISIBLE);
		}

		
		
		
		textScore1.setText(dataJournee.getScore1());
		textScore2.setText(dataJournee.getScore2());
		textPeriode.setText(dataJournee.getPeriode());

		// +dataJournee.getPeriode().compareTo(date) ;

		//
		if (position % 9 == 0) {
			view.findViewById(R.id.journeej).setVisibility(View.VISIBLE);
		} else {
			view.findViewById(R.id.journeej).setVisibility(View.GONE);
			view.findViewById(R.id.equipej).setVisibility(View.INVISIBLE);
		}
		
		
		Timestamp stampCurrant = new java.sql.Timestamp(System.currentTimeMillis());
		Timestamp stampMatch = new java.sql.Timestamp(dataJournee.getDateMatch());
		
		if(stampCurrant.before(stampMatch)){
//			check1.setChecked(checkBoxState1[position]);
//			check2.setChecked(checkBoxState2[position]);
			view.findViewById(R.id.checkj1).setVisibility(View.VISIBLE);
			view.findViewById(R.id.checkj2).setVisibility(View.VISIBLE);
		}
		else{
			view.findViewById(R.id.checkj1).setVisibility(View.INVISIBLE);
			view.findViewById(R.id.checkj2).setVisibility(View.INVISIBLE);
		}
		
		// rendre le check box sélectionable sur le listview
		check1.setTag(position);
		check1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int pos= (Integer) view.getTag();
				boolean checked = ((CheckBox) view).isChecked();

				if (checked && checkBoxState1[pos] == false) {

					Toast.makeText(
							parent.getContext(),
							"doit agir sur la liste equipe suivie: " + " Paris",
							Toast.LENGTH_SHORT).show();
					// TODO ajouter à la liste des paris
					checkBoxState1[pos] = true;

				} else {
					// TODO retirer de la liste des paris
					checkBoxState1 [pos]= false;
					
				}
			}
		});
		check2.setTag(position);
		check2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int pos=(Integer)view.getTag();
				boolean checked = ((CheckBox) view).isChecked();

				if (checked && checkBoxState2[pos] == false) {
	
						Toast.makeText(
								parent.getContext(),
								"doit agir sur la liste equipe suivie: " + " Paris",
								Toast.LENGTH_SHORT).show();
						checkBoxState2[pos] = true;
					// TODO ajouter à la liste des paris
				} else {
					
					// TODO retirer de la liste des paris
						checkBoxState2[pos] = false;
				}
			}
		});

		 
		//suivre.setTag(position);
		suivre.setBackgroundColor(dataJournee.buttonState?Color.argb(255, 200, 50, 0):Color.argb(255, 50, 200, 0));
		int text = dataJournee.buttonState?R.string.nsuivie:R.string.suivie;
		suivre.setText(text);
		suivre.setTag(position) ;
		suivre.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// buttonState[pos] = true;
				int pos=(Integer)view.getTag();
				DataJournee dataJournee = listMatch.get(pos);

				Button test = (Button) view;
				// Toast.makeText(parent.getContext(), "dposition : " + pos +
				// " "+ buttonState[pos] , Toast.LENGTH_SHORT).show();
				// buttonState[pos] = true;
				if (dataJournee.buttonState == false) {

					test.setText(R.string.nsuivie);
					Toast.makeText(parent.getContext(),
							"dposition : " + pos + " " ,
							Toast.LENGTH_SHORT).show();
					test.setBackgroundColor(Color.argb(255, 200, 50, 0));
					// view.findViewById(R.id.suivrej)
					dataJournee.buttonState = true;

				} else {

					test.setText(R.string.suivie);
					test.setBackgroundColor(Color.argb(255, 50, 200, 0));
					dataJournee.buttonState = false ;
				}
				

			}
		});

		// if click in the listview different to checkbox
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO redirect to equipe page
				int pos=(Integer)view.getTag();
				DataJournee dataJournee = listMatch.get(pos);

				Toast.makeText(parent.getContext(),
						"doit ouvrir la page : " + dataJournee.getNumJournee(),
						Toast.LENGTH_SHORT).show();
				Intent i = new Intent(context,ChoixLeagueActivity.class);
				i.putExtra("equipe1", dataJournee.getEquipe1());
				i.putExtra("equipe2", dataJournee.getEquipe2());
				i.putExtra("score1", dataJournee.getScore1());
				i.putExtra("score2", dataJournee.getScore2());
			
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(i);
			}
		});

		return view;
	}
	
	
//	private void setCheckBoxFalse(boolean[]data, int n){
//		for (int i = 0; i < n; i++) {
//			data[i] = false ;
//		}
//		
//	}

	// public void testOnClick(View btn)
	// {
	// Toast.makeText(getApplicationContext(), toastText,
	// Toast.LENGTH_SHORT).show();
	//
	// Intent i = new Intent(ExplicitActivity.this, ImplementsActivity.class);
	// startActivity(i);
	// }

}
