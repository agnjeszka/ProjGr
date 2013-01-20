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

	private int _statIndex = 0;
	private int _chartsNumber;
	public List<GraphicalView> Charts;
	private LinearLayout _layout;
	private Button _prev;
	private Button _next;
	private StatisticDataGenerator _stats;

	Context con;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statystyki);
<<<<<<< HEAD
		
		_stats = StatisticDataGenerator.getInstance();
		
=======
		con = getApplicationContext();

		_stats = new StatisticDataGenerator();

>>>>>>> adc17c42dc49abe6d0d6e79e757442e4ea0e2c95
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
		if (_statIndex==(_chartsNumber-1)){
			_next.setEnabled(false);
			_prev.setEnabled(true);
		}
		else if (_statIndex==0){
			_prev.setEnabled(false);
			_next.setEnabled(true);
		}
		else{
			_next.setEnabled(true);
			_prev.setEnabled(true);
		}
	}

	public List<GraphicalView> loadCharts(List<GraphicalView> gViewList){
		if (_stats.GetExpensesMonthly() == null) {
			gViewList.add((new FilledLineGraph(new float[]{(float) 1217.15 ,(float) 1725.28,(float) 1435.98,(float) 1536.50},"Wydatki miesiêczne")).getView(this));
		}
		else
		{
			gViewList.add((new FilledLineGraph(_stats.GetExpensesMonthly(),"Wydatki miesiêczne")).getView(this));

		}

		if (_stats.GetIncomeMonthly() == null) {
			gViewList.add((new FilledLineGraph(new float[]{(float) 1800 ,(float) 1800,(float) 1900,(float) 1900},"Dochody miesiêczne")).getView(this));

		}
		else
		{
			gViewList.add((new FilledLineGraph(_stats.GetIncomeMonthly(),"Dochody miesiêczne")).getView(this));
		}

		if (_stats.GetSavingsMonthly() == null) {
			gViewList.add((new FilledLineGraph(new float[]{(float) 582.85 ,(float) 74.72,(float) 464.02,(float) 363.50},"Dochody miesiêczne")).getView(this));
		}
		else
		{
			gViewList.add((new FilledLineGraph(_stats.GetSavingsMonthly(),"Dochody miesiêczne")).getView(this));
		}

		if ((_stats.GetExpensesMonthly() == null) || (_stats.GetIncomeMonthly() == null)) {
			gViewList.add((new TwoLineGraph(new float[]{(float) 1217.15 ,(float) 1725.28,(float) 1435.98,(float) 1536.50},new float[]{(float) 1800 ,(float) 1800,(float) 1900,(float) 1900},"Wydatki i dochody miesiêczne")).getView(this));
		}
		else
		{
			gViewList.add((new TwoLineGraph(_stats.GetExpensesMonthly(),_stats.GetIncomeMonthly(),"Wydatki i dochody miesiêczne")).getView(this));
		}

		String[] _titles = {"Wydatki z tego miesi¹ca","Wydatki z poprzedniego miesi¹ca","Wydatki sprzed dwóch miesiêcy"};

		//interface
		//		for (int i=0;i<3;i++){
		//			if (_stats.GetRaportsCount()-i>0){
		//				gViewList.add((new BarGraph(_stats.GetIthMonthExpenses(i),_titles[i])).getView(this));
		//			}
		//		}

		//sample data
		gViewList.add((new BarGraph(new float[] {324,6,2,546,8,2,34,4,6,3,34,7,34,2,6,86,78,123,3,1,43,46,16,32,275,59,234,632,436,6,12,4,6,37},_titles[0])).getView(this));
		gViewList.add((new BarGraph(new float[] {734,34,5,2,6,2,6,7,5,4,45,534,34,5,7,5,5,1,33,6,7,2,5,3,55,3,2,2,232,2,5,6,5,78,8,8,7,65,3,55},_titles[1])).getView(this));
		gViewList.add((new BarGraph(new float[] {485,3,49,59,2,3,4,7,309,8,6,58,7,96,34,9,3,93,28,5,32,5,6,354,2,2,45,7,62,3,43,35,1,1,2,4,7,7},_titles[2])).getView(this));

		String[] _titles2 = {"Kategorie wydatków w tym miesi¹cu","Kategorie wydatków w poprzednim miesi¹cu","Kategorie wydatków sprzed 2 miesiêcy"};

		//interface
		//		for (int i=0;i<3;i++){
		//			if (_stats.GetRaportsCount()-i>0){
		//				gViewList.add((new PieGraph(_stats.GetIthMonthExpensesByCategories(i),_stats.GetMainCategories(),_titles2[i])).getView(this));
		//			}
		//		}

		//sample data
		gViewList.add((new PieGraph(new float[] {450,600,120,90,100,130},new String[] {"jedzenie", "rachunki", "odzie¿", "komunikacja", "us³ugi", "inne"},_titles2[0])).getView(this));
		gViewList.add((new PieGraph(new float[] {350,400,180,90,120,30},new String[] {"jedzenie", "rachunki", "odzie¿", "komunikacja", "us³ugi", "inne"},_titles2[1])).getView(this));
		gViewList.add((new PieGraph(new float[] {480,500,110,90,50,160},new String[] {"jedzenie", "rachunki", "odzie¿", "komunikacja", "us³ugi", "inne"},_titles2[2])).getView(this));

		//interface
		//		for (String category : _stats.GetMainCategories()){
		//			String[] _titles3 = {"Podkategorie wydatków z kategorii "+ category+" z tego miesi¹ca","Podkategorie wydatków z kategorii "+ category+" z poprzedniego miesi¹ca","Podkategorie wydatków z kategorii "+ category+" sprzed dwóch miesiêcy"};
		//			for (int i=0;i<3;i++){
		//				gViewList.add((new PieGraph(_stats.GetIthMonthExpensesBySubcategories(i, category),_stats.GetSubCategories(category),_titles3[i])).getView(this));
		//			}
		//		}

		//sample data
		gViewList.add((new PieGraph(new float[] {33,57,120,40,200},new String[] {"owoce","warzywa","miêso","s³odycze","inne"},"Podkategorie wydatków z kategorii jedzenie z tego miesi¹ca")).getView(this));
		gViewList.add((new PieGraph(new float[] {278,100,10,50,20},new String[] {"czynsz","pr¹d","gaz","internet","telefon"},"Podkategorie wydatków z kategorii rachunki z tego miesi¹ca")).getView(this));
		gViewList.add((new PieGraph(new float[] {110,60,200,30},new String[] {"spodnie","koszulki","obuwie","inne"},"Podkategorie wydatków z kategorii odzie¿ z tego miesi¹ca")).getView(this));
		gViewList.add((new PieGraph(new float[] {70,20},new String[] {"miesiêczne","pojedyncze"},"Podkategorie wydatków z kategorii komunikacja z tego miesi¹ca")).getView(this));
		gViewList.add((new PieGraph(new float[] {55,20,99},new String[] {"fryzjer","klub","si³ownia"},"Podkategorie wydatków z kategorii us³ugi z tego miesi¹ca")).getView(this));
		
		return gViewList;
	}
}
