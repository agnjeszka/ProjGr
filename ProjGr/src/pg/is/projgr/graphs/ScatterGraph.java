//TO REFACTOR

package pg.is.projgr.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class ScatterGraph {
	public Intent getIntent(Context context)
	{
		int[] y = {1,3,5,7,9,8,9,4,3,2,8,7}; //amount sold
		double[] x = {4.5,6.3,7.8,4.7,9.8,1.4,1.9,3.5,6.4,4.3,5.9,8.0}; //price
		
		XYSeries series = new XYSeries("Wydatki");
		for (int i=0;i<x.length;i++)
		{
			series.add(x[i],y[i]);
		}
		
		int[] y2 = {5,3,2,5,6,7,1,2,6,3,6,8}; //amount in
		double[] x2 = {3.5,4.6,9.8,3.7,8.8,3.4,2.9,4.5,7.4,5.3,6.9,9.0}; //price
		
		XYSeries series2 = new XYSeries("Dochody");
		for (int i=0;i<x2.length;i++)
		{
			series2.add(x2[i],y2[i]);
		}
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		dataset.addSeries(series2);
		
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setColor(Color.rgb(0, 51, 51));
		renderer.setFillPoints(true);
		renderer.setPointStyle(PointStyle.CIRCLE);
		
		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setColor(Color.rgb(0, 153, 153));
		renderer2.setFillPoints(true);
		renderer2.setPointStyle(PointStyle.CIRCLE);
		
		XYMultipleSeriesRenderer mrenderer = new XYMultipleSeriesRenderer();
		mrenderer.addSeriesRenderer(renderer);
		mrenderer.addSeriesRenderer(renderer2);
		mrenderer.setBackgroundColor(Color.BLACK);
		mrenderer.setApplyBackgroundColor(true);
		mrenderer.setChartTitle("Liczba wydatków w zale¿noœci od ceny");
		mrenderer.setYTitle("Liczba");
		mrenderer.setXTitle("Kwota");
		mrenderer.setGridColor(Color.DKGRAY);
		mrenderer.setShowGrid(true);
		mrenderer.setZoomButtonsVisible(true);
		mrenderer.setZoomEnabled(true);
		mrenderer.setYAxisMin(0);
		mrenderer.setYAxisMax(10);
		mrenderer.setXAxisMin(0);
		mrenderer.setXAxisMax(10);
		
		Intent intent = ChartFactory.getScatterChartIntent(context, dataset, mrenderer);
		
		return intent;
	}
}
