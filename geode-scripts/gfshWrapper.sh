#!/bin/bash
#gfsh "start locator $@" # -e "connect" -e  "configure pdx --auto-serializable-classes=com\.example\.springoneplatform\.scs\.demo\.model\.pdx\.* --read-serialized=true"
gfsh run --file /geode-scripts/gfshWrapper.gfsh

#trap 'gfsh -e "connect" -e "shutdown --include-locators=true" && exit 0 ' SIGTERM
while true; do
  sleep 10
done
