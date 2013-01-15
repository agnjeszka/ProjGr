package pg.is.projgr.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import pg.is.projgr.actions.StatisticDataGenerator;
import android.content.Context;
import android.graphics.Color;

public class FilledLineGraph {
		private float[] _vector;
		private String _title;
	
	public FilledLineGraph(float[] vector,String title){
		_vector=vector;
		_title=title;
	}
	
	public GraphicalView getView(Context context){
		float[] y=_vector;
				
		TimeSeries series = new TimeSeries("");
		for (int i=0;i<y.length;i++)
		{
			series.add(i+1,y[i]);
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
		mrenderer.setChartTitle(_title);
		mrenderer.setGridColor(Color.DKGRAY);
		mrenderer.setShowGrid(true);
		mrenderer.setZoomButtonsVisible(true);
		mrenderer.setZoomEnabled(true);
		mrenderer.setYTitle("Kwota");
		mrenderer.setXTitle("Miesi¹ce");
		mrenderer.setYAxisMin(1.25*StatisticDataGenerator.GetMin(_vector));
		mrenderer.setYAxisMax(1.25*StatisticDataGenerator.GetMax(_vector));
		
		return ChartFactory.getLineChartView(context, dataset, mrenderer);
	}

}
