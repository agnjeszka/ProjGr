package pg.is.projgr;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button klik = (Button) findViewById(R.id.button1);
        Button klik2 = (Button) findViewById(R.id.button3);
        Button klik3 = (Button) findViewById(R.id.button2);
        
        klik2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent startNewActivityOpen = new Intent(MainActivity.this, Raporty.class);
				startActivityForResult(startNewActivityOpen, 0);
			}
		});
        
        klik.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent startNewActivityOpen = new Intent(MainActivity.this, Klikniecia.class);
				startActivityForResult(startNewActivityOpen, 0);
				//startActivity(new Intent("pg.is.projgr.KLIKNIECIA"));
			}
		});
        
        klik3.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent startNewActivityOpen = new Intent(MainActivity.this, Statystyki.class);
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
