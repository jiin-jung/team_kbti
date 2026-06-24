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
src/main/java/kleague/kbti
 ┣ controller
 ┃ ┣ KbtiController.java
 ┃ ┣ TeamController.java
 ┃ ┗ PlayerController.java
 ┣ service
 ┃ ┣ KbtiService.java
 ┃ ┣ TeamQueryService.java
 ┃ ┣ PlayerQueryService.java
 ┃ ┗ CsvMigrationService.java
 ┣ recommendation
 ┃ ┣ code
 ┃ ┣ matcher
 ┃ ┗ vector
 ┣ mapper
 ┃ ┣ TeamResponseMapper.java
 ┃ ┗ PlayerResponseMapper.java
 ┣ exception
 ┃ ┣ GlobalExceptionHandler.java
 ┃ ┗ ErrorResponse.java
 ┣ config
 ┃ ┗ WebConfig.java
 ┣ model
 ┃ ┣ TeamTactics.java
 ┃ ┣ TacticalVector.java
 ┃ ┗ KbtiDimension.java
 ┣ domain
 ┃ ┗ TeamEntity.java
 ┣ repository
 ┃ ┣ TeamRepository.java
 ┃ ┗ TeamTacticsRepository.java
 ┣ loader
 ┃ ┣ TeamTacticsCsvLoader.java
 ┃ ┣ TeamRankingCsvLoader.java
 ┃ ┣ PlayerRatingsCsvLoader.java
 ┃ ┗ row
 ┣ dto
 ┃ ┣ request
 ┃ ┗ response
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

서비스 계층은 비즈니스 흐름을 조합합니다. 추천 규칙, 벡터 변환, 거리 계산처럼 변경 가능성이 큰 세부 구현은 `recommendation` 패키지의 인터페이스 뒤로 분리했습니다. 응답 DTO 조립은 `mapper` 패키지로 분리해 서비스가 조회/필터링 흐름에 집중하도록 했습니다.

#### CsvMigrationService

- K리그 팀 전술 데이터 CSV 파일을 읽어 DB에 저장
- 모든 팀 데이터를 동일 기준으로 정규화
- 추천 시스템의 지식 베이스 역할

#### KbtiService

- 서비스 핵심 비즈니스 로직
- 사용자 취향 벡터 생성은 `PreferenceVectorMapper`에 위임
- 팀 전술 벡터와 유사도 계산은 `TeamMatcher`에 위임
- 최적 팀 선정

---

### recommendation

- `KbtiCodeGenerator`: 사용자 입력과 팀 전술 데이터에서 KBTI 코드를 생성하는 전략
- `PreferenceVectorMapper`: 사용자 요청 DTO를 전술 벡터로 변환하는 전략
- `TeamMatcher`: 사용자 벡터와 팀 벡터를 비교해 최적 팀을 찾는 전략
- 기본 구현은 임계값 기반 코드 생성, 20점 스케일 벡터 변환, 유클리드 거리 매칭을 사용

새 추천 알고리즘이나 코드 생성 규칙을 추가할 때 기존 서비스 수정 대신 새 구현체를 추가해 교체할 수 있도록 OCP 기준으로 분리했습니다.

---

### exception

- `GlobalExceptionHandler`: validation, not found, data load 실패를 표준 에러 응답으로 변환
- `ErrorResponse`: API 에러 응답 공통 포맷
- `ResourceNotFoundException`, `DataLoadException`: 서비스/로더 계층의 의미 있는 예외 타입

---

### config

- `WebConfig`: API CORS 정책 관리
- `KbtiApiProperties`: `kbti.api.allowed-origins` 설정 바인딩
- 기본 profile은 H2 console과 SQL 로그를 끄고, `dev` profile에서 개발 편의 설정을 켭니다.

---

### model

- `TeamTactics`: 팀별 전술 점수와 식별 정보를 담는 내부 모델
- `TacticalVector`: 거리 계산 가능한 전술 벡터 값 객체
- `KbtiDimension`: KBTI 코드 상세 설명 모델

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
- `dto.request`에 위치
- 모든 값은 1~5점 척도

```json
{
  "tempo": 5,
  "directness": 2,
  "pressing": 4,
  "fight": 3
}
```

#### KbtiResponse

- 추천 결과 응답 DTO
- `dto.response`에 위치
- 추천 팀 정보 및 전술 요약 포함
- CSV 입력 행은 API DTO가 아니므로 `loader.row`에 분리

---

## 5. 서비스 동작 흐름

1. CsvMigrationService를 통해 팀 전술 데이터 적재
2. 사용자가 축구 취향 입력
3. `PreferenceVectorMapper`가 사용자 취향을 전술 벡터로 변환
4. `TeamMatcher`가 팀 전술 벡터와 비교해 가장 가까운 팀을 선택
5. 추천 결과를 응답 DTO로 반환

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
