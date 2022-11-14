#!/bin/bash

function myfunc ()
{

    if test "$BASH_COMMAND" = "git log -2";
    then
        echo git only api
        exit
    else
        return 0
    fi
}

shopt -s extdebug
trap "myfunc" DEBUG

echo 2
git log -2