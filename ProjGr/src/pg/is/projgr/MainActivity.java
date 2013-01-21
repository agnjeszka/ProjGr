package pg.is.projgr;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import pg.is.projgr.actions.StatisticDataGenerator;
import pg.is.projgr.actions.DatabaseHelper;
import pg.is.projgr.actions.Kategoria;
import pg.is.projgr.actions.Podkategoria;
import pg.is.projgr.actions.Produkt;
import pg.is.projgr.actions.Wydatek;

public class MainActivity extends Activity {

	public static StatisticDataGenerator dataGenerator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		dataGenerator = StatisticDataGenerator.getInstance();
		dataGenerator.setDatabaseHelperContext(getApplicationContext());
//		try {
//			Kategoria j = new Kategoria("jedzenie");
//			dataGenerator.insertKategoria(j);
//			Kategoria j4 = new Kategoria("odzie¿");
//			dataGenerator.insertKategoria(j4);
//			Kategoria j1 = new Kategoria("us³ugi");
//			dataGenerator.insertKategoria(j1);
//			Kategoria j2 = new Kategoria("rachunki");
//			dataGenerator.insertKategoria(j2);
//			Kategoria j3 = new Kategoria("inne");
//			dataGenerator.insertKategoria(j3);
//
//			List<Kategoria> kat = dataGenerator.selectKategorie();
//
//			Kategoria j5 = dataGenerator.getKategoriaByName("jedzenie");
//			String k3 = kat.get(0).getNazwa();
//			Log.i("kategoria", j5.getNazwa());
//			Log.i("kategoria", k3);
//
//			 Podkategoria o = new Podkategoria("owoce", j);
//			 dataGenerator.insertPodkategoria(o);
//			 dataGenerator.insertProdukt(new Produkt("jablko", o));
//
//			 Podkategoria p1 = new Podkategoria("restauracja", j);
//			 dataGenerator.insertPodkategoria(p1);
//			 dataGenerator.insertProdukt(new Produkt("obiad", p1));
//
//			 Podkategoria p5 = dataGenerator.getPodkategoriaByName("restauracja");
//			 
//			 Podkategoria p6 = new Podkategoria("buty", j4);
//			 dataGenerator.insertPodkategoria(p6);
//			 dataGenerator.insertProdukt(new Produkt("kozaki", p6));
//
//			 Podkategoria p7 = new Podkategoria("transport", j1);
//			 dataGenerator.insertPodkategoria(p7);
//			 dataGenerator.insertProdukt(new Produkt("taksówka", p7));
//			 
//			 Podkategoria p8 = new Podkategoria("rozrywka", j3);
//			 dataGenerator.insertPodkategoria(p8);
//			 dataGenerator.insertProdukt(new Produkt("kino", p8));
//			 dataGenerator.insertProdukt(new Produkt("koncert", p8));
//			 dataGenerator.insertProdukt(new Produkt("spektakl", p8));
//		
//			
//			 Log.i("Produkt",dataGenerator.getProduktByName("kino").get(0).getNazwa());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		try {

			List<Wydatek> wydatki = MainActivity.dataGenerator.selectWydatki();
			Wydatek w = wydatki.get(wydatki.size() - 1);
			Log.d("Main Activity nazwa", w.getNazwa());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setContentView(R.layout.activity_main);

		Button klik = (Button) findViewById(R.id.button1);
		Button klik2 = (Button) findViewById(R.id.button3);
		Button klik3 = (Button) findViewById(R.id.button2);

		klik2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent startNewActivityOpen = new Intent(MainActivity.this,
						Raporty.class);
				startActivityForResult(startNewActivityOpen, 0);
			}
		});

		klik.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent startNewActivityOpen = new Intent(MainActivity.this,
						Klikniecia.class);
				startActivityForResult(startNewActivityOpen, 0);
				// startActivity(new Intent("pg.is.projgr.KLIKNIECIA"));
			}
		});

		klik3.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent startNewActivityOpen = new Intent(MainActivity.this,
						Statystyki.class);
				startActivityForResult(startNewActivityOpen, 0);
				//startActivity(new Intent("pg.is.projgr.KLIKNIECIA"));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
