#!/bin/bash -x

# This script creates the missing localized .po files and merges the keys into them.
# See https://xnap-commons.sourceforge.net/gettext-commons/tutorial.html for details.

set -e

. settings.conf

for i in $SUPPORTED_LANGUAGES; do
 test -r po/$i.po || {
  xgettext po/keys.pot -o po/$i.po
  sed -i 's/charset=CHARSET/charset=UTF-8/g' po/$i.po # set the CHARSET
  }
 msgmerge -U po/$i.po po/keys.pot
 done
