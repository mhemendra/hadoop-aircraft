package secondarySort;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class AircraftSecondarySortReducer extends Reducer<IntPair, NullWritable, IntPair, NullWritable> {
	public void reduce(IntPair key, Iterable<NullWritable> value, Context context)
			throws IOException, InterruptedException {
		context.write(key, NullWritable.get());
	}
}
