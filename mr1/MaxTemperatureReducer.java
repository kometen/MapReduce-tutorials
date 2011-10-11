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
		Weather w = new Weather();
		Weather wm = new Weather();	// max. temp.
		while (values.hasNext()) {
			w = (Weather) values.next();
			wm = (wm.getTemperature() > w.getTemperature()) ? wm : w;
		}
		String out = wm.getYear() + "," + Integer.toString(wm.getMonth()) + "," + Integer.toString(wm.getDay());
		output.collect(key, new Text(out));
	}
}
