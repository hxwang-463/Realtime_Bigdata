import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class threeint implements Writable {
    private Integer count;
    private Integer death;
    private Integer injured;

    public threeint(Integer count, Integer death, Integer injured){
        set(count, death, injured);
    }
    public void set(Integer count, Integer death, Integer injured){
        this.count = count;
        this.death = death;
        this.injured = injured;
    }
    public Integer getcount(){
        return count;
    }
    public Integer getdeath(){
        return death;
    }
    public Integer getinjured(){
        return injured;
    }
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(count);
        out.writeInt(death);
        out.writeInt(injured);
    }
    @Override
    public void readFields(DataInput in) throws IOException {
        count = in.readInt();
        death = in.readInt();
        injured = in.readInt();
    }
}
