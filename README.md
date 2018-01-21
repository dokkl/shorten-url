**프로젝트 소개**

shorten-url 은 웹화면에서 URL을 입력받아 짧게 줄여주고, Shortening된 URL을 브라우저에 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service 입니다.

_개발환경_

tool : intellij
java version : 1.8
spring boot version : 1.5.9.RELEASE
spring starter : web, data-jpa, jdbc, thymeleaf, test
dependency : h2, lombok, commons-validator
build tool : gradle

_실행환경_

spring boot 에 embedd tomcat 에서 동작
인메모리 DB h2 를 사용하였고 프로젝트(was) 실행시에 테이블, 시퀀스가 생성되고 프로젝트(was) 종료시에 인메모리 DB h2 가 종료되면 모든데이터가 삭제된다.


**빌드방법**

아래 방법 두가지중 선택하여 실행
1. 개발툴 (intellij)에서 gradle projects 창에서 build 실행
2. 프로젝트홈에서 ./gradlew build 실행
   ex) 1. cd /Users/coupang/dev/workspace/shorten-url
       2. ./gradlew build


**실행방법**

아래 방법 두가지중 선택하여 실행
1. HoonShortenUrlApplication 의 main 메소드 실행 (개발 tool(intellij) 에서 실행하기 편한 방법)
2. 프로젝트홈/build/libs 디렉토리로 이동후 jar파일 실행
   ex) 1. cd /Users/coupang/dev/workspace/shorten-url/build/libs
       2. java -jar shorten-url-0.0.1-SNAPSHOT.jar

application.properties 에서 알고리즘을 선택할수있다. (hoon.strategy.algrithm = shortenUrlAlgorithmBase62Impl or shortenUrlAlgorithmBase32Impl)

1. 브라우저에서 http://localhost:8080 입력하면 서비스 화면이 나온다.
   - 화면에서 text box에 original url을 입력하고 ShortenURL 버튼을 클릭하면 shortenUrl이 나온다.
   - shortenURL을 클릭하거나 복사하여 브라우저에서 실행하면 원래의 url로 리다이렉트 서비스 된다.  
   
2. DB 조회 화면
   - 브라우저에서 http://localhost:8080/h2-console 입력하면 h2 DB 연결화면이 나온다.
   - jdbc url : jdbc:h2:mem:testdb, user name : sa 입력하고 connect 버튼 클릭
   - 화면 좌측에서 SHORTEN_URLS 테이블 클릭하고 run 버튼을 클릭하면 테이블 조회가 된다.


**문제해결 전략**

id(key), 원래의 url, shorten url 필드가 있는 테이블을 하나와 id에 적용할 10억1 부터 1씩 자동 증가하는 10자리의 시퀀스하나를 생성한다.
화면에서 원래의 url을 입력받으면 db에 저장하고 저장시에 생성된 10억번대의 숫자 id(key)를 인코딩(url로 사용할수 있도록 문자와 숫자로만) 한다.    
인코딩 위해 2가지 알고리즘(전략)을 준비했다.
1. base62 알고리즘
A-Z, a-z, 0-9 62개의 character 문자열을 모듈러 연산한다.

2. base32 알고리즘
A-Z, 2-7 32개의 character 문자열을 모듈러 연산한다.
참조 : https://www.garykessler.net/library/base64.html 


**테스트 소스**

인테그레이션테스트 : 프로젝트홈/src/integrationtest
단위테스트 : 프로젝트홈/src/test

**제약사항**
1. origin url 의 최대길이는 255자로 한정한다. (DB 커럼의 길이를 varchar(255)로 설정)


