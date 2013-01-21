package pg.is.projgr;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pg.is.projgr.actions.Kategoria;
import pg.is.projgr.actions.Podkategoria;
import pg.is.projgr.actions.Produkt;
import pg.is.projgr.actions.Raport;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;

public class Klikniecia extends Activity {
	public static String nazwa1;
	public static String nazwa2;
	public static int pozycja;
	public static String nazwa3 = "Ustaw date";
	public static String kategoria;

	DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
	TextView lblDateAndTime;
	Calendar myCalendar = Calendar.getInstance();
	AlertDialog alertDialog;
	EditText txtProduct2;
	EditText txtProduct3;
	Spinner txtProduct4;
	TextView txtPodkategoria;
	Date date;
	AlertDialog alertDialogDodano;

	DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			date = new Date(year, monthOfYear, dayOfMonth);
			myCalendar.set(Calendar.YEAR, year);
			myCalendar.set(Calendar.MONTH, monthOfYear);
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

			updateLabel();

		}
	};

	private void updateLabel() {
		lblDateAndTime.setText(fmtDateAndTime.format(myCalendar.getTime()));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wydatek);

		txtPodkategoria = (TextView) findViewById(R.id.Podkategoria);

		Intent i = getIntent();
		String product;
		product = i.getStringExtra("product");
		txtPodkategoria.setText(product);

		this.txtProduct2 = (EditText) findViewById(R.id.editText1);
		txtProduct2.setText(nazwa1);

		txtProduct3 = (EditText) findViewById(R.id.editText2);
		txtProduct3.setText(nazwa2);
		txtProduct4 = (Spinner) findViewById(R.id.kategorie_spinner);
		txtProduct4.setSelection(0);

		Button klik3 = (Button) findViewById(R.id.button4);
		Button zatwierdzBtn = (Button) findViewById(R.id.zatwierdz);
		Button klik4 = (Button) findViewById(R.id.button2);
		Button lupkaBtn = (Button) findViewById(R.id.Lupka);

		klik3.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				EditText txtProduct2 = (EditText) findViewById(R.id.editText1);
				nazwa1 = txtProduct2.getText().toString();

				EditText txtProduct3 = (EditText) findViewById(R.id.editText2);
				nazwa2 = txtProduct3.getText().toString();

				Spinner txtProduct4 = (Spinner) findViewById(R.id.kategorie_spinner);
				pozycja = txtProduct4.getSelectedItemPosition();
				kategoria = txtProduct4.getSelectedItem().toString();

				TextView lblDateAndTime = (TextView) findViewById(R.id.button2);
				nazwa3 = lblDateAndTime.getText().toString();

				Intent startNewActivityOpen = new Intent(Klikniecia.this,
						AndroidListViewActivity.class);
				startActivityForResult(startNewActivityOpen, 0);
			}
		});
		klik4.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent startNewActivityOpen = new Intent(Klikniecia.this,
						DateTimeDemo1.class);
				startActivityForResult(startNewActivityOpen, 0);
			}
		});

		lupkaBtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				KategoryzujProdukt(txtProduct2.getText().toString());
			}
		});

		lblDateAndTime = (TextView) findViewById(R.id.button2);
		Button btnDate = (Button) findViewById(R.id.button2);
		btnDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new DatePickerDialog(Klikniecia.this, d, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();

			}
		});

		updateLabel();

		Button txtProduct5 = (Button) findViewById(R.id.button2);
		txtProduct5.setText(nazwa3);

		zatwierdzBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				SimpleDateFormat pdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
				Date dateLocal = new Date(2013, 01, 20);
				try {
					dateLocal = pdf.parse(lblDateAndTime.getText().toString());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateLocal);
				int month = cal.get(Calendar.MONTH);
				Log.d("date",""+ month);
				List<Raport> raporty = MainActivity.dataGenerator.getRaportByMonth(month);
				if (raporty.size() == 0) {
					float number = Float.valueOf(txtProduct3.getText()
							.toString());
					Raport raport = new Raport(dateLocal.getMonth(), dateLocal.getYear(),
							2000.0f, 1000.0f - number, 0.0f + number);
					try {
						MainActivity.dataGenerator.insertRaport(raport);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					Raport raport = raporty.get(0);
					float number = Float.valueOf(txtProduct3.getText()
							.toString());
					raport.odejmijWydatek(number);
					MainActivity.dataGenerator.updateRaportMonth(raport);
				}

				Kategoria kat = MainActivity.dataGenerator.getKategoriaByName(kategoria);
				Podkategoria pod = MainActivity.dataGenerator.getPodkategoriaByName(txtPodkategoria.getText().toString());
				try {
					MainActivity.dataGenerator.insertProdukt(new Produkt(txtProduct2.getText().toString(),pod));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				alertDialogDodano = new AlertDialog.Builder(Klikniecia.this)
						.create();
				alertDialogDodano.setTitle("Dodano");
				alertDialogDodano.setMessage("Dodano wydatek do bazy");
				alertDialogDodano.show();

				Intent startNewActivityOpen = new Intent(Klikniecia.this,
						MainActivity.class);
				startActivityForResult(startNewActivityOpen, 0);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void KategoryzujProdukt(String nazwa) {
		try {
			Podkategoria p = MainActivity.dataGenerator.kategoryzujProduktPoNazwie(nazwa.toLowerCase());

			if (p == null) {
				alertDialog = new AlertDialog.Builder(this).create();
				alertDialog.setTitle("Nie znaleziono produktu");
				alertDialog.setMessage("Dodaj produkt do bazy");
				alertDialog.show();
			} else {
				txtProduct4.setSelection(p.getKategoria().getID() - 1);
				kategoria = p.getKategoria().getNazwa();
				txtPodkategoria.setText(p.getNazwa());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
