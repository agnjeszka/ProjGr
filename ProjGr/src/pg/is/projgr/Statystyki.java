package pg.is.projgr;

import java.util.ArrayList;
import java.util.List;
import pg.is.projgr.graphs.*;
import org.achartengine.GraphicalView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import pg.is.projgr.actions.StatisticDataGenerator;

public class Statystyki extends Activity  {

	private int _statIndex;
	private int _chartsNumber;
	public List<GraphicalView> Charts;
	private LinearLayout _layout;
	private Button _prev;
	private Button _next;
	private StatisticDataGenerator _stats;
	
	Context con = getApplicationContext();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statystyki);
		
		_stats = StatisticDataGenerator.getInstance();
		
		_prev = (Button) findViewById(R.id.statPrev);
		_next = (Button) findViewById(R.id.statNext);

		buttonsDisabler();
		
		Charts = new ArrayList<GraphicalView>();
		
		Charts = loadCharts(Charts);
		
		_chartsNumber = Charts.size();
		_layout = (LinearLayout)findViewById(R.id.chart);
		
		_layout.addView(Charts.get(_statIndex));
	}
	
	public void statPrevHandle(View view){
		_layout.removeAllViewsInLayout();
		_statIndex--;
		_layout.addView(Charts.get(_statIndex));
		buttonsDisabler();
	}
	
	public void statNextHandle(View view){
		_layout.removeAllViewsInLayout();
		_statIndex++;
		_layout.addView(Charts.get(_statIndex));
		buttonsDisabler();
	}

	public void buttonsDisabler(){
		if (_statIndex==_chartsNumber){
			_next.setEnabled(false);
		}
		else if (_statIndex==1){
			_prev.setEnabled(false);
		}
		else{
		_next.setEnabled(true);
		_prev.setEnabled(false);
		}
	}
	
	public List<GraphicalView> loadCharts(List<GraphicalView> gViewList){
		gViewList.add((new FilledLineGraph(_stats.GetExpensesMonthly(),"Wydatki miesi�czne")).getView(this));
		gViewList.add((new FilledLineGraph(_stats.GetIncomeMonthly(),"Dochody miesi�czne")).getView(this));
		gViewList.add((new FilledLineGraph(_stats.GetSavingsMonthly(),"Oszcz�dno�ci miesi�czne")).getView(this));

		gViewList.add((new TwoLineGraph(_stats.GetExpensesMonthly(),_stats.GetIncomeMonthly(),"Wydatki i dochody miesi�czne")).getView(this));
		
		String[] _titles = {"Wydatki z tego miesi�ca","Wydatki z poprzedniego miesi�ca","Wydatki sprzed dw�ch miesi�cy"};
		for (int i=0;i<3;i++){
			if (_stats.GetRaportsCount()-i>0){
				gViewList.add((new BarGraph(_stats.GetIthMonthExpenses(i),_titles[i])).getView(this));
			}
		}
		
		String[] _titles2 = {"Kategorie wydatk�w w tym miesi�cu","Kategorie wydatk�w w poprzednim miesi�cu","Kategorie wydatk�w sprzed 2 miesi�cy"};
		for (int i=0;i<3;i++){
			if (_stats.GetRaportsCount()-i>0){
				gViewList.add((new PieGraph(_stats.GetIthMonthExpensesByCategories(i),_stats.GetMainCategories(),_titles2[i])).getView(this));
			}
		}
		
		for (String category : _stats.GetMainCategories()){
			String[] _titles3 = {"Podkategorie wydatk�w z kategorii "+ category+" z tego miesi�ca","Podkategorie wydatk�w z kategorii "+ category+" z poprzedniego miesi�ca","Podkategorie wydatk�w z kategorii "+ category+" sprzed dw�ch miesi�cy"};
			for (int i=0;i<3;i++){
				gViewList.add((new PieGraph(_stats.GetIthMonthExpensesBySubcategories(i, category),_stats.GetSubCategories(category),_titles3[i])).getView(this));
			}
		}
		return gViewList;
	}
}
