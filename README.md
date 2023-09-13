# email-service

## Overview

Spring Cloud 프로젝트입니다. 이 프로젝트는 다음과 같은 주요 기능 및 라이브러리를 활용하고 있습니다

- Eureka Client for service discovery
- AWS SES
- Spring for Kafka
- Spring Data Redis

## API

### 이메일 인증

사용자는 회원가입시 이메일 인증을 수행해야합니다. 사용자의 이메일과 함께 요청이 들어오면 랜덤 6자리 코드를 생성한후, AWS SES를 이용하여 사용자에게 전송합니다. 동시에 이 6자리 코드를 Redis에 저장합니다.
<br>  
이후 사용자가 이메일로 전송받은 6자리 코드를 입력하고 요청을 보내면 Redis에 저장된 값과 비교하여 사용자에게 응답합니다.

<details>
<summary>템플릿 펼치기/접기</summary>
<img width="923" alt="스크린샷 2023-09-12 오후 7 29 09" src="https://github.com/wooriFisa-Final-Project-F4/.github/assets/109801772/d96eab1c-8b42-4c35-b909-8c5456494fa4">
</details>

### 낙찰 알림 이메일

ArteModerni 웹사이트는 [auction-status-updater](https://github.com/wooriFisa-Final-Project-F4/auction-status-updater)를 이용해 매일 자정 경매가 끝난 상품에 대해 상태를 업데이트 하면서 낙찰자를 확인하여 관련 정보로 이벤트를 발행합니다. email-service는 해당 이벤트를 구독하고 있다가, 이벤트 발행에 맞춰 낙찰 알림을 전송합니다.

<details>
<summary>템플릿 펼치기/접기</summary>

<img width="1364" alt="스크린샷 2023-09-12 오후 7 09 04" src="https://github.com/wooriFisa-Final-Project-F4/.github/assets/109801772/856bd9ec-d420-46c2-a798-3b723d5656ce">
</details>

## Requirements

- Java 17
- Spring Cloud

## Stack

<p align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/redis/redis-original.svg" alt="redis" width="40" height="40"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" alt="spring" width="40" height="40"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" alt="java" width="40" height="40"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/apachekafka/apachekafka-original.svg" width="40" height="40"/>
</p>

## Mechanism

### Project Architecture

<img width="1618" alt="Architect (2) 복사본" src="https://github.com/wooriFisa-Final-Project-F4/.github/assets/109801772/27ac2b1d-8624-424f-aefb-4ceda4484b63">
