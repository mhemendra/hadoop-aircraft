package multipleOut;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class AircraftMultioutReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
	private MultipleOutputs<IntWritable, IntWritable> multiout;

	@Override
	protected void setup(Context context) {
		multiout = new MultipleOutputs<IntWritable, IntWritable>(context);
	}

	@Override
	public void reduce(IntWritable key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int dayofWeek = key.get();
		int count = 0;
		Iterator<IntWritable> iterator = values.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			count++;
		}

		multiout.write(key, new IntWritable(count), new Integer(dayofWeek).toString());
		// context.write(key, new IntWritable(count));

	}
	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException{
		multiout.close();
	}
}
