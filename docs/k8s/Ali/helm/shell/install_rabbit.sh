#!/bin/bash
DIR="$( cd "$( dirname "$0"  )" && pwd  )"
helm install rabbitmq ${DIR}/../rabbitmq-ha -n component-rabbitmq
