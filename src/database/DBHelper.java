package database;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends  SQLiteOpenHelper{
	
	static final int VERSION = 1;
	
	// Tableau des equipes favoris
	static final String TABLE_FAVORIS = "favoris";
	static final String ID_EQUIPE = "idEquipe";
	static final String NOM_EQUIPE = "nomEquipe";
	
	
	// Tableau des equipes suivi ou paris
	static final String TABLE_MATCHS = "matchs";
	static final String ID_MATCH = "idMatch";
	static final String NOM_EQUIPE1 = "nomEquipeA";
	static final String NOM_EQUIPE2 = "nomEquipeB";
	static final String PARI_EQUIPE1 = "pariEquipeA";
	static final String PARI_EQUIPE2 = "pariEquipeB";
	static final String ISSUIVI = "isSuivi";
	
	// Attributes a selectioner d'un match
	final String [] select_match = new String[]{
			ID_MATCH,
			NOM_EQUIPE1,
			NOM_EQUIPE2,
			PARI_EQUIPE1,
			PARI_EQUIPE2,
			ISSUIVI
	};
	
	// Attributes a selectioner d'une equipe favori
	final String [] select_favori = new String[]{
			ID_EQUIPE,
			NOM_EQUIPE,
	};
	
	
	Context context;
	
	public DBHelper(Context context){
		super(context, "liveSoccer.db", null, VERSION);
		this.context = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		Toast.makeText(context, "Creation DB", Toast.LENGTH_LONG).show();
		Log.d("DBHelper", "Creation DB");
		
		String sql = "";
		
		sql = "create table  if not exists " + TABLE_FAVORIS + " ("
				+ ID_EQUIPE + " int primary key, "
				+ NOM_EQUIPE + " text)";
		
		db.execSQL(sql);

		
		sql = "create table if not exists " + TABLE_MATCHS + " ("
				+ ID_MATCH + " int primary key, "
				+ NOM_EQUIPE1 + " text, "
				+ NOM_EQUIPE2 + " text, "
				+ PARI_EQUIPE1 + " text, "
				+ PARI_EQUIPE2 + " text, "
				+ ISSUIVI + " text)";
		
		db.execSQL(sql);		
		Log.d("DBHelper", "Fin Creation DB");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db,  int ancienneVersion, int nouvelleVersion){
		Toast.makeText(context, "Mise a jour DB", Toast.LENGTH_LONG).show();
		Log.d("DBHelper","Mise a jour DB");
		
		db.execSQL("drop table if exists "+ TABLE_FAVORIS);
		db.execSQL("drop table if exists "+ TABLE_MATCHS);
		
		onCreate(db);
	}
	
	/**
	 * Insere un nouveau equipe favori dans le tableau favoris
	 * @param idEquipe
	 * @param nomEquipe
	 */
	public void addNewFavori(int idEquipe, String nomEquipe){
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues  val = new ContentValues();
		
		val.clear();
		
		val.put(ID_EQUIPE, idEquipe);
		val.put(NOM_EQUIPE, nomEquipe);
		
		try{
			db.insertOrThrow(TABLE_FAVORIS, null, val);
		}
		catch(SQLException e){
			Log.d("DBHelper", "Error insert table favoris: " + e.getMessage() );
		}
		
		db.close();
	}
	
	/**
	 * Insere un nouveau match dans le tableau matchs
	 * @param idMatch
	 * @param equipe1
	 * @param equipe2
	 * @param pariEquipe1
	 * @param pariEquipe2
	 * @param isSuivi
	 */
	public void addNewMacth(int idMatch, String equipe1,	String equipe2, 
			String pariEquipe1, String pariEquipe2,	String isSuivi){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues val = new ContentValues();
		
		val.clear();
		
		val.put(ID_MATCH,idMatch);
		val.put(NOM_EQUIPE1,equipe1);
		val.put(NOM_EQUIPE2,equipe2);
		val.put(PARI_EQUIPE1,pariEquipe1);
		val.put(PARI_EQUIPE2,pariEquipe2);
		val.put(ISSUIVI,isSuivi);

		try{
			db.insertOrThrow(TABLE_MATCHS, null, val);
			Log.d("DBHelper", "Match: " + idMatch + "  cree" );
		}
		catch(SQLException e){
			Log.d("DBHelper", "Error insert table matchs: " + e.getMessage() );
		}
		db.close();
	}
	
	
	/**
	 * Met a jour le tableau matchs quand le  checkbox de l'equipe1  du layout 
	 * "journee_complet.xml" est coche ou decoche
	 * @param idMatch
	 * @param aSuivre	
	 * 			true si l'usager decide de suivre de match et 
	 * 			false si l'usager decide de ne plus suivre de match
	 */
	public void updateMatchPariEquipe1(int idMatch, String pari ){
				
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues vals = new ContentValues();
		vals.put(PARI_EQUIPE1, pari);
		
		db.update(TABLE_MATCHS, vals, ID_MATCH + " = " + idMatch , null);		

		//verifier si le match es suive ou on a pari 
		if(pari.equals("false") && isToDelete(idMatch)){
				deleteMatch(idMatch);
		}	
	}

	
	/**
	 * Met a jour le tableau matchs quand le  checkbox de l'equipe2  du layout 
	 * "journee_complet.xml" est coche ou decoche
	 * @param idMatch
	 * @param pari	
	 * 			true si l'usager decide de suivre de match et 
	 * 			false si l'usager decide de ne plus suivre de match
	 */
	public void updateMatchPariEquipe2(int idMatch, String pari){
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues vals = new ContentValues();
		vals.put(PARI_EQUIPE2, pari);
		
		db.update(TABLE_MATCHS, vals, ID_MATCH + " = " + idMatch , null);		

		//verifier si le match es suive ou on a pari 
		if(pari.equals("false") && isToDelete(idMatch)){
				deleteMatch(idMatch);
		}	
	}
	
	
	/**
	 * Met a jour le tableau matchs quand le buton suivre est presse 
	 * @param idMatch
	 * @param aSuivre	
	 * 			"suivi" si l'usager decide de suivre de match et 
	 * 			"nonSuivi" si l'usager decide de ne plus suivre de match
	 */
	public void updateMatchSuivi(int idMatch, String aSuivre){
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues vals = new ContentValues();
		vals.put(ISSUIVI, aSuivre);
		
		db.update(TABLE_MATCHS, vals, ID_MATCH + " = " + idMatch , null);		

		
		//verifier si le match es suive ou on a pari 
		if(aSuivre.equals("nonSuivi") && isToDelete(idMatch)){
				deleteMatch(idMatch);
		}	
	}
	

	/**
	 * Verifie a partir de l'id du match si un match est dans le tableau matchs
	 * @param idMatch
	 * @return  true si le match se truve dans le tableau matchs, sinon false 
	 */
	public boolean isMatchSuivi(int idMatch){
		boolean isMatchSuivi = false;
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.query(TABLE_MATCHS, select_match, ID_MATCH+ " = " +idMatch, null, null, null, null);

		cursor.moveToFirst();
		
		if(cursor.getCount()==1 && cursor.getString(5).equals("suivi")){
			isMatchSuivi  =true;
		}

		Log.d("DBHelper", "Match: getMatch: " + idMatch);
		db.close();
		
		return isMatchSuivi;
	}
	
	
	/**
	 * Verifie a partir du nom si une equipe est dans le tableau de favoris
	 * @param nomEquipe
	 * @return  true si l'equipe se truve dans le tableau favoris, sinon false 
	 */
	public boolean isFavori(String nomEquipe){
		boolean isFavori = false;
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_FAVORIS, select_favori, NOM_EQUIPE+ " = '" +nomEquipe + "'", null, null, null, null);
		if(cursor.getCount()==1){
			isFavori = true;
		}

		Log.d("DBHelper", "isFavori(): " + isFavori );
		db.close();
		
		
		return isFavori;		
	}
	
	
	/**
	 * Cherche le match par id dans le tableau matchs et retourne le match sous
	 * forme HashMap
	 * @param idMatch
	 * @return  Si l'equipe est trouve retourne un HassMap contenant les donnes du match,
	 * 	sinon retourne un HashMap null 
	 */
	public HashMap<String, String> getMatch(int idMatch){
		HashMap<String, String> match = null;
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.query(TABLE_MATCHS, select_match, ID_MATCH+ " = " +idMatch, null, null, null, null);

		if(cursor.getCount()==1){
			match = new HashMap<String, String> ();
			cursor.moveToFirst();
			match.put("id_match", ""+cursor.getInt(0));
			match.put("nom_equipe1", cursor.getString(1));
			match.put("nom_equipe2", cursor.getString(2));
			match.put("pari_equipe1", cursor.getString(3));
			match.put("pari_equipe2", cursor.getString(4));
			match.put("is_suivi", cursor.getString(5));
			Log.d("DBHelper", "Match: " + idMatch + "  getMatch2" );
		}

		Log.d("DBHelper", "Match: getMatch: " + idMatch);
		db.close();
		return match;
	}
	
	
	/**
	 * Verifie si les checkbox de l'equipe1 et de l'equipe2 sont desactive et si l'etat du bouton
	 * suivre est desactive
	 * @param idMatch
	 * @return  true si les deux checkbox sont desactive et l'etat de suivre est desactive,
	 * 			false sinon   
	 */
	public boolean isToDelete(int idMatch){
		boolean isToDelete = true;
				
		HashMap<String, String> match = null;
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.query(TABLE_MATCHS, select_match, ID_MATCH+ " = " +idMatch, null, null, null, null);
		cursor.moveToFirst();
		boolean checkBox_Equipe1_Coche = cursor.getString(3).equals("true");
		boolean checkBox_Equipe2_Coche = cursor.getString(4).equals("true");
		boolean bouton_suivi_active = cursor.getString(5).equals("suivi");
		if(cursor.getCount()==1 && (checkBox_Equipe1_Coche || checkBox_Equipe2_Coche || bouton_suivi_active)){
			isToDelete = false;
		}

		Log.d("DBHelper", "IsToDelete:  " + isToDelete +   "   idMatch" + idMatch);
		db.close();
		
		return isToDelete;
	}
	
	
	/**
	 * Compte le nombre d'elements du tableau favoris
	 * @return La taille du tableau favoris
	 */
	public int querySizeFavoris() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(TABLE_FAVORIS, new String[] {ID_EQUIPE}, null, null, null, null, null);
		int size = c.getCount();
		db.close();
		return size;
	}
	
	
	/**
	 * Efface l'equipe passe en parametre du tableau favoris
	 * @param idEquipe
	 * @return  true si l'equipe a ete efface
	 */
	public boolean deleteFavoris(int idEquipe){
		SQLiteDatabase db = this.getReadableDatabase();
		boolean efface = db.delete(TABLE_FAVORIS, ID_EQUIPE + "= " + idEquipe , null) > 0;
		db.close();
		return efface;
	}
		
	
	/**
	 * Compte le nombre d'elements du tableau matchs
	 * @return La taille du tableau matchs
	 */
	public int querySizeMatchs() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(TABLE_MATCHS, new String[] {ID_MATCH}, null, null, null, null, null);
		int size = c.getCount();
		db.close();
		return size;
	}
	
	
	/**
	 * Efface le match passe en parametre du tableau matchs
	 * @param id_match
	 * @return true si le match a ete efface
	 */
	public boolean deleteMatch(int id_match){
		SQLiteDatabase db = this.getReadableDatabase();
		boolean efface = db.delete(TABLE_MATCHS, ID_MATCH + "= " + id_match , null) > 0;
		db.close();
		return efface;
	}
}