#!/bin/bash
if [ $(whoami) != "root" ]; then
  echo "Permission denied"
  exit 1
fi

sudo java -jar ./NanoHttp.jar -p 8080

