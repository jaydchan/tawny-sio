#!/bin/bash

# Uses the sio_ent.txt to replace SIO IDs with english language
# labels.
#
# Usage: ./replace.sh <FILENAME>

input=$1

for line in $(cat /home/jdwarrender/Repositories/tawny-sio/output/sio_ent.txt)
do
	if [[ "$line" == *,[a-zA-Z_]* ]]
	then
		echo "LINE: " $line
		IFS=', ' read OLD NEW <<< $line
		OLDSUB=${OLD##*/}
		#echo "OLD: " $OLD
		#echo "OLDSUB: " $OLDSUB
		#echo "NEW: " $NEW

		sed -i "s,$OLD,$NEW,g" "$input"
		sed -i "s,$OLDSUB,$NEW,g" "$input"
	fi
done

OLD="&lt;"
NEW='<'
sed -i "s,$OLD,$NEW,g" "$input"

OLD="&gt;"
NEW='>'
sed -i "s,$OLD,$NEW,g" "$input"

OLD="&quot;"
NEW='"'
sed -i "s,$OLD,$NEW,g" "$input"

OLD="&#10;"
NEW=''
sed -i "s,$OLD,$NEW,g" "$input"

OLD="amp;"
NEW=''
sed -i "s,$OLD,$NEW,g" "$input"
