-javaagent:/Users/tanhua/workspace/pinpoint/agent/target/pinpoint-agent-1.6.3-SNAPSHOT/pinpoint-bootstrap-1.6.3-SNAPSHOT.jar 
-Dpinpoint.agentId=1010 
-Dpinpoint.applicationName=rocketmq

nohup ./bin/mqnamesrv &
nohup sh mqbroker -n localhost:9876 autoCreateTopicEnable=true &
