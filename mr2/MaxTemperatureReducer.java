package no.gnome.mapreduce;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class MaxTemperatureReducer extends MapReduceBase implements Reducer<Text, Weather, Text, Text> {
	public void reduce(Text key, Iterator<Weather> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {
		Weather wmax = new Weather();
		Weather wcurmax = new Weather("1967", 10, 28, Integer.MIN_VALUE, "1");	// max. temp.
		Weather wmin = new Weather();
		Weather wcurmin = new Weather("1967", 10, 28, Integer.MAX_VALUE, "1");	// max. temp.
		while (values.hasNext()) {
			wmax = (Weather) values.next();
			wmin = (Weather) wmax;
			if (wmax.getTemperature() > wcurmax.getTemperature()) {
				wcurmax.setYear(wmax.getYear());
				wcurmax.setMonth(wmax.getMonth());
				wcurmax.setDay(wmax.getDay());
				wcurmax.setTemperature(wmax.getTemperature());
				wcurmax.setQuality(wmax.getQuality());
			}
			if (wmin.getTemperature() < wcurmin.getTemperature()) {
				wcurmin.setYear(wmin.getYear());
				wcurmin.setMonth(wmin.getMonth());
				wcurmin.setDay(wmin.getDay());
				wcurmin.setTemperature(wmin.getTemperature());
				wcurmin.setQuality(wmin.getQuality());
			}
		}
		String outmax = "max: " + wcurmax.getYear() + "," + Integer.toString(wcurmax.getMonth()) + "," + Integer.toString(wcurmax.getDay()) + "," + Integer.toString(wcurmax.getTemperature());
		String outmin = "min: " + wcurmin.getYear() + "," + Integer.toString(wcurmin.getMonth()) + "," + Integer.toString(wcurmin.getDay()) + "," + Integer.toString(wcurmin.getTemperature());
		output.collect(key, new Text(outmax));
		output.collect(key, new Text(outmin));
	}
}
