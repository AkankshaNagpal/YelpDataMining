package com.yelp.checkin;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CheckInParser {
	
private static final String filePath = 
"/Users/akankshanagpal/Downloads/checkin_test.json";

public static void main(String[] args)
{
	try
	{
		HashMap<String,Integer> getCheckInCount = new HashMap();
		FileReader reader = new FileReader(filePath);
		JSONParser  jsonParser = new JSONParser();
		JSONArray level1Obj = (JSONArray) jsonParser.parse(reader);
		
		for(int i=0; i<level1Obj.size();i++)
		{
			JSONObject obj = (JSONObject) level1Obj.get(i);
			String bid = (String) obj.get("business_id");
			JSONObject checkin = (JSONObject) obj.get("checkin_info");
			int count = checkin.size();
			//System.out.println(count);
		    getCheckInCount.put(bid, count);
		}
		
		System.out.println(getCheckInCount);
		DefaultPieDataset dataset = new DefaultPieDataset( );
		for(Entry<String, Integer> mpEntry : getCheckInCount.entrySet())
		{
			dataset.setValue(mpEntry.getKey(), new Double( mpEntry.getValue()) );
		}
		
	
	      JFreeChart chart = ChartFactory.createPieChart(
	         "Checkin Info", // chart title
	         dataset, // data
	         true, // include legend
	         true,
	         true);
	         
	      int width = 640; /* Width of the image */
	      int height = 480; /* Height of the image */ 
	      File pieChart = new File( "PieChart_CheckIn.jpeg" ); 
	      ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
	      System.out.println("File Saved");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	

}
