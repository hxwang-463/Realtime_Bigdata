import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;


public class CollisionMapper 
  extends Mapper<LongWritable, Text, Text, threeint> {

    private boolean check_data(String date, String zip, String injured, String killed){
      if(!Pattern.matches("\\d{2}/\\d{2}/\\d{4}", date)) return false;
      if(!Pattern.matches("\\d{5}", zip)) return false;
      if(!Pattern.matches("\\d+", injured)) return false;
      if(!Pattern.matches("\\d+", killed)) return false;
      return true;
    }

    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {
      String line = value.toString();
      String [] arr = line.split(",");

      String crash_date = arr[0];
      String zip = arr[3];
      String injured = arr[11];
      String killed = arr[12];
      if(check_data(crash_date,zip,injured,killed)) {
        context.write(new Text(crash_date+"-"+zip), new threeint(1, Integer.parseInt(killed), Integer.parseInt(injured)));
      }
    }
}
