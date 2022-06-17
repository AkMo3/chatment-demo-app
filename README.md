# chatment-demo-app

Steps To Run:
1. mvn clean package
2. java -jar ./target/chatment-demo-app-0.0.1-SNAPSHOT.jar
3. Requests:
   - Dog Fact: `curl --location --GET 'http://localhost:8080/facts/dog'`
   - Cat Fact: `curl --location --GET 'http://localhost:8080/facts/cat'`
   - Login: `curl -c cookies.txt 'http://localhost:8080/admin' -X POST -H 'Content-Type: application/x-www-form-urlencoded'
             -H 'Referer: http://localhost:8080/login' -H 'Origin: http://localhost:8080' -H 'Connection: keep-alive'
             --data-raw 'username=admin&password=adminPass'`
   - Log: `curl -b cookies.txt --location --GET 'http://localhost:8080/admin/log'`
  
