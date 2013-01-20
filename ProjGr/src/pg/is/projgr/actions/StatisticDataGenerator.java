package pg.is.projgr.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;

import android.R.string;
import android.content.Context;
import android.util.Log;

import pg.is.projgr.IStatisticDataGenerator;

public class StatisticDataGenerator implements IStatisticDataGenerator {

	private static StatisticDataGenerator instance;
	private DatabaseHelper helper;

	private StatisticDataGenerator() {
		DatabaseHelper helper = null;
	}

	public static StatisticDataGenerator getInstance() {

		if (instance == null) {
			instance = new StatisticDataGenerator();
		}
		return instance;
	}

	public void setDatabaseHelperContext(Context context) {
		this.helper = new DatabaseHelper(context);
		this.helper.getWritableDatabase();
	}

	public int GetRaportsCount() {

		return (int) (this.helper.getRaportRuntimeDao().countOf());
	}

	public float[] GetExpensesMonthly() {

		int count = (int) (this.helper.getRaportRuntimeDao().countOf());
		float[] expenses = new float[count];
		List<Raport> r = this.helper.getRaportRuntimeDao().queryForAll();
		for (int i = 0; i < r.size(); i++) {
			expenses[i] = r.get(i).getLaczneWydatki();
		}
		return expenses;
	}

	public float[] GetIncomeMonthly() {

		int count = (int) (this.helper.getRaportRuntimeDao().countOf());
		float[] income = new float[count];
		List<Raport> r = this.helper.getRaportRuntimeDao().queryForAll();
		for (int i = 0; i < r.size(); i++) {
			income[i] = r.get(i).getBudzet();
		}
		return income;
	}

	public float[] GetSavingsMonthly() {

		List<Raport> r = this.helper.getRaportRuntimeDao().queryForAll();
		int count = r.size();
		float[] savings = new float[count];

		for (int i = 0; i < r.size(); i++) {
			savings[i] = r.get(i).getOszczednosci();
		}
		return savings;
	}

	// czyli - ceny poszczegolnych produktow kupionych w danym miesiacu, jak
	// rozumiem?
	public float[] GetIthMonthExpenses(int id) {

		List<Wydatek> expenses = new ArrayList(this.helper
				.getRaportRuntimeDao().queryForId(id).getWydatki());
		float[] expensesFloat = new float[expenses.size()];
		for (int i = 0; i < expenses.size(); i++) {
			expensesFloat[i] = expenses.get(i).getCena();
		}
		return expensesFloat;
	}

	public String[] GetMainCategories() {
		List<Kategoria> categories = new ArrayList<Kategoria>(this.helper
				.getKategoriaRuntimeDao().queryForAll());
		String[] categoriesStrings = new String[categories.size()];
		for (int i = 0; i < categories.size(); i++) {
			categoriesStrings[i] = categories.get(i).getNazwa();
		}
		return categoriesStrings;
	}

	public float[] GetIthMonthExpensesByCategories(int id) {
		int count = (int) this.helper.getKategoriaRuntimeDao().countOf();
		float[] expensesByCategories = new float[count];

		Raport ityRaport = this.helper.getRaportRuntimeDao().queryForId(id);
		List<Wydatek> expenses = new ArrayList(ityRaport.getWydatki());
		for (int i = 0; i < expenses.size(); i++) {
			Kategoria k = expenses.get(i).getProdukt().getPodkategoria()
					.getKategoria();
			if (k.getID() == 1) {
				expensesByCategories[0] += expenses.get(i).getCena();
			}
			if (k.getID() == 2) {
				expensesByCategories[1] += expenses.get(i).getCena();
			}
			if (k.getID() == 3) {
				expensesByCategories[2] += expenses.get(i).getCena();
			}
			if (k.getID() == 4) {
				expensesByCategories[3] += expenses.get(i).getCena();
			}
		}
		return expensesByCategories;
	}

	// TODO
	public float[] GetIthMonthExpensesBySubcategories(int i, String category) {

		// List<Raport> r = this.helper.getRaportRuntimeDao().queryForAll();

		return null;
	}

	public String[] GetSubCategories(String category) {
		List<Podkategoria> subcategories = new ArrayList<Podkategoria>(
				this.helper.getPodkategoriaRuntimeDao().queryForAll());
		String[] categoriesStrings = new String[subcategories.size()];
		for (int i = 0; i < subcategories.size(); i++) {
			categoriesStrings[i] = subcategories.get(i).getNazwa();
		}
		return categoriesStrings;
	}

	public static int GetMax(float[] array) {
		float max = array[0];
		for (int j = 1; j < array.length; j++) {
			if (array[j] > max) {
				max = array[j];
			}
		}
		return (int) max;
	}

	public static int GetMin(float[] array) {
		float min = array[0];
		for (int j = 1; j < array.length; j++) {
			if (array[j] < min) {
				min = array[j];
			}
		}
		return (int) min;
	}

	public Podkategoria kategoryzujProduktPoNazwie(String nazwaProduktu)
			throws SQLException {
		List<Produkt> produkty = selectProdukty();
		for (int i = 0; i < produkty.size(); i++) {
			if (produkty.get(i).getNazwa().equals(nazwaProduktu)) {
				return produkty.get(i).getPodkategoria();
			}
		}
		return null;
	}
	


	public void insertWydatek(Wydatek wydatek) throws SQLException {
		this.helper.getWydatekRuntimeDao().create(wydatek);
	}

	public List<Wydatek> selectWydatki() throws SQLException {
		return this.helper.getWydatekRuntimeDao().queryForAll();
	}

	public void insertKategoria(Kategoria kategoria) throws SQLException {
		this.helper.getKategoriaRuntimeDao().create(kategoria);
	}

	public List<Kategoria> selectKategorie() throws SQLException {
		return this.helper.getKategoriaRuntimeDao().queryForAll();
	}

	public void insertPodkategoria(Podkategoria podkategoria)
			throws SQLException {
		this.helper.getPodkategoriaRuntimeDao().create(podkategoria);
	}

	public List<Podkategoria> selectPodkategorie() throws SQLException {
		return this.helper.getPodkategoriaRuntimeDao().queryForAll();
	}

	public void insertProdukt(Produkt produkt) throws SQLException {
		this.helper.getProduktRuntimeDao().create(produkt);
		Log.i("insert", produkt.getNazwa());
	}

	public List<Produkt> selectProdukty() throws SQLException {
		return this.helper.getProduktRuntimeDao().queryForAll();
	}
	
	public void insertRaport(Raport raport) throws SQLException {
		this.helper.getRaportRuntimeDao().create(raport);
		Log.i("insert", "raport za miesiac"+raport.getMiesiac());
	}

	public List<Raport> selectRaporty() throws SQLException {
		return this.helper.getRaportRuntimeDao().queryForAll();
	}
	
	public Podkategoria getPodkategoriaByName(String nazwa){
		
		QueryBuilder<Podkategoria, Integer> customerQb = this.helper.getPodkategoriaRuntimeDao().queryBuilder();
		SelectArg nameSelectArg = new SelectArg();
		// this gives the c.name = ?
		try {
			customerQb.where().eq("nazwa", nameSelectArg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Podkategoria> results = null;
		// then you set the args and run the query
		nameSelectArg.setValue(nazwa);
		try {
			results = customerQb.query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results.get(0);
	}
	
	
	
	public Kategoria getKategoriaByName(String nazwa){
		
		QueryBuilder<Kategoria, Integer> customerQb = this.helper.getKategoriaRuntimeDao().queryBuilder();
		SelectArg nameSelectArg = new SelectArg();
		// this gives the c.name = ?
		try {
			customerQb.where().eq("nazwa", nameSelectArg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Kategoria> results = null;
		// then you set the args and run the query
		nameSelectArg.setValue(nazwa.toLowerCase());
		try {
			results = customerQb.query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results.get(0);
	}
	
	public List<Produkt> getProduktByName(String nazwa)
	{
		QueryBuilder<Produkt, Integer> customerQb = this.helper.getProduktRuntimeDao().queryBuilder();
		SelectArg nameSelectArg = new SelectArg();
		// this gives the c.name = ?
		try {
			customerQb.where().eq("nazwa", nameSelectArg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Produkt> results = null;
		// then you set the args and run the query
		nameSelectArg.setValue(nazwa);
		try {
			results = customerQb.query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}
	
	public List<Raport> getRaportByMonth(int month)
	{
		QueryBuilder<Raport, Integer> customerQb = this.helper.getRaportRuntimeDao().queryBuilder();
		SelectArg nameSelectArg = new SelectArg();
		// this gives the c.name = ?
		try {
			customerQb.where().eq("miesiac", nameSelectArg);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Raport> results = null;
		// then you set the args and run the query
		nameSelectArg.setValue(month);
		try {
			results = customerQb.query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}
	
	
	public List<Raport> updateRaportMonth(Raport raport)
	{
		QueryBuilder<Raport, Integer> customerQb = this.helper.getRaportRuntimeDao().queryBuilder();
		
		 this.helper.getRaportRuntimeDao().update(raport);
		
		
		List<Raport> results = null;
		return results;
	}
}
