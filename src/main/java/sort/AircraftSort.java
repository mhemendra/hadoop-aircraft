package sort;

import java.net.URI;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.lib.InputSampler;
import org.apache.hadoop.mapred.lib.TotalOrderPartitioner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AircraftSort extends Configured implements Tool {

	public int run(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: MaxTemperature <input path> <output path>");
			System.exit(-1);
		}

		Job job = new Job(getConf());
		job.setJarByClass(AircraftSort.class);
		job.setJobName("Aircraft Total Sort");

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setInputFormatClass(KeyValueTextInputFormat.class);
		job.setOutputKeyClass(Text.class);
		job.setPartitionerClass(TotalOrderPartitioner.class);
		InputSampler.Sampler<Text, Text> sampler = new InputSampler.RandomSampler<Text, Text>(
				0.1, 10000, 10);
		//TotalOrderPartitioner.setPartitionFile(job.getConfiguration(), new Path("/tmp/partition.lst"));
		InputSampler.writePartitionFile(job, sampler);
		String partitionFile=TotalOrderPartitioner.getPartitionFile(job.getConfiguration());
		URI partitionUri=new URI(partitionFile);
		job.addCacheFile(partitionUri);
		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new AircraftSort(), args);
		System.exit(exitCode);
	}
}
