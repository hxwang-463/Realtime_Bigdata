import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class PageRankReducer 
    extends Reducer<Text, TextDoublePair, Text, NullWritable> {
  @Override
  public void reduce(Text key, Iterable<TextDoublePair> values, Context context)
      throws IOException, InterruptedException {
    
    double pr = 0;
    String destinations;
    for (TextDoublePair val : values) {
      String source = val.getSource().toString();
      double probability = val.getPr();
      if (probability==-1) {
        destinations = source;
      }
      else {
        pr += probability;
      }

    }
    String output = key+" "+destinations+" "+String.valueOf(pr);
    context.write(new Text(output), null);
  } 
}
