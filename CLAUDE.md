# Voca1800 - CLAUDE.md

## 프로젝트 개요

하루 30단어씩 60일 동안 1,800개 단어를 암기하는 게이미피케이션 기반 어휘 학습 웹앱.
친구와의 진도 경쟁(랭킹)과 퀴즈 기능을 포함한다.

## 기술 스택

| 영역 | 기술 |
|------|------|
| Frontend | Vue 3, Vite, Vuetify 4, Pinia, Vue Router 5, Axios |
| Backend | Spring Boot 4, Java 21, Spring Data JPA, Lombok |
| Database | PostgreSQL |
| Infra | Docker, Google Cloud |

## 아키텍처

DDD + Clean Architecture + Hexagonal Architecture 조합.

- **도메인(domain)**: Entity, Value Object, Domain Service — 순수 Java, 프레임워크 의존 없음
- **애플리케이션(application)**: Use Case 단위 Service + Port(in/out) 인터페이스
- **어댑터(adapter/in)**: REST Controller, Request/Response DTO
- **어댑터(adapter/out)**: JPA Repository 구현체, 외부 시스템 연동

의존성 방향: `adapter → application → domain` (반대 방향 의존 금지)

## 프로젝트 구조

```
voca1800/
├── frontend/                          # Vue 3 앱
│   └── src/
│       ├── api/                       # 도메인별 API 모듈
│       │   ├── axios.js               # Axios 인스턴스 (baseURL: /api)
│       │   ├── authApi.js
│       │   ├── userApi.js
│       │   ├── followApi.js
│       │   ├── wordApi.js
│       │   ├── progressApi.js
│       │   ├── rankingApi.js
│       │   └── settingApi.js
│       ├── component/                 # 재사용 컴포넌트
│       ├── router/                    # Vue Router 설정
│       ├── views/                     # 페이지 단위 뷰
│       └── main.js
│
├── backend/                           # Spring Boot 앱
│   └── src/main/java/com/voca1800/
│       ├── {domain}/                  # 도메인별 패키지
│       │   ├── domain/                # Entity, Value Object, Domain Service
│       │   ├── application/
│       │   │   ├── port/
│       │   │   │   ├── in/            # Use Case 인터페이스
│       │   │   │   └── out/           # Repository 포트 인터페이스
│       │   │   └── service/           # Use Case 구현체
│       │   └── adapter/
│       │       ├── in/web/            # REST Controller + DTO
│       │       └── out/persistence/   # JPA Repository 구현체
│       └── global/                    # 공통 설정, 예외 처리, 유틸
│
├── docs/
│   └── api-spec.md                    # API 명세서 (Request/Response 정의)
│
└── docker-compose.yml
```

## 도메인 구성

| 도메인 | 역할 | 관련 화면 |
|--------|------|-----------|
| `auth` | 로그인, 회원가입, 토큰 관리 | (미구현) |
| `user` | 프로필 조회, 수정, 탈퇴 | MyPage, EditProfile |
| `follow` | 팔로우, 언팔로우, 팔로워/팔로잉 목록, 유저 검색 | FollowPage |
| `word` | 단어 마스터 데이터 (영어, 뜻, 발음) | StudyMain |
| `progress` | Day별 학습 진도, 외움/미흡 처리, 학습 통계 | DayBoard, StudyMain, MyPage |
| `ranking` | 팔로잉 기반 진도 순위 조회 | RankingBoard |
| `setting` | 학습 언어, 테마, 볼륨 등 사용자 설정 | Settings |

추후 추가 예정: `quiz` (랜덤 퀴즈, 점수)

### 도메인별 헥사고날 구조 예시 (user)

```
user/
├── domain/
│   └── User.java                        # Aggregate Root
├── application/
│   ├── port/
│   │   ├── in/
│   │   │   ├── GetUserUseCase.java      # 조회 인터페이스
│   │   │   └── UpdateUserUseCase.java   # 수정 인터페이스
│   │   └── out/
│   │       └── UserRepositoryPort.java  # Repository 포트
│   └── service/
│       ├── GetUserService.java          # 조회 구현체
│       └── UpdateUserService.java       # 수정 구현체
└── adapter/
    ├── in/web/
    │   ├── UserController.java
    │   └── dto/
    │       ├── UpdateUserRequest.java
    │       └── UserResponse.java
    └── out/persistence/
        ├── UserPersistenceAdapter.java  # 포트 구현체
        └── UserJpaRepository.java
```

## 개발 서버 실행

```bash
# 프론트엔드
cd frontend && npm run dev      # http://localhost:5173

# 백엔드
cd backend && ./gradlew bootRun # http://localhost:8080

# 전체 (Docker)
docker-compose up
```

## 코딩 컨벤션

### Frontend
- Vue 파일: `PascalCase.vue`
- `<script setup>` 사용 (Composition API)
- 상태관리: Pinia store
- UI: Vuetify 4 컴포넌트 (`v-btn`, `v-card` 등)
- 백엔드 통신: `src/api/` 모듈에서 Axios로 REST API 호출

### Backend
- 패키지: `com.voca1800.{domain}.{layer}` 구조
- Use Case 단위로 Service 클래스 분리 (CreateXxxService, UpdateXxxService 등)
- Entity를 adapter 밖으로 노출 금지 (항상 DTO 변환)
- Lombok 사용 (`@Getter`, `@Builder`, `@RequiredArgsConstructor`)
- REST API 엔드포인트: `/api/{domain}/...`

## 통신 방식

- 프론트 ↔ 백엔드: REST API (JSON)
- API 기본 prefix: `/api`

## 개발 순서

| 순서 | 작업 | 상태 |
|:---:|------|:---:|
| 1 | 화면 디자인 (Vue 컴포넌트) | 완료 |
| 2 | 화면별 API 명세 (docs/api-spec.md) | 완료 |
| 3 | 프론트 API 파일 (src/api/) | 완료 |
| 4 | DB 설계 (테이블, 컬럼, 관계) | - |
| 5 | Entity (JPA 엔티티) | - |
| 6 | Repository (Port + JPA 구현체) | - |
| 7 | UseCase / Service (비즈니스 로직) | - |
| 8 | Controller + DTO (REST 엔드포인트) | - |
| 9 | 프론트 연동 (하드코딩 → API 호출) | - |

### 기능 우선순위

1. 대시보드 + 단어 학습 (플래시카드)
2. 마이페이지 + 프로필 수정
3. 랭킹 + 친구 검색/목록
4. (보너스) 랜덤 퀴즈
