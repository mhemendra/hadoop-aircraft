import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class AircraftFrequencyReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

	@Override
	public void reduce(IntWritable key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
int dayofWeek=key.get();
		int count = 0;
		Iterator<IntWritable> iterator = values.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			count++;
		}
		context.getCounter("Total Flight by dayofweek", Integer.toString(dayofWeek)).increment(count);
		context.write(key, new IntWritable(count));

	}
}
