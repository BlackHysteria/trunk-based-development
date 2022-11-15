#!/bin/bash

function myfunc ()
{

    if test "$BASH_COMMAND" = "git push";
    then
        echo git only through api!
        curl --location --request GET 'http://localhost:8080/run/'
        exit
    else
        return 0
    fi
}

shopt -s extdebug
trap "myfunc" DEBUG

echo start
git push