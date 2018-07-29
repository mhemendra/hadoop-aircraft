import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Cluster;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobID;
import org.apache.hadoop.mapreduce.TaskCounter;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AircraftDelay extends Configured implements Tool {

	public int run(String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("Usage: AircraftDelay <Job Id>");
			System.exit(-1);
		}
		String jobID = args[0];
	    Cluster cluster = new Cluster(getConf());
	    Job job = cluster.getJob(JobID.forName(jobID));
	    if (job == null) {
	      System.err.printf("No job with ID %s found.\n", jobID);
	      return -1;
	    }
	    if (!job.isComplete()) {
	      System.err.printf("Job %s is not complete.\n", jobID);
	      return -1;
	    }

	    Counters counters = job.getCounters();
	    long missing = counters.findCounter(
	    		"Arrival Delay By Week","1").getValue();
	    long total = counters.findCounter("Total Flight by dayofweek","1").getValue();

	    System.out.printf("Percentage of Flights with Arrival Delay on Monday: %.2f%%\n",
	        100.0 * missing / total);
	    return 0;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new AircraftDelay(), args);
		System.exit(exitCode);
	}
}
