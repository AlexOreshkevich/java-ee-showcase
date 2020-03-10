# How to build
use `build.sh` or 
```$bash
docker build --tag=ibm/broker .
```

# How to run
use `run.sh` or
```$bash
docker run -p 1414:1414 -p 9443:9443 -it ibm/broker:latest
```

# Access web console
Open https://localhost:9443/ibmmq/console/ using default credentials: User: `admin`, Password: `passw0rd`