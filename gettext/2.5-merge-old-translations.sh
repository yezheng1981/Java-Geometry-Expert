#!/bin/bash

# Extract old translations for all configured languages, use JGEX's *.lan files and take the third column.

. settings.conf

for i in $SUPPORTED_LANGUAGES; do

 case $i in
  de)
   l=German
   ;;
  rs)
   l=Serbian
   ;;
 esac

 echo "Extracting old translations from $l.lan..."

 rm -f po/old-$i.po po/$i-merged.po
 cat ../language/$l.lan | dos2unix | grep -v ^Font | grep '#' | sed s/"\""/"\\\\\""/g | \
  awk -F '#' '{gsub(/^[ \t]+|[ \t]+$/,"", $2); gsub(/^[ \t]+|[ \t]+$/,"", $3); print "msgid \"" $2 "\""; print "msgstr \"" $3 "\""; print ""}' > po/old-$i.po
 cat po/$i.po po/old-$i.po > po/$i-merged.po
 msguniq po/$i-merged.po -o po/$i.po
 rm po/$i-merged.po

 done
