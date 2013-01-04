package pg.is.projgr.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import pg.is.projgr.StatisticDataGenerator;

import android.content.Context;
import android.graphics.Color;

public class BarGraph {
	private float[] _expenses;
	private String _title;
	
	public BarGraph(float[] array, String title){
		_expenses=array;
		_title=title;
	}
	
	public GraphicalView getView(Context context)
	{
		float[] x=_expenses;
		
		CategorySeries series = new CategorySeries("Wydatki");
		for (int i=0;i<x.length;i++)
		{
			series.add("Bar "+(i+1),x[i]);
		}
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setDisplayChartValues(true);
		renderer.setChartValuesSpacing((float)0.45);
		renderer.setColor(Color.rgb(51, 204, 204));
				
		XYMultipleSeriesRenderer mrenderer = new XYMultipleSeriesRenderer();
		mrenderer.addSeriesRenderer(renderer);
		mrenderer.setBackgroundColor(Color.BLACK);
		mrenderer.setApplyBackgroundColor(true);
		mrenderer.setChartTitle(_title);
		mrenderer.setYTitle("Kwota");
		mrenderer.setXTitle("Miesi¹ce");
		mrenderer.setBarSpacing((float)1);
		mrenderer.setGridColor(Color.DKGRAY);
		mrenderer.setShowGrid(true);
		mrenderer.setZoomButtonsVisible(true);
		mrenderer.setZoomEnabled(true);
		mrenderer.setYAxisMin(0);
		mrenderer.setYAxisMax(StatisticDataGenerator.GetMax(_expenses));
		mrenderer.setXAxisMin(0);
		mrenderer.setXAxisMax(x.length+1);
				
		return ChartFactory.getBarChartView(context, dataset, mrenderer, Type.DEFAULT);
	}
}
