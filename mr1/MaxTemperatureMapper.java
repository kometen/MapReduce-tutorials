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
	private static final int MISSING = 9999;
	
	public void map(LongWritable key, Text value, OutputCollector<Text, Weather> output, Reporter reporter) throws IOException {
		String line = value.toString();
		String year = line.substring(15,19);
		int month = Integer.parseInt(line.substring(19,21));
		int day = Integer.parseInt(line.substring(21,23));
		int airTemperature;
		if (line.charAt(87) == '+') {
			airTemperature = Integer.parseInt(line.substring(88, 92));
		} else {
			airTemperature = Integer.parseInt(line.substring(87, 92));
		}
		String quality = line.substring(92, 93);
		if (airTemperature != MISSING && quality.matches("[01459]")) {
			Weather w = new Weather(year, month, day, airTemperature, quality);
			output.collect(new Text(year), w);
		}
	}
}
