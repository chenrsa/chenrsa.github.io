#!/bin/bash
DIR="$( cd "$( dirname "$0"  )" && pwd  )"
helm install redis-ha-default ${DIR}/../redis-ha -n component-redis
