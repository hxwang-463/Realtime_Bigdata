import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Collision {

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: Collision <input path> <output path>");
      System.exit(-1);
    }
    
    Job job = Job.getInstance();
    job.setJarByClass(Collision.class);
    job.setJobName("data ingestion for collision");
    job.setNumReduceTasks(1);

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    
    job.setMapperClass(CollisionMapper.class);
    job.setCombinerClass(CollisionReducer.class);
    job.setReducerClass(CollisionReducer.class);

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(threeint.class);
    
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
