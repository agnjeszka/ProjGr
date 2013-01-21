package pg.is.projgr.actions;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import pg.is.projgr.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Database helper class used to manage the creation and upgrading of your
 * database. This class also usually provides the DAOs used by the other
 * classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper implements Parcelable {

	// name of the database file for your application -- change to something
	// appropriate for your app
	private static final String DATABASE_NAME = "budzet2.db";
	// any time you make changes to your database objects, you may have to
	// increase the database version
	private static final int DATABASE_VERSION = 10;

	// wydatek
	private Dao<Wydatek, Integer> wydatekDao = null;
	private RuntimeExceptionDao<Wydatek, Integer> wydatekRuntimeDao = null;

	// kategoria
	private Dao<Kategoria, Integer> kategoriaDao = null;
	private RuntimeExceptionDao<Kategoria, Integer> kategoriaRuntimeDao = null;
	// raport
	private Dao<Raport, Integer> raportDao = null;
	private RuntimeExceptionDao<Raport, Integer> raportRuntimeDao = null;

	// podkategoria
	private Dao<Podkategoria, Integer> podkategoriaDao = null;
	private RuntimeExceptionDao<Podkategoria, Integer> podkategoriaRuntimeDao = null;
	
	//produkt
	private Dao<Produkt, Integer> produktDao = null;
	private RuntimeExceptionDao<Produkt, Integer> produktRuntimeDao = null;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION,
				R.raw.ormlite_config);

	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, Wydatek.class);
			TableUtils.createTable(connectionSource, Raport.class);
			TableUtils.createTable(connectionSource, Kategoria.class);
			TableUtils.createTable(connectionSource, Podkategoria.class);
			TableUtils.createTable(connectionSource, Produkt.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}

		// here we try inserting data in the on-create as a test
		RuntimeExceptionDao<Wydatek, Integer> dao = getWydatekRuntimeDao();
		long millis = System.currentTimeMillis();
		// create some entries in the onCreate
		Wydatek simple = new Wydatek();
		dao.create(simple);
		simple = new Wydatek("Banan", 2.50f, new Date(02, 01, 13));
		dao.create(simple);
		Log.i(DatabaseHelper.class.getName(),
				"created new entries in onCreate: " + millis);
	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Wydatek.class, true);
			TableUtils.dropTable(connectionSource, Raport.class, true);
			TableUtils.dropTable(connectionSource, Kategoria.class, true);
			TableUtils.dropTable(connectionSource, Podkategoria.class, true);
			TableUtils.dropTable(connectionSource, Produkt.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the Database Access Object (DAO) for our SimpleData class. It
	 * will create it or just give the cached value.
	 */
	public Dao<Wydatek, Integer> getWydatekDao() throws SQLException {
		if (wydatekDao == null) {
			wydatekDao = getDao(Wydatek.class);
		}
		return wydatekDao;
	}

	public RuntimeExceptionDao<Wydatek, Integer> getWydatekRuntimeDao() {
		if (wydatekRuntimeDao == null) {
			wydatekRuntimeDao = getRuntimeExceptionDao(Wydatek.class);
		}
		return wydatekRuntimeDao;
	}

	public Dao<Raport, Integer> getRaportDao() throws SQLException {
		if (raportDao == null) {
			raportDao = getDao(Raport.class);
		}
		return raportDao;
	}

	public RuntimeExceptionDao<Raport, Integer> getRaportRuntimeDao() {
		if (raportRuntimeDao == null) {
			raportRuntimeDao = getRuntimeExceptionDao(Raport.class);
		}
		return raportRuntimeDao;
	}

	public Dao<Kategoria, Integer> getKategoriaDao() throws SQLException {
		if (kategoriaDao == null) {
			kategoriaDao = getDao(Kategoria.class);
		}
		return kategoriaDao;
	}

	public RuntimeExceptionDao<Kategoria, Integer> getKategoriaRuntimeDao() {
		if (kategoriaRuntimeDao == null) {
			kategoriaRuntimeDao = getRuntimeExceptionDao(Kategoria.class);
		}
		return kategoriaRuntimeDao;
	}

	public Dao<Podkategoria, Integer> getPodkategoriaDao() throws SQLException {
		if (podkategoriaDao == null) {
			podkategoriaDao = getDao(Podkategoria.class);
		}
		return podkategoriaDao;
	}

	public RuntimeExceptionDao<Podkategoria, Integer> getPodkategoriaRuntimeDao() {
		if (podkategoriaRuntimeDao == null) {
			podkategoriaRuntimeDao = getRuntimeExceptionDao(Podkategoria.class);
		}
		return podkategoriaRuntimeDao;
	}

	public Dao<Produkt, Integer> getProduktDao() throws SQLException {
		if (produktDao == null) {
			produktDao = getDao(Produkt.class);
		}
		return produktDao;
	}

	public RuntimeExceptionDao<Produkt, Integer> getProduktRuntimeDao() {
		if (produktRuntimeDao == null) {
			produktRuntimeDao = getRuntimeExceptionDao(Produkt.class);
		}
		return produktRuntimeDao;
	}


	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		wydatekDao = null;
		wydatekRuntimeDao = null;
		raportDao = null;
		raportRuntimeDao = null;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
}