package pg.is.projgr;

import java.util.ArrayList;
import java.util.List;

import pg.is.projgr.graphs.*;
import org.achartengine.GraphicalView;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Statystyki extends Activity  {

	int statIndex;
	List<GraphicalView> charts;
	int chartsNumber;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statystyki);
		
		Button prev = (Button) findViewById(R.id.statPrev);
		prev.setEnabled(false);
		
		Button next = (Button) findViewById(R.id.statNext);
		next.setEnabled(true);
		
		charts = new ArrayList<GraphicalView>();
		//load
		
		chartsNumber = charts.size();
		//load first
	}
	
	//work in progress
	public void statPrevHandle(View view){
//		PieGraph pie = new PieGraph();
//		GraphicalView gView = pie.getView(this);
//		LinearLayout layout = (LinearLayout)findViewById(R.id.chart);
//		layout.addView(gView);
	}
	
	public void statNextHandle(View view){
//		PieGraph pie = new PieGraph();
//		GraphicalView gView = pie.getView(this);
//		LinearLayout layout = (LinearLayout)findViewById(R.id.chart);
//		layout.addView(gView);
	}


}
