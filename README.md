# Voca1800 - Gamified Vocabulary Learning Platform

**Voca1800**은 하루 30단어씩 총 60일 동안 1,800개의 필수 단어를 완벽하게 암기하도록 돕는 웹 애플리케이션입니다.
단순한 암기를 넘어, **친구와의 진도 경쟁(Ranking)**과 **게이미피케이션(Gamification)** 요소를 도입하여 학습 동기를 극대화했습니다.

<br>

## Tech Stack

| Category | Technology |
| :--- | :--- |
| **Frontend** | ![Vue.js](https://img.shields.io/badge/Vue.js-35495E?style=flat&logo=vue.js&logoColor=4FC08D) ![Vite](https://img.shields.io/badge/Vite-646CFF?style=flat&logo=vite&logoColor=white) ![Vuetify](https://img.shields.io/badge/Vuetify-1867C0?style=flat&logo=vuetify&logoColor=white) |
| **Backend** | ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white) ![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white) ![JPA](https://img.shields.io/badge/Spring_Data_JPA-gray?style=flat) |
| **Database** | ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=flat&logo=postgresql&logoColor=white) |
| **Infrastructure** | ![Google Cloud](https://img.shields.io/badge/Google_Cloud-4285F4?style=flat&logo=google-cloud&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white) |

<br>

## Architecture

**DDD + Clean Architecture + Hexagonal Architecture** 조합으로 설계했습니다.

```
[Frontend]                    [Backend]                         [DB]
Vue 3 ──→ Axios ──→ Controller(adapter/in) ──→ UseCase(application) ──→ Repository(adapter/out) ──→ PostgreSQL
                              │                       │
                         Request/Response DTO    Port(in/out) 인터페이스
                                                      │
                                                 Domain(Entity, VO)
```

의존성 방향: `adapter → application → domain` (반대 방향 의존 금지)

<br>

## Project Structure

```
voca1800/
├── frontend/                          # Vue 3
│   └── src/
│       ├── api/                       # 도메인별 API 모듈 (Axios)
│       ├── component/                 # 재사용 컴포넌트
│       ├── router/                    # Vue Router
│       └── views/                     # 페이지 뷰
│
├── backend/                           # Spring Boot
│   └── src/main/java/com/voca1800/
│       ├── {domain}/
│       │   ├── domain/                # Entity, Value Object
│       │   ├── application/
│       │   │   ├── port/in/           # UseCase 인터페이스
│       │   │   ├── port/out/          # Repository 포트
│       │   │   └── service/           # UseCase 구현체
│       │   └── adapter/
│       │       ├── in/web/            # Controller + DTO
│       │       └── out/persistence/   # JPA Repository
│       └── global/                    # 공통 설정, 예외 처리
│
├── docs/                              # API 명세서
└── docker-compose.yml
```

<br>

## Domain

| Domain | Description | Endpoints |
| :--- | :--- | :--- |
| **auth** | 회원가입, 로그인, 로그아웃 | `POST /api/auth/signup` `POST /api/auth/login` |
| **user** | 프로필 조회, 수정, 탈퇴 | `GET /api/users/me` `PUT /api/users/me` |
| **follow** | 팔로우, 언팔로우, 유저 검색 | `POST /api/follows/{id}` `GET /api/follows/search` |
| **word** | Day별 단어 목록 (영어, 뜻, 발음) | `GET /api/words/days/{dayNumber}` |
| **progress** | 학습 진도, 외움/미흡, 통계 | `GET /api/progress/days` `POST /api/progress/days/{n}/complete` |
| **ranking** | 팔로잉 기반 진도 순위 | `GET /api/ranking` |
| **setting** | 학습 언어, 테마, 볼륨 설정 | `GET /api/settings` `PUT /api/settings` |

> 상세 API 명세는 [docs/api-spec.md](docs/api-spec.md) 참고

<br>

## Screens

| Screen | Description | Key Feature |
| :--- | :--- | :--- |
| **Dashboard** | Day 1~60 학습 진도 | Grid 형태, 완료/진행중/잠김 상태 표시 |
| **Study** | 플래시카드 단어 학습 | 스와이프로 외움/미흡 분류 |
| **Ranking** | 팔로잉 유저 진도 경쟁 | 진도율 기반 순위, 내 순위 강조 |
| **My Page** | 프로필, 학습 통계 | 외운 단어 수, 팔로워/팔로잉 |
| **Follow** | 팔로워/팔로잉 관리 | 유저 검색, 팔로우/언팔로우 |
| **Edit Profile** | 프로필 편집 | 이름, 소개, 아바타 변경 |
| **Settings** | 학습 설정 | 언어, 테마, 볼륨, 학습 초기화 |

<br>

## Getting Started

```bash
# Frontend
cd frontend && npm install && npm run dev    # http://localhost:5173

# Backend
cd backend && ./gradlew bootRun              # http://localhost:8080

# Docker
docker-compose up
```

<br>

## Development Progress

| Step | Task | Status |
| :---: | :--- | :---: |
| 1 | 화면 디자인 (Vue 컴포넌트) | Done |
| 2 | API 명세 정의 (docs/api-spec.md) | Done |
| 3 | 프론트 API 모듈 (src/api/) | Done |
| 4 | DB 설계 | - |
| 5 | Backend 구현 (Entity ~ Controller) | - |
| 6 | 프론트 ↔ 백엔드 연동 | - |
