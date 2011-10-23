package no.gnome.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MaxTemperatureMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Weather> {
	private static final int MISSING = 99999;
	
	public void map(LongWritable key, Text value, OutputCollector<Text, Weather> output, Reporter reporter) throws IOException {
		String line = value.toString();
		String year = line.substring(14,18);
		int month = Integer.parseInt(line.substring(18,20));
		int day = Integer.parseInt(line.substring(20,22));
		String stringTemp = line.substring(24,30);
		int airTemperature = (int)(Double.parseDouble(stringTemp.trim()) * 10);
		String quality = "0";
		if (airTemperature != MISSING && quality.matches("[01459]")) {
			Weather w = new Weather(year, month, day, airTemperature, quality);
			output.collect(new Text(year), w);
		}
	}
}
