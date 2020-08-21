#!/bin/bash
DIR="$( cd "$( dirname "$0"  )" && pwd  )"
helm install emqx ${DIR}/../emqx -n component-emqx
