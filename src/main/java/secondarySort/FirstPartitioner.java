package secondarySort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class FirstPartitioner extends Partitioner<IntPair, NullWritable> {
	@Override
	public int getPartition(IntPair key, NullWritable value, int numPartitions) {
		return (key.getFirstInt() * 78) % numPartitions;

	}

}
