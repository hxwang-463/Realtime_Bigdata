#!/bin/bash
if [ $# != 2 ] ; then
echo "USAGE: bash run.sh input_file output_directory"
exit 1;
fi

hadoop fs -rm -r -f $2

echo start
hadoop jar Collision.jar Collision_data_ingestion $1 $2
echo All done. Saved in $2