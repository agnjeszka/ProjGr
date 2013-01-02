//TO REFACTOR

package pg.is.projgr.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class BarGraph {
	public Intent getIntent(Context context)
	{
		int[] x={10,12,9,16,3,7,8,4,21,18}; //expenses
		int[] x2={8,10,10,12,13,9,18,14,20,16}; //income
		
		CategorySeries series = new CategorySeries("Wydatki");
		for (int i=0;i<x.length;i++)
		{
			series.add("Bar "+(i+1),x[i]);
		}
		
		CategorySeries series2 = new CategorySeries("Dochody");
		for (int i=0;i<x2.length;i++)
		{
			series2.add("Bar "+(i+1),x2[i]);
		}
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		dataset.addSeries(series2.toXYSeries());
		
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setDisplayChartValues(true);
		renderer.setChartValuesSpacing((float)0.45);
		renderer.setColor(Color.rgb(51, 204, 204));
		
		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setDisplayChartValues(true);
		renderer2.setChartValuesSpacing((float)0.45);
		renderer2.setColor(Color.rgb(51, 153, 153));
				
		XYMultipleSeriesRenderer mrenderer = new XYMultipleSeriesRenderer();
		mrenderer.addSeriesRenderer(renderer);
		mrenderer.addSeriesRenderer(renderer2);
		mrenderer.setBackgroundColor(Color.BLACK);
		mrenderer.setApplyBackgroundColor(true);
		mrenderer.setChartTitle("Dochody i wydatki miesiêczne");
		mrenderer.setYTitle("Kwota");
		mrenderer.setXTitle("Miesi¹ce");
		mrenderer.setBarSpacing((float)1);
		mrenderer.setGridColor(Color.DKGRAY);
		mrenderer.setShowGrid(true);
		mrenderer.setZoomButtonsVisible(true);
		mrenderer.setZoomEnabled(true);
		mrenderer.setYAxisMin(0);
		mrenderer.setYAxisMax(25);
		mrenderer.setXAxisMin(0);
		mrenderer.setXAxisMax(11);
		
		Intent intent = ChartFactory.getBarChartIntent(context, dataset, mrenderer, Type.DEFAULT);
				
		return intent;
	}
}
