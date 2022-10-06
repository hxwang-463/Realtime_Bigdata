import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TextDoublePair implements Writable {
  private Text source;
  private double probability;

  public TextDoublePair(){
    set(new Text(), -1);
  }
  public TextDoublePair(String first, double second){
    set(new Text(first), second);
  }
  public TextDoublePair(Text first, double second){
    set(first, second);
  }
  public void set(Text first, double second) {
    this.source = first;
    this.probability = second;
  }
  public Text getSource() {
    return source;
  }
  public double getPr() {
    return probability;
  }

  @Override
  public void write(DataOutput out) throws IOException {
    source.write(out);
    out.writeDouble(probability);
  }
  @Override
  public void readFields(DataInput in) throws IOException {
    source.readFields(in);
    probability = in.readDouble();
  }
  @Override
  public String toString() {
    return source + "\t" + probability;
  }
}

public class PageRankMapper 
  extends Mapper<LongWritable, Text, Text, TextDoublePair> {
    private Text outkey = new Text();
    private TextDoublePair outvalue = new TextDoublePair();

    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {
      String line = value.toString();
      String [] arr = line.split("\\s+");
      int len = arr.length;
      String result = arr[1];
      for(int i=2;i<len-1;i++){
          result += " "+ arr[i];
      }
      outvalue.set(new Text(result), -1);
      context.write(new Text(arr[0]), outvalue);
      double pr = Double.parseDouble(arr[len-1])/(len-2);
      for(int i=1;i<len-1;i++){
        outvalue.set(new Text(arr[0]), pr);
        context.write(new Text(arr[i]), outvalue);
      }
    }
}
