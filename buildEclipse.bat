call mvn eclipse:clean eclipse:eclipse
call mvn clean install -Dmaven.test.skip=true
call mvn dependency:sources