package pg.is.projgr.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import pg.is.projgr.StatisticDataGenerator;

import android.content.Context;
import android.graphics.Color;

public class TwoLineGraph {
	private float[] _expenses;
	private float[] _income;
	private String _title;
	
	public TwoLineGraph(float[] expenses,float[] income, String title){
		_expenses=expenses;
		_income=income;
		_title=title;
	}
		
	public GraphicalView getView(Context context){
		float[] y=_expenses;
		float[] y2=_income;
		int _max;
		
		if (StatisticDataGenerator.GetMax(_expenses)>StatisticDataGenerator.GetMax(_income)){
			_max=StatisticDataGenerator.GetMax(_expenses);
		}
		else{
			_max=StatisticDataGenerator.GetMax(_income);
		}
		
		TimeSeries series = new TimeSeries("Wydatki");
		for (int i=0;i<y.length;i++)
		{
			series.add(i+1,y[i]);
		}
		TimeSeries series2 = new TimeSeries("Dochody");
		for (int i=0;i<y.length;i++)
		{
			series2.add(i+1,y2[i]);
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
		mrenderer.setChartTitle(_title);
		mrenderer.setGridColor(Color.DKGRAY);
		mrenderer.setShowGrid(true);
		mrenderer.setZoomButtonsVisible(true);
		mrenderer.setZoomEnabled(true);
		mrenderer.setYTitle("Kwota");
		mrenderer.setXTitle("Miesi¹ce");
		mrenderer.setYAxisMin(0);
		mrenderer.setYAxisMax(1.25*_max);
		
		return ChartFactory.getLineChartView(context, dataset, mrenderer);
	}

}
