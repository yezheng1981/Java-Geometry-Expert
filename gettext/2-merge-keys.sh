#!/bin/bash -x

# This scripts merges keys into localized .po files.
# See https://xnap-commons.sourceforge.net/gettext-commons/tutorial.html for details.

set -e

. settings.conf

for i in $SUPPORTED_LANGUAGES; do
 test -r po/$i.po || touch po/$i.po
 msgmerge -U po/$i.po po/keys.pot
 done
