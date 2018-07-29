package multipleIn;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AircraftFrequencyMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
	enum Delay {
		ARRDELAY, DEPDELAY
	}

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		int dayOfWeek;
		if (!line.contains("Year")) {
			String[] columns = line.split(",");

			dayOfWeek = Integer.parseInt(columns[3]);
			context.write(new IntWritable(dayOfWeek), new IntWritable(1));

		}
	}
}
