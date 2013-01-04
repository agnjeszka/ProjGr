package pg.is.projgr.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import android.content.Context;
import android.graphics.Color;

public class PieGraph {

	private float[] _expenses;
	private String[] _categories;
	private String _title;
	
	public PieGraph(float[] expenses, String[] categories, String title){
		_expenses=expenses;
		_categories=categories;
		_title=title;
	}
	
	public GraphicalView getView(Context context)
	{
		float[] values = _expenses;
		String[] categories = _categories;
		
		CategorySeries series = new CategorySeries("Kategorie");
		for (int i=0;i<values.length;i++)
		{
			series.add(categories[i],values[i]);
		}
		 
		int[] colorsDictionary = new int[] {Color.BLUE,Color.GREEN,Color.CYAN,Color.MAGENTA,Color.RED,Color.YELLOW};
		int[] colors = new int[_categories.length];
		
		for (int i=0;i<=colors.length;i++){
			if ((colors.length % colorsDictionary.length==1) && (i==colors.length)) {
				colors[i]=colorsDictionary[(i+1) % colorsDictionary.length];
			}
			else{
				colors[i]=colorsDictionary[i%colorsDictionary.length];
			}
		}
		
		DefaultRenderer renderer = new DefaultRenderer();
		for (int color: colors)
		{
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		
		renderer.setChartTitle(_title);
		renderer.setZoomButtonsVisible(true);
		renderer.setZoomEnabled(true);
		renderer.setBackgroundColor(Color.BLACK);
		renderer.setApplyBackgroundColor(true);
				
		return ChartFactory.getPieChartView(context, series, renderer);
	}
}
