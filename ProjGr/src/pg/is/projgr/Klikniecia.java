package pg.is.projgr;

<<<<<<< HEAD
import java.sql.SQLException;
=======
import java.text.DateFormat;
import java.util.Calendar;
>>>>>>> 0fd1b7c54ecf5a384f64911ba7749b99e80651de

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;

public class Klikniecia extends Activity{
	public static String nazwa1;
	public static String nazwa2;
	public static int pozycja;
	public static String nazwa3="Ustaw date";
	
	DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
	TextView lblDateAndTime;
	Calendar myCalendar = Calendar.getInstance();

	DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
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
		
		
		TextView txtProduct = (TextView) findViewById(R.id.Podkategoria);
		 
        Intent i = getIntent();
        String product;
		try {
			product = i.getStringExtra(MainActivity.dbHelper.selectWydatki().get(0).getNazwa());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // displaying selected product name
        try {
			txtProduct.setText(MainActivity.dbHelper.selectWydatki().get(0).getNazwa());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        EditText txtProduct2 = (EditText) findViewById(R.id.editText1);
        txtProduct2.setText(nazwa1);
        EditText txtProduct3 = (EditText) findViewById(R.id.editText2);
        txtProduct3.setText(nazwa2);
        Spinner txtProduct4 = (Spinner) findViewById(R.id.kategorie_spinner);
        txtProduct4.setSelection(pozycja);
		
        Button klik3 = (Button) findViewById(R.id.button4);
        Button klik4 = (Button) findViewById(R.id.button2);
        klik3.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				EditText txtProduct2 = (EditText) findViewById(R.id.editText1);
				nazwa1 = txtProduct2.getText().toString();
				
				EditText txtProduct3 = (EditText) findViewById(R.id.editText2);
				nazwa2 = txtProduct3.getText().toString();
				
				Spinner txtProduct4 = (Spinner) findViewById(R.id.kategorie_spinner);
				pozycja = txtProduct4.getSelectedItemPosition();
				
				TextView lblDateAndTime = (TextView) findViewById(R.id.button2);
				nazwa3 = lblDateAndTime.getText().toString();
				
				Intent startNewActivityOpen = new Intent(Klikniecia.this, AndroidListViewActivity.class);
				startActivityForResult(startNewActivityOpen, 0);
			}
		});
        klik4.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent startNewActivityOpen = new Intent(Klikniecia.this, DateTimeDemo1.class);
				startActivityForResult(startNewActivityOpen, 0);
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
        
	}
	
	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
