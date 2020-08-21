#!/bin/bash
DIR="$( cd "$( dirname "$0"  )" && pwd  )"
helm install paas ${DIR}/../paas
