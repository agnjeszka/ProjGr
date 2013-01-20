package pg.is.projgr;

import java.sql.SQLException;
import java.util.ArrayList;

import pg.is.projgr.actions.Raport;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Raporty extends Activity {

	public static String budzetplus;
	public static String budzetminus;
	public static String budzetrownasie;

	private String array_spinner[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.raporty);

		ArrayList<Raport> raporty = null;

		try {
			raporty = new ArrayList<Raport>(
					MainActivity.dataGenerator.selectRaporty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		array_spinner = new String[raporty.size()];
		for (int i = 0; i < raporty.size(); i++) {
			array_spinner[i] = "Miesiac: " + raporty.get(i).getMiesiac()+1;
		}

		Spinner s = (Spinner) findViewById(R.id.raporty_spinner);
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, array_spinner);
		s.setAdapter(adapter);

	}

}
