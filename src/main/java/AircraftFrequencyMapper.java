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
		int dayofWeek;
		if (!line.contains("Year")) {
			String[] columns = line.split(",");

			// if (line.charAt(10) == ',') {
			dayofWeek = Integer.parseInt(columns[3]);
			if (!columns[14].equals("0")) {
				context.getCounter(Delay.ARRDELAY).increment(1);
				context.getCounter("Arrival Delay By Week", Integer.toString(dayofWeek)).increment(1);
			}
			if (!columns[15].equals("0")) {
				context.getCounter(Delay.DEPDELAY).increment(1);
			}
			System.out.println("Key is"+key);
			System.out.println("Value is"+value);
			/*
			 * } else {
			 * 
			 * dayofWeek = Integer.parseInt(line.substring(10, 11)); }
			 */

			context.write(new IntWritable(dayofWeek), new IntWritable(1));
		}
	}
}
