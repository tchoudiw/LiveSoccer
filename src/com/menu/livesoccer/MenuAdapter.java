/**
 * 
 */
package com.menu.livesoccer;

import java.text.DateFormat;
import java.util.List;

import com.example.livesoccer.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Class to makke adpter for the main menu of the application
 * @author LiveSoccer Team
 *
 */
public class MenuAdapter extends BaseAdapter{

	private LayoutInflater inflater;
	/**
	 * Constructor 
	 * @param context TODO
	 */
	public MenuAdapter(Context context) {
		_context = context ;
		//dataMenu = data ;
		this.inflater =
				LayoutInflater.from(context);
	}
	
	
	public class MenuData{
		
		
	}
	
	@Override
	public int getCount() {
		return dataMenu.length;
	}
	@Override
	public Object getItem(int position) {
		
		return dataMenu[position];
	}
	@Override
	public long getItemId(int position) {
		
		return position;
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
//		if(view == null){
//			LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.menu, parent, false);
//		}
		
		//else
		//{
			TextView titleMenu = (TextView)view.findViewById(R.id.texteMemu);
			ImageView icon = (ImageView)view.findViewById(R.id.menuIcon);

			titleMenu.setText(dataMenu[position]);
			
			icon.setImageResource(menuIcons[position]);
			
			view.findViewById(R.id.overrideText).setVisibility(View.INVISIBLE);
			view.findViewById(R.id.menuInner).setVisibility(View.VISIBLE);
		//}
		
		if(position % 2 == 0)
			view.setBackgroundColor(Color.argb(255, 20, 20, 20));
		else
			view.setBackgroundColor(Color.BLACK);
		
		return view;
	}
	
	
	
	
	/**
	 * _context <code>Context</code> 
	 */
	private Context _context;

	/**
	 * Liste des <code>String</code> pour menu de l'acueil 
	 */
	private int   dataMenu[]  = {
			R.string.choix_match,
			R.string.live_match,
			R.string.historiques_match,
			R.string.futurs_match,
			R.string.mes_equipes,
			R.string.configuration_sons
		};
	/**
	 * Liste des imamage .png des menu d'accueil 
	 */
	private int menuIcons[] = {
			R.drawable.choisir_un_match,
			R.drawable.matchs_en_cours,
			R.drawable.historiques_des_matchs,
			R.drawable.matchs_a_venir,
			R.drawable.favoris,
			R.drawable.configuration_son
		};

	
}
