import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;


public class CollisionReducer 
    extends Reducer<Text, threeint, Text, threeint> {
  @Override
  public void reduce(Text key, Iterable<threeint> values, Context context)
      throws IOException, InterruptedException {
    
    Integer count = 0;
    Integer death = 0;
    Integer injured = 0;
    for (threeint val : values) {
      count += val.getcount();
      death += val.getdeath();
      injured += val.getinjured();
    }
    context.write(key, new threeint(count, death, injured));
  } 
}
