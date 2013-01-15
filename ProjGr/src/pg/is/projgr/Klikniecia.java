package pg.is.projgr;

import java.sql.SQLException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;

public class Klikniecia extends Activity{
	public static String nazwa1;
	public static String nazwa2;
	public static int pozycja;
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
				
				Intent startNewActivityOpen = new Intent(Klikniecia.this, AndroidListViewActivity.class);
				startActivityForResult(startNewActivityOpen, 0);
			}
		});
        klik4.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent startNewActivityOpen = new Intent(Klikniecia.this, Kalendarz.class);
				startActivityForResult(startNewActivityOpen, 0);
			}
		});
		
	}
	
	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
