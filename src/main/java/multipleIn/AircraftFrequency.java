package multipleIn;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AircraftFrequency extends Configured implements Tool {

	public int run(String[] args) throws Exception {
		if (args.length != 3) {
			System.err.println("Usage: MaxTemperature <input path1> <input path2> <output path>");
			System.exit(-1);
		}
		Job job = new Job();
		job.setJarByClass(AircraftFrequency.class);
		job.setJobName("Aircraft Frequency");
		MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, AircraftFrequencyMapper.class);
		MultipleInputs.addInputPath(job, new Path(args[1]), SequenceFileInputFormat.class,
				AircraftFrequencyOneRemovedlMapper.class);
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		job.setReducerClass(AircraftFrequencyReducer.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);

		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new AircraftFrequency(), args);
		System.exit(exitCode);
	}
}
