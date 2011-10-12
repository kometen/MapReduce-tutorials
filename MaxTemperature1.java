package no.gnome.mapreduce1;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class MaxTemperature1 {
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("Usage: MaxTemperature1 <input path> <output path>");
			System.exit(-1);
		}
		
		JobConf conf = new JobConf(MaxTemperature1.class);
		conf.setJobName("Max temperature");
		
		FileInputFormat.addInputPath(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		
		conf.setMapperClass(MaxTemperatureMapper1.class);
		conf.setReducerClass(MaxTemperatureReducer1.class);
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Weather.class);
		
		JobClient.runJob(conf);
	}
}
