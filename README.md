# ⚽ K-BTI
### 야구의 OPS처럼, 축구의 보이지 않는 가치를 수치화하다

K-BTI는 K리그 경기·전술 데이터를 기반으로 사용자의 축구 성향을 정량화하고, 가장 잘 맞는 K리그 팀을 데이터 기반으로 추천하는 서비스입니다.

---

## 1. 프로젝트 개요

- 기존 축구 평가는 득점, 도움 등 단편적 기록에 집중되어 있음
- 수비, 압박, 전술적 기여도는 수치로 설명되기 어려움
- K-BTI는 팀 전술 데이터를 **성향 벡터**로 재구성하여 사용자 취향과 동일한 수치 공간에서 매칭함

---

## 2. 핵심 아이디어

- 사용자 축구 취향 → **취향 벡터**
- K리그 팀 전술 데이터 → **전술 벡터**
- 두 벡터 간 **유클리드 거리(Euclidean Distance)** 기반 유사도 계산
- 가장 유사한 팀을 추천

---

## 3. 패키지 구조

```text
src/main/java/com/kbti
 ┣ controller
 ┃ ┗ KbtiController.java
 ┣ service
 ┃ ┣ KbtiService.java
 ┃ ┗ CsvMigrationService.java
 ┣ domain
 ┃ ┗ TeamEntity.java
 ┣ repository
 ┃ ┗ TeamRepository.java
 ┣ dto
 ┃ ┣ KbtiRequest.java
 ┃ ┗ KbtiResponse.java
 ┗ KbtiApplication.java
```

---

## 4. 패키지별 역할 설명

### controller

#### KbtiController

- 사용자 요청 진입점
- `/api/kbti/test` 엔드포인트 제공
- 사용자 축구 취향 데이터를 서비스 레이어로 전달

---

### service

#### CsvMigrationService

- K리그 팀 전술 데이터 CSV 파일을 읽어 DB에 저장
- 모든 팀 데이터를 동일 기준으로 정규화
- 추천 시스템의 지식 베이스 역할

#### KbtiService

- 서비스 핵심 비즈니스 로직
- 사용자 취향 벡터 생성
- 팀 전술 벡터와 유사도 계산
- 최적 팀 선정

---

### domain

#### TeamEntity

- K리그 팀 전술 성향 엔티티
- 팀별 템포, 압박, 패스 성향 등 수치 정보 저장

---

### repository

#### TeamRepository

- TeamEntity JPA Repository
- 팀 전술 데이터 조회 담당

---

### dto

#### KbtiRequest

- 사용자 축구 취향 입력 DTO
- 모든 값은 1~5점 척도

```json
{
  "tempo": 5,
  "pressing": 4,
  "passing": 2,
  "attitude": 3
}
```

#### KbtiResponse

- 추천 결과 응답 DTO
- 추천 팀 정보 및 전술 요약 포함

---

## 5. 서비스 동작 흐름

1. CsvMigrationService를 통해 팀 전술 데이터 적재
2. 사용자가 축구 취향 입력
3. 취향 벡터와 팀 전술 벡터 간 유클리드 거리 계산
4. 가장 유사한 팀을 추천 결과로 반환

---

## 6. 기술 스택

- Backend: Spring Boot
- ORM: JPA (Hibernate)
- Database: MySQL
- Data Source: K리그 경기·전술 CSV
- Analysis: Euclidean Distance 기반 벡터 유사도

---

## 7. 한 줄 요약

> K-BTI는 축구 데이터를 성향과 취향의 언어로 번역하는 데이터 기반 팀 추천 서비스입니다.
