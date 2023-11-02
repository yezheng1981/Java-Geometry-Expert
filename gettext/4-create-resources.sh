#!/bin/bash -x

# This scripts creates all ResourceBundle class files in the package jgex.i18n.
# See https://xnap-commons.sourceforge.net/gettext-commons/tutorial.html for details.

set -e

. settings.conf

for i in $SUPPORTED_LANGUAGES; do
 msgfmt --java2 -d .. -r jgex.i18n.Messages -l $i po/$i.po
 done
