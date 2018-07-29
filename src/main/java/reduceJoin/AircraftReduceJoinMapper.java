package reduceJoin;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import secondarySort.IntPair;

public class AircraftReduceJoinMapper extends Mapper<LongWritable, Text, IntPair, IntWritable> {
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		int arrDelay;
		if (!line.contains("Year")) {
			String[] columns = line.split(",");
			int year = Integer.parseInt(columns[0]);
			int dayofWeek = Integer.parseInt(columns[3]);
			if (columns[14].equals("NA")) {
				arrDelay = 0;
			} else {
				arrDelay = Integer.parseInt(columns[14]);
			}
			int dayOfMonth = Integer.parseInt(columns[2]);
			context.write(new IntPair(year, dayofWeek), new IntWritable(dayOfMonth));
		}
	}
}
