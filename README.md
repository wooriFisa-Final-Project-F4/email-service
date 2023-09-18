# Email-Service
> Spring Cloud를 이용한 프로젝트입니다.

## 목차
- [Dependency](#-dependency) <br>
- [Email Service 기능](#-auth-service-기능) <br>
- [Service Architecture](#-service-architecture) <br>
                                                

## 🛠️ Dependency

|           기능           | 기술 스택                                               |
|:--------------------:|:------------------------------------------------------|
|      Spring Boot      | - Spring Framework 2.7.14<br> - Java 17 <br> - Gradle 8.0 |
|     Spring Cloud      | - Eureka <br> - Config |
|  Amazon SES  | - aws-java-sdk-ses 1.12.530  |
|  Kafka  | - Confluent Kafka 7.4.0 <br>- Zookeeper 7.4.0                          |
|  Database   | - Redis        |


## 📝 Email Service 기능

|       기능       | 내용                                                     |
|:--------------:|:-------------------------------------------------------|
|      이메일 인증     | 사용자 회원가입 시 이메일 인증을 위해 이메일로 6자리 코드를 전송하고 Redis에 저장한다. 사용자가 이 코드를 입력하면 비교하여 응답한다.                       |
|      낙찰 알림 이메일    | email-service는  auction-status-updater로부터 발생된 이벤트를 구독하고, 이벤트 발행에 맞춰 낙찰알림을 전송한다.                         |


<details>
<summary>이메일 인증 접기/펼치기</summary>
<br>
사용자는 회원가입시 이메일 인증을 수행해야합니다. 사용자의 이메일과 함께 요청이 들어오면 랜덤 6자리 코드를 생성한후, AWS SES를 이용하여 사용자에게 전송합니다. 동시에 이 6자리 코드를 Redis에 저장합니다.
<br> 
<br> 
이후 사용자가 이메일로 전송받은 6자리 코드를 입력하고 요청을 보내면 Redis에 저장된 값과 비교하여 사용자에게 응답합니다.
<br><br>
<img width="923" alt="스크린샷 2023-09-12 오후 7 29 09" src="https://github.com/wooriFisa-Final-Project-F4/.github/assets/109801772/d96eab1c-8b42-4c35-b909-8c5456494fa4">
</details>

<br>
<details>
<summary>낙찰 알림 이메일 접기/펼치기</summary>
<br>ArteModerni 웹사이트는 [auction-status-updater](https://github.com/wooriFisa-Final-Project-F4/auction-status-updater)를 이용해 매일 자정 경매가 끝난 상품에 대해 상태를 업데이트 하면서 낙찰자를 확인하여 관련 정보로 이벤트를 발행합니다.
  <br>
  <br>email-service는 해당 이벤트를 구독하고 있다가, 이벤트 발행에 맞춰 낙찰 알림을 전송합니다.<br>
<img width="1364" alt="스크린샷 2023-09-12 오후 7 09 04" src="https://github.com/wooriFisa-Final-Project-F4/.github/assets/109801772/856bd9ec-d420-46c2-a798-3b723d5656ce">
</details>

## 🧩 Service Architecture
<img width="1618" alt="Architect (2) 복사본" src="https://github.com/wooriFisa-Final-Project-F4/.github/assets/109801772/27ac2b1d-8624-424f-aefb-4ceda4484b63">
