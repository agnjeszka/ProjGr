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

public class LineGraph {
	public Intent getIntent(Context context){
		int[] y={-100,120,250,-123,56,67,110,106}; //Balance
		int[] x={1,2,3,4,5,6,7,8}; //Months
		
		TimeSeries series = new TimeSeries("Oszczêdnoœci");
		for (int i=0;i<x.length;i++)
		{
			series.add(x[i],y[i]);
		}
		
		//creates data set from series
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		//dataset.addSeries(series2)
		
		//gives line its properties, one renderer for one series
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setFillPoints(true);
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setLineWidth(5);
		renderer.setColor(Color.rgb(51, 153, 102));
		renderer.setFillBelowLine(true);
		renderer.setFillBelowLineColor(Color.rgb(51, 204, 153));
		
		//for grouping series into multiple renderer
		XYMultipleSeriesRenderer mrenderer = new XYMultipleSeriesRenderer();
		mrenderer.addSeriesRenderer(renderer);
		mrenderer.setApplyBackgroundColor(true);
		mrenderer.setBackgroundColor(Color.BLACK);
		mrenderer.setChartTitle("Saldo miesiêczne");
		mrenderer.setGridColor(Color.DKGRAY);
		mrenderer.setShowGrid(true);
		mrenderer.setZoomButtonsVisible(true);
		mrenderer.setZoomEnabled(true);
		mrenderer.setYTitle("Kwota");
		mrenderer.setXTitle("Miesi¹ce");
		mrenderer.setYAxisMin(-500);
		mrenderer.setYAxisMax(500);
		
		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mrenderer,"Statystyki"); 
		return intent;
	}

}
