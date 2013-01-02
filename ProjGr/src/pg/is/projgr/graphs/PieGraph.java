//TO REFACTOR

package pg.is.projgr.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.R.string;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class PieGraph {
//	public Intent getIntent(Context context)
	public GraphicalView getView(Context context)
	{
		int[] values = {1,2,4,7,2,4};
		String[] cathegories = {"Rachunki","Us³ugi","Jedzenie","Kosmetyki","Rozrywka","Inne"};
		
		CategorySeries series = new CategorySeries("Kategorie");
		for (int i=0;i<values.length;i++)
		{
			series.add(cathegories[i],values[i]);
		}
		 
		int[] colors = new int[] {Color.BLUE,Color.GREEN,Color.CYAN,Color.MAGENTA,Color.RED,Color.YELLOW};
		
		DefaultRenderer renderer = new DefaultRenderer();
		for (int color: colors)
		{
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		
		renderer.setChartTitle("Podkategorie wydatków");
		renderer.setZoomButtonsVisible(true);
		renderer.setZoomEnabled(true);
		renderer.setBackgroundColor(Color.BLACK);
		renderer.setApplyBackgroundColor(true);
				
		//Intent intent ChartFactory.getPieChartIntent(context, series, renderer,"Pie");
		//return intent;
		
		return ChartFactory.getPieChartView(context, series, renderer);
	}
}
