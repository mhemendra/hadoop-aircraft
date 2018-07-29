package sort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AircraftSortMapper extends Mapper<LongWritable, Text, IntWritable, Text> {
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		System.out.println("Line is:"+line);
		String arrivalDelay;
		int delay;
		if (!line.contains("Year")) {
			String[] columns = line.split(",");
			arrivalDelay = columns[14];
			if (arrivalDelay.equals("NA")) {
				delay = 0;
			} else {
				delay = Integer.parseInt(arrivalDelay);
			}

			context.write(new IntWritable(delay), value);
		}
	}
}
