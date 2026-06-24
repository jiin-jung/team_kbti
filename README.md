# K-BTI - K리그 전술 성향 기반 팀 추천 서비스

<div align="center">
  <h1>K-BTI</h1>
  <p>⚽ 데이터로 찾는 나와 가장 닮은 K리그 팀 ⚽</p>
</div>

<br/>

<div align="center">
  <!-- K-BTI 메인 화면 또는 서비스 대표 배너 -->
  <sub>K-BTI 대표 이미지 추가 예정</sub>
</div>

<br/>

<div align="center">
  <a href="src/main/resources/static/index.html">홈 화면</a>
  &nbsp; | &nbsp;
  <a href="src/main/resources/static/kbti-test.html">K-BTI 테스트</a>
  &nbsp; | &nbsp;
  <a href="src/main/resources/static/team-list.html">팀 목록</a>
  &nbsp; | &nbsp;
  <a href="src/main/resources/static/player-list.html">선수 랭킹</a>
</div>

---

## 프로젝트 개요

- **프로젝트명:** K-BTI
- **프로젝트 형태:** K리그 데이터 기반 WEB 서비스
- **서비스 상태:** 개발 및 리팩토링 진행 중
- **목표:** 사용자의 축구 취향을 전술 벡터로 정량화하고, K리그 팀 전술 데이터와 비교해 가장 잘 맞는 팀을 추천
- **주요 타겟 사용자:**
  - 나와 성향이 맞는 K리그 팀을 찾고 싶은 사용자
  - 팀별 전술 성향을 직관적으로 비교하고 싶은 사용자
  - K리그 팀 순위와 선수 랭킹을 함께 확인하고 싶은 사용자

---

## 프로젝트 소개

### 프로젝트 배경

축구 팀의 매력은 득점, 도움, 승점 같은 단편적인 기록만으로 설명하기 어렵습니다. 어떤 팀은 빠른 전환과 강한 압박이 매력이고, 어떤 팀은 짧은 패스와 안정적인 빌드업으로 경기를 지배합니다.

K-BTI는 이런 전술적 차이를 `tempo`, `directness`, `pressing`, `sideUsage`, `fight` 같은 수치 지표로 재구성합니다. 사용자의 축구 취향도 같은 기준의 벡터로 변환한 뒤, 팀 전술 벡터와의 유클리드 거리를 계산해 가장 가까운 팀을 추천합니다.

### 사용자 니즈

⚽ **팀 추천**

- 내 축구 취향과 잘 맞는 K리그 팀을 알고 싶음
- 단순 인기나 순위가 아니라 전술 성향 기준으로 팀을 추천받고 싶음
- 추천 결과를 KBTI 코드와 설명으로 직관적으로 이해하고 싶음

📊 **팀 정보 탐색**

- K리그 팀별 전개 속도, 패스 직선성, 압박 강도, 투지 지표를 비교하고 싶음
- 팀별 KBTI 코드와 상세 해석을 확인하고 싶음
- 팀 순위와 전술 성향을 함께 보고 싶음

🏃 **선수 랭킹**

- AI 평점과 raw score 기준으로 선수를 비교하고 싶음
- 팀, 포지션, 최소 경기 수 조건으로 선수 랭킹을 필터링하고 싶음
- 상위 선수 목록을 빠르게 확인하고 싶음

---

## 프로젝트 목표

1. **사용자 축구 취향을 정량화하는 KBTI 테스트 제공**

2. **K리그 팀 전술 데이터를 기반으로 한 팀 추천 시스템 구축**

3. **팀 전술 성향, 순위, 선수 랭킹을 함께 확인할 수 있는 API 제공**

4. **OCP를 고려한 추천 전략 확장 구조 설계**

5. **Prometheus와 Grafana 기반 서비스 모니터링 환경 구성**

---

## 주요 기능

### 1. K-BTI 팀 추천

- 사용자 취향 입력값을 1~5점 척도로 수집
- 취향 데이터를 전술 벡터로 변환
- K리그 팀 전술 벡터와 유클리드 거리 기반 비교
- 가장 가까운 팀과 KBTI 코드, 성향 설명 반환

<div align="center">
  <!-- K-BTI 테스트 화면 또는 추천 결과 화면 -->
  <sub>K-BTI 추천 시연 이미지 추가 예정</sub>
</div>

<br/>

### 2. 팀 전술 정보 조회

- 전체 팀 전술 지표 조회
- 팀 ID 기반 단일 팀 상세 조회
- 팀별 KBTI 코드와 코드 상세 설명 제공
- 전개 속도, 패스 직선성, 압박 강도, 측면 활용, 투지 지표 제공

<div align="center">
  <!-- 팀 목록 및 팀 상세 화면 -->
  <sub>팀 전술 정보 시연 이미지 추가 예정</sub>
</div>

<br/>

### 3. K리그 팀 순위 조회

- CSV 기반 K리그 팀 순위 데이터 로드
- 순위, 경기 수, 승점, 득실차, 승무패, 득점, 실점 제공
- 순위 데이터와 팀 전술 데이터를 매칭해 KBTI 코드 함께 제공

<div align="center">
  <!-- 팀 순위 화면 -->
  <sub>팀 순위 시연 이미지 추가 예정</sub>
</div>

<br/>

### 4. 선수 랭킹 조회

- AI 평점 기준 선수 랭킹 제공
- 팀명, 포지션 그룹, 최소 경기 수 필터 제공
- AI 평점, raw score, 경기 수 기준 정렬

<div align="center">
  <!-- 선수 랭킹 화면 -->
  <sub>선수 랭킹 시연 이미지 추가 예정</sub>
</div>

<br/>

### 5. API 예외 처리 및 요청 검증

- 공통 에러 응답 포맷 제공
- request body validation 처리
- path variable, query parameter 검증
- 존재하지 않는 리소스와 데이터 로딩 실패를 명확한 예외 타입으로 분리

```json
{
  "timestamp": "2026-06-24T00:00:00Z",
  "status": 400,
  "code": "INVALID_REQUEST",
  "message": "요청 값이 올바르지 않습니다.",
  "path": "/api/kbti/test",
  "fieldErrors": []
}
```

<br/>

### 6. 모니터링 및 코드 리뷰 자동화

- Spring Boot Actuator health, metrics, prometheus endpoint 제공
- Prometheus scrape 설정 제공
- Grafana datasource 자동 등록
- CodeRabbit PR 리뷰 설정 제공

---

## 팀원 소개

| 이름 | 역할 | 담당 업무 |
|:---:|:---:|:---|
| <a href="https://github.com/jiin-jung"><img src="https://github.com/jiin-jung.png" width="70px"/><br/><sub><b>정지인</b></sub></a> | BE | K-BTI 추천 API, 팀/선수 조회 API, 구조 리팩토링, 모니터링 구성 |

---

## 기술 스택

<table>
  <thead>
    <tr>
      <th>분류</th>
      <th>기술 스택</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Backend</td>
      <td>
        <img src="https://img.shields.io/badge/Java_21-007396?style=flat&logo=openjdk&logoColor=white"/>
        <img src="https://img.shields.io/badge/Spring_Boot_4-6DB33F?style=flat&logo=springboot&logoColor=white"/>
        <img src="https://img.shields.io/badge/Spring_WebMVC-6DB33F?style=flat&logo=spring&logoColor=white"/>
        <img src="https://img.shields.io/badge/Spring_Validation-6DB33F?style=flat&logo=spring&logoColor=white"/>
        <img src="https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=flat&logo=spring&logoColor=white"/>
      </td>
    </tr>
    <tr>
      <td>Database & Data</td>
      <td>
        <img src="https://img.shields.io/badge/H2-09476B?style=flat"/>
        <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white"/>
        <img src="https://img.shields.io/badge/CSV_Data-217346?style=flat"/>
      </td>
    </tr>
    <tr>
      <td>Analysis</td>
      <td>
        <img src="https://img.shields.io/badge/Euclidean_Distance-111111?style=flat"/>
        <img src="https://img.shields.io/badge/Vector_Matching-0052CC?style=flat"/>
      </td>
    </tr>
    <tr>
      <td>Monitoring</td>
      <td>
        <img src="https://img.shields.io/badge/Spring_Actuator-6DB33F?style=flat&logo=spring&logoColor=white"/>
        <img src="https://img.shields.io/badge/Micrometer-1F2937?style=flat"/>
        <img src="https://img.shields.io/badge/Prometheus-E6522C?style=flat&logo=prometheus&logoColor=white"/>
        <img src="https://img.shields.io/badge/Grafana-F46800?style=flat&logo=grafana&logoColor=white"/>
      </td>
    </tr>
    <tr>
      <td>Infra & Tooling</td>
      <td>
        <img src="https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white"/>
        <img src="https://img.shields.io/badge/Gradle-02303A?style=flat&logo=gradle&logoColor=white"/>
        <img src="https://img.shields.io/badge/JUnit5-25A162?style=flat&logo=junit5&logoColor=white"/>
        <img src="https://img.shields.io/badge/Swagger-85EA2D?style=flat&logo=swagger&logoColor=black"/>
        <img src="https://img.shields.io/badge/CodeRabbit-FF5700?style=flat"/>
      </td>
    </tr>
  </tbody>
</table>

---

## 시스템 아키텍처

```text
Client
  └─ Static HTML
       └─ Spring Boot API
            ├─ KBTI Recommendation
            │   ├─ KbtiCodeGenerator
            │   ├─ PreferenceVectorMapper
            │   └─ TeamMatcher
            ├─ CSV Loader
            │   ├─ Team tactics data
            │   ├─ Team ranking data
            │   └─ Player rating data
            ├─ H2 / MySQL
            └─ Actuator
                 └─ Prometheus
                      └─ Grafana
```

---

## 패키지 구조

```text
src/main/java/kleague/kbti
 ┣ config          # CORS, 설정 properties
 ┣ controller      # API endpoint
 ┣ domain          # JPA entity 및 도메인 보조 모델
 ┣ dto             # request / response DTO
 ┣ exception       # 공통 예외 응답 및 예외 타입
 ┣ loader          # CSV 데이터 로더
 ┣ mapper          # response DTO 매핑
 ┣ model           # 추천 내부 모델
 ┣ recommendation  # KBTI 코드, 벡터 변환, 매칭 전략
 ┣ repository      # JPA / CSV repository abstraction
 ┣ service         # 비즈니스 흐름 조합
 ┗ util            # KBTI 설명 유틸
```

---

## API 명세

Swagger UI와 OpenAPI JSON은 애플리케이션 실행 후 아래 경로에서 확인할 수 있습니다.

```text
http://localhost:8080/swagger-ui.html
http://localhost:8080/v3/api-docs
```

### KBTI 추천

```http
POST /api/kbti/test
```

```json
{
  "tempo": 5,
  "directness": 2,
  "pressing": 4,
  "fight": 3
}
```

### 팀 조회

```http
GET /api/teams
GET /api/teams/{teamId}
GET /api/teams/rank
```

### 선수 랭킹

```http
GET /api/players/rank?top=10&team=울산&position=MF&minGames=15
```

---

## 로컬 실행

### 애플리케이션 실행

```bash
./gradlew bootRun
```

### 테스트

```bash
./gradlew test
```

### 개발 profile 실행

```bash
SPRING_PROFILES_ACTIVE=dev ./gradlew bootRun
```

### CSV 마이그레이션 profile 실행

```bash
SPRING_PROFILES_ACTIVE=migration ./gradlew bootRun
```

---

## 모니터링

애플리케이션 실행 후 Actuator endpoint를 확인할 수 있습니다.

```text
http://localhost:8080/actuator/health
http://localhost:8080/actuator/prometheus
```

Prometheus와 Grafana는 Docker Compose로 실행합니다.

```bash
docker compose up -d
```

- Prometheus: `http://localhost:9090`
- Grafana: `http://localhost:3000`
- Grafana 기본 계정: `admin` / `admin`

Prometheus는 기본적으로 `host.docker.internal:8080/actuator/prometheus`를 수집합니다.

---

## CodeRabbit

`.coderabbit.yaml`에서 PR 리뷰 자동화 설정을 관리합니다.

- 리뷰 언어: 한국어
- 자동 리뷰 대상 기본 브랜치: `jiin`, `main`
- 빌드 산출물과 Gradle 캐시는 리뷰 대상에서 제외

---

## 한 줄 요약

<div align="center">
  <h3><strong>축구 취향을 데이터로 번역해, 나와 가장 닮은 K리그 팀을 찾습니다.</strong></h3>
  <p>K-BTI는 팀 전술 데이터와 사용자 취향을 같은 벡터 공간에서 비교하는 데이터 기반 추천 서비스입니다.</p>
</div>
