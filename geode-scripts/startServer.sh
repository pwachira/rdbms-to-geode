#!/bin/bash
mkdir -p /data/server
sleep 25
gfsh run --file /geode-scripts/startServer.gfsh
trap 'gfsh -e "connect --locator=locators[10334]"  -e "stop server --name=server" && exit 0 ' SIGTERM
while true; do
    sleep 10
done
