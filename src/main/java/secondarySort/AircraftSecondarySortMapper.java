package secondarySort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AircraftSecondarySortMapper extends Mapper<LongWritable, Text, IntPair, NullWritable> {
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		//int depDelay;
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
/*			if (columns[15].equals("NA")) {
				depDelay = 0;
			} else {
				depDelay = Integer.parseInt(columns[14]);
			}*/

			context.write(new IntPair(year, dayofWeek), NullWritable.get());
		}
	}
}
