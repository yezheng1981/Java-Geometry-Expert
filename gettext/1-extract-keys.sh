#!/bin/bash -x

# This scripts extract keys into the template .pot file.
# It does a good job, but not all texts can be, unfortunately, extracted.
# See https://xnap-commons.sourceforge.net/gettext-commons/tutorial.html for details.

set -e

test -r po/keys.pot && {
 echo "The file po/keys.pot already exists. Remove it if you want to recreate it."
 exit 1
 }

. package-version.sh
. settings.conf

# Collect names
xgettext -ktr -kgetLanguage -kgetLanguage:2 -kaddItem \
 -ksendAction:1 -kaddAMenu:2 -kaddRadioButtonMenuItem:2 \
 -o po/names.pot -L Java --from-code=UTF-8 \
 --copyright-holder="JGEX Contributors" --package-name=jgex --package-version=$PACKAGE_VERSION \
 --msgid-bugs-address="$MAINTAINER_EMAIL" \
 ../wprover/*.java ../gprover/*.java ../UI/*.java ../maths/*.java ../pdf/*.java

# Collect tooltips
xgettext -kaddAMenu:3 -kaddRadioButtonMenuItem:3 -kmakeAButton:3 \
 -o po/tooltips.pot -L Java --from-code=UTF-8 \
 --copyright-holder="JGEX Contributors" --package-name=jgex --package-version=$PACKAGE_VERSION \
 --msgid-bugs-address="$MAINTAINER_EMAIL" \
 ../wprover/*.java ../gprover/*.java ../UI/*.java ../maths/*.java ../pdf/*.java

# Collect additional texts
xgettext -kaddRadioButtonMenuItem:4 -kmakeAButton:4 \
 -o po/texts.pot -L Java --from-code=UTF-8 \
 --copyright-holder="JGEX Contributors" --package-name=jgex --package-version=$PACKAGE_VERSION \
 --msgid-bugs-address="$MAINTAINER_EMAIL" \
 ../wprover/*.java ../gprover/*.java ../UI/*.java ../maths/*.java ../pdf/*.java

# Collect all strings from certain files
xgettext -a -o po/all.pot -L Java --from-code=UTF-8 \
 --copyright-holder="JGEX Contributors" --package-name=jgex --package-version=$PACKAGE_VERSION \
 --msgid-bugs-address="$MAINTAINER_EMAIL" \
 ../wprover/MProveTree.java ../wprover/CProperty.java

# Remove unnecessary entries from po/all.pot, including images and strings beginning with spaces
cat po/all.pot | \
 awk '{if (index($0, ".gif") == 0) print $0; else getline; }' | \
 awk '{if (index($0, "images/") == 0) print $0; else getline; }' | \
 awk '{if (index($0, "msgid \" ") == 0) print $0; else getline; }' \
 > po/all-filtered.pot

# Get English language keys, use JGEX's English.lan file and extract the second column
rm -f po/old.pot
set -f # Disable expansion of *
cat ../language/English.lan | dos2unix | grep -v ^Font | grep '#' | \
 awk -F '#' '{gsub(/^[ \t]+|[ \t]+$/,"", $1); print $2}' | while read key; do
 key=`echo $key | sed s/\"/\'/g` # Change quotes to apostrophes
 echo -e "msgid \"$key\"\nmsgstr \"\"\n" >> po/old.pot
 done

xgettext po/names.pot po/tooltips.pot po/texts.pot po/all-filtered.pot po/old.pot -o po/keys.pot

# Cleanup
rm po/names.pot po/tooltips.pot po/texts.pot po/all-filtered.pot po/all.pot po/old.pot
