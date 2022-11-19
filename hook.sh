#!/bin/bash

mkdir -p ./.git/hooks
ln -s ../../script/pre-push .git/hooks/pre-push
chmod +x .git/hooks/pre-push