package secondarySort;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AircraftSecondarySort extends Configured implements Tool {

	public int run(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: MaxTemperature <input path> <output path>");
			System.exit(-1);
		}

		Job job = new Job(getConf());
		job.setJarByClass(AircraftSecondarySort.class);
		job.setJobName("Aircraft Total Sort");

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setOutputKeyClass(IntPair.class);
		job.setOutputValueClass(NullWritable.class);
		job.setMapperClass(AircraftSecondarySortMapper.class);
		job.setReducerClass(AircraftSecondarySortReducer.class);
		job.setPartitionerClass(FirstPartitioner.class);
		//job.setSortComparatorClass(KeyComparator.class);
		job.setGroupingComparatorClass(GroupComparator.class);
		
		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new AircraftSecondarySort(), args);
		System.exit(exitCode);
	}
}
