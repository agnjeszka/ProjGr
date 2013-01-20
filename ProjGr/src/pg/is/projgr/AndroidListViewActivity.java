package pg.is.projgr;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pg.is.projgr.actions.Kategoria;
import pg.is.projgr.actions.Podkategoria;
import pg.is.projgr.actions.Wydatek;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AndroidListViewActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// storing string resources into Array
		String[] adobe_products = getResources().getStringArray(
				R.array.adobe_products);
		Kategoria kat = MainActivity.dataGenerator.getKategoriaByName(Klikniecia.kategoria);
		ArrayList<Podkategoria> podkategorieList = null;
		
			podkategorieList = new ArrayList<Podkategoria>( kat.getPodkategorie());
		
		final int dlugosc = podkategorieList.size();

		String[] wydatki = new String[dlugosc+1];
		
		for (int i = 0; i < dlugosc; i++) {
			wydatki[i] = podkategorieList.get(i).getNazwa();
		}
		wydatki[dlugosc] = "Dodaj podkategoriê" ;
		
		// final int dlugosc=adobe_products.length;
		// Binding resources Array to ListAdapter
		this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
				R.id.label, wydatki));

		ListView lv = getListView();

		// listening to single list item on click
 		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position != dlugosc) {
					// selected item

					String product = ((TextView) view).getText().toString();

					// Launching new Activity on selecting single List Item
					Intent i = new Intent(getApplicationContext(),
							Klikniecia.class);
					// sending data to new activity
					i.putExtra("product", product);
					startActivity(i);
				} else {

					Intent startNewActivityOpen = new Intent(
							AndroidListViewActivity.this, DodajKategorie.class);
					startActivityForResult(startNewActivityOpen, 0);
				}
			}

		});
	}
}
