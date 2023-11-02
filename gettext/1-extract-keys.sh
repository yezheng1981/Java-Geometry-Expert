#!/bin/bash -x

# This scripts extract keys into the template .pot file.
# See https://xnap-commons.sourceforge.net/gettext-commons/tutorial.html for details.

set -e

test -r po/keys.pot && {
 echo "The file po/keys.pot already exists. Remove it if you want to recreate it."
 exit 1
 }

. package-version.sh
. settings.conf

xgettext -ktr -kgetLanguage -kgetLanguage:2 -o po/keys.pot -L Java --from-code=ASCII \
 --copyright-holder="JGEX Contributors" --package-name=jgex --package-version=$PACKAGE_VERSION \
 --msgid-bugs-address="$MAINTAINER_EMAIL" \
 ../wprover/*.java ../gprover/*.java ../UI/*.java ../maths/*.java ../pdf/*.java
