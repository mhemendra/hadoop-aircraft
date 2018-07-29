package multipleOut;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AircraftMultioutMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		int dayofWeek;
		if (!line.contains("Year")) {
			String[] columns = line.split(",");

			dayofWeek = Integer.parseInt(columns[3]);
			context.write(new IntWritable(dayofWeek), new IntWritable(1));
		}
	}
}
