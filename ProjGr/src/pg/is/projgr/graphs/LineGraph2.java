//TO REFACTOR

package pg.is.projgr.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class LineGraph2 {
	public Intent getIntent(Context context){
		int[] y={1900,1910,2300,2100,2050,2067,1800,1700,2200,2000}; //Expenses
		int[] x={1,2,3,4,5,6,7,8,9,10}; //Months
		
		int[] y2={1850,1900,2200,2450,2100,2150,2222,1999,2345,2143}; //Income
		
		TimeSeries series = new TimeSeries("Wydatki");
		for (int i=0;i<x.length;i++)
		{
			series.add(x[i],y[i]);
		}
		TimeSeries series2 = new TimeSeries("Dochody");
		for (int i=0;i<x.length;i++)
		{
			series2.add(x[i],y2[i]);
		}
				
		//creates data set from series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		dataset.addSeries(series2);
		
		//gives line its properties, one renderer for one series
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setFillPoints(true);
		renderer.setPointStyle(PointStyle.POINT);
		renderer.setLineWidth(5);
		renderer.setColor(Color.rgb(51, 51, 102));
		
		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setFillPoints(true);
		renderer2.setPointStyle(PointStyle.POINT);
		renderer2.setLineWidth(5);
		renderer2.setColor(Color.rgb(51, 204, 153));
		
		//for grouping series into multiple renderer
		XYMultipleSeriesRenderer mrenderer = new XYMultipleSeriesRenderer();
		mrenderer.addSeriesRenderer(renderer);
		mrenderer.addSeriesRenderer(renderer2);
		mrenderer.setApplyBackgroundColor(true);
		mrenderer.setBackgroundColor(Color.BLACK);
		mrenderer.setChartTitle("Dochody i wydatki miesiêczne");
		mrenderer.setGridColor(Color.DKGRAY);
		mrenderer.setShowGrid(true);
		mrenderer.setZoomButtonsVisible(true);
		mrenderer.setZoomEnabled(true);
		mrenderer.setYTitle("Kwota");
		mrenderer.setXTitle("Miesi¹ce");
		mrenderer.setYAxisMin(0);
		mrenderer.setYAxisMax(2500);
		
		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mrenderer,"Statystyki"); 
		return intent;
	}

}
