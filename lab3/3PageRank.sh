#!/bin/bash
echo Run Page Rank Algorithm for 3 times!
input = $1
output = $2
hadoop fs -rm -r -f $output
hadoop fs -rm -r -f "temp1"

hadoop jar pageRank.jar PageRank $input temp1
echo finish 1st time!
hadoop fs -rm -r -f "temp2"
hadoop jar pageRank.jar PageRank temp1/part-r-00000 temp2
echo finish 2nd time!
hadoop jar pageRank.jar PageRank temp2/part-r-00000 $2
echo "finish 3nd time!"
hadoop fs -rm -r -f "temp1"
hadoop fs -rm -r -f "temp2"
echo All done. Saved in $2