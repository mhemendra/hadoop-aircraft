package reduceJoin;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import secondarySort.IntPair;

public class AircraftReduceJoinReducer extends Reducer<IntPair, NullWritable, IntWritable, IntWritable> {
	public void reduce(IntPair key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		
		  Iterator<IntWritable> iter = values.iterator();
		while (iter.hasNext()) {
			IntWritable record = iter.next();
	        
	        context.write(new IntWritable(key.getFirstInt()), record);
	      }
		System.out.println("Printing Key after setting to Context"+key);
	}
}
