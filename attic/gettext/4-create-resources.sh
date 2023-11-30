#!/bin/bash -x

# This scripts creates all ResourceBundle class files in the package i18n. It creates ../classes/i18n/Messages*.class,
# therefore, before or after a successful Java build this script should also be called (before starting the application)
# to put these files in the .jar bundle as well.
#
# That is, the following scenario should work: "cd ..; ant clean init; cd gettext; ./4-create-resources.sh; cd ..; ant all"
#
# See https://xnap-commons.sourceforge.net/gettext-commons/tutorial.html for details.

set -e

. settings.conf

for i in $SUPPORTED_LANGUAGES; do
 msgfmt --java2 -d ../classes -r i18n.Messages -l $i po/$i.po
 done
