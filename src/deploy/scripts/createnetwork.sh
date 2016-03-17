#!/usr/bin/env bash

stackstatus=""
attempts=1

STACKNAME="stack-charprop-network"
STACKTEMPLATE="../cloudformation/network.json"
ATTEMPT_LIMIT=10

aws cloudformation create-stack --stack-name $STACKNAME --template-body file://$STACKTEMPLATE
while [ "$stackstatus" != "CREATE_COMPLETE" ]  && [ $attempts  -lt $ATTEMPT_LIMIT ]; do
    echo "$STACKNAME creation in progress"
    sleep 10
    stackstatus=$( aws cloudformation describe-stacks --stack-name $STACKNAME --output text --query "Stacks[0].StackStatus" )
    attempts=$((attempts + 1))
done

if [ $attempts -eq $ATTEMPT_LIMIT ] || [ $stackstatus != "CREATE_COMPLETE" ]; then
    echo "Stack creation FAILED."
    aws cloudformation delete-stack --stack-name $STACKNAME
    exit -1;
fi
