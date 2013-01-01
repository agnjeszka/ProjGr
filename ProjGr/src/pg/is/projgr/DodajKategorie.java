package pg.is.projgr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DodajKategorie extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.dodaj_podkategorie);
		
		Button klik = (Button) findViewById(R.id.button3);
		
		klik.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				EditText txtProduct3 = (EditText) findViewById(R.id.editTextDodPodkat);
				String product = txtProduct3.getText().toString();
	              // Launching new Activity on selecting single List Item
	              Intent i = new Intent(getApplicationContext(), Klikniecia.class);
	              // sending data to new activity
	              i.putExtra("product", product);
	              startActivity(i);
				
				//Intent startNewActivityOpen = new Intent(DodajKategorie.this, Klikniecia.class);
				//startActivityForResult(startNewActivityOpen, 0);
			}
		});
		
	}
	

}
