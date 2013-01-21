package pg.is.projgr;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import pg.is.projgr.actions.Raport;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Raporty extends Activity {

	public static String budzetplus;
	public static String budzetminus;
	public static String budzetrownasie;
	TextView plus;
	TextView minus;
	TextView rowne;
	ArrayList<Raport> raporty;

	private String array_spinner[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.raporty);

		plus = (TextView) findViewById(R.id.textViewPasekPl);
		minus = (TextView) findViewById(R.id.textViewPasekMin);
		rowne = (TextView) findViewById(R.id.textViewPasekRowne);
		
		
	

		try {
			raporty = new ArrayList<Raport>(
					MainActivity.dataGenerator.selectRaporty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		array_spinner = new String[raporty.size()];
		for (int i = 0; i < raporty.size(); i++) {
			array_spinner[i] = "Miesiac: " + (raporty.get(i).getMiesiac()+1);
		}

		Spinner s = (Spinner) findViewById(R.id.raporty_spinner);
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, array_spinner);
		s.setAdapter(adapter);
		
		plus.setText(Float.valueOf(new DecimalFormat("#.##").format(raporty.get(0).getBudzet())).toString());
		minus.setText(Float.valueOf(new DecimalFormat("#.##").format(raporty.get(0).getLaczneWydatki())).toString());
		rowne.setText(Float.valueOf(new DecimalFormat("#.##").format(raporty.get(0).getOszczednosci())).toString());
		
		s.setOnItemSelectedListener(new OnItemSelectedListener() {
		 
			public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
					int position, long id) {

				plus.setText(Float.valueOf(new DecimalFormat("#.##").format(raporty.get(position).getBudzet())).toString());
				minus.setText(Float.valueOf(new DecimalFormat("#.##").format(raporty.get(position).getLaczneWydatki())).toString());
				rowne.setText(Float.valueOf(new DecimalFormat("#.##").format(raporty.get(position).getOszczednosci())).toString());
				
			}

			public void onNothingSelected(AdapterView<?> parentView) {
				// TODO Auto-generated method stub
				
			}

		});
	}

}
