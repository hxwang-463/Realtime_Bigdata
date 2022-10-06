import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.*;
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
