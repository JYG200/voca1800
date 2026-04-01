# Voca1800 API 명세서

## 기본 정보

- Base URL: `/api`
- Content-Type: `application/json`
- 인증: (추후 정의)

---

## 1. Auth (인증)

### POST /api/auth/signup — 회원가입

**Request**
```json
{
  "email": "user@example.com",
  "password": "password123",
  "name": "Yonggyu"
}
```

**Response** `201 Created`
```json
{
  "userId": 1,
  "email": "user@example.com",
  "name": "Yonggyu"
}
```

---

### POST /api/auth/login — 로그인

**Request**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response** `200 OK`
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6...",
  "userId": 1,
  "name": "Yonggyu"
}
```

---

### POST /api/auth/logout — 로그아웃

**Response** `200 OK`

---

## 2. User (회원)

### GET /api/users/me — 내 프로필 조회

> 화면: MyPage

**Response** `200 OK`
```json
{
  "userId": 1,
  "name": "Yonggyu",
  "email": "user@example.com",
  "bio": "매일 조금씩, 꾸준히",
  "avatarBg": "linear-gradient(135deg, #7c3aed, #2563eb)",
  "avatarUrl": null,
  "followers": 24,
  "following": 12,
  "memorizedWords": 364,
  "totalWords": 1800
}
```

---

### PUT /api/users/me — 프로필 수정

> 화면: EditProfile

**Request**
```json
{
  "name": "Yonggyu",
  "bio": "매일 조금씩, 꾸준히",
  "avatarBg": "linear-gradient(135deg, #7c3aed, #2563eb)"
}
```

**Response** `200 OK`
```json
{
  "userId": 1,
  "name": "Yonggyu",
  "bio": "매일 조금씩, 꾸준히",
  "avatarBg": "linear-gradient(135deg, #7c3aed, #2563eb)",
  "avatarUrl": null
}
```

---

### DELETE /api/users/me — 회원 탈퇴

**Response** `204 No Content`

---

## 3. Follow (팔로우)

### GET /api/follows/following — 팔로잉 목록

> 화면: FollowPage (팔로잉 탭)

**Response** `200 OK`
```json
[
  {
    "userId": 2,
    "name": "Mina",
    "bio": "영어 공부 중",
    "avatarBg": "linear-gradient(135deg, #ec4899, #f97316)",
    "avatarUrl": null
  },
  {
    "userId": 3,
    "name": "Ken",
    "bio": "매일 단어 10개!",
    "avatarBg": "linear-gradient(135deg, #10b981, #22c55e)",
    "avatarUrl": null
  }
]
```

---

### GET /api/follows/followers — 팔로워 목록

> 화면: FollowPage (팔로워 탭)

**Response** `200 OK`
```json
[
  {
    "userId": 4,
    "name": "Sora",
    "bio": "토익 목표 900점",
    "avatarBg": "linear-gradient(135deg, #0ea5e9, #6366f1)",
    "avatarUrl": null,
    "isFollowing": false
  }
]
```

---

### POST /api/follows/{userId} — 팔로우

> 화면: FollowPage

**Response** `201 Created`

---

### DELETE /api/follows/{userId} — 언팔로우

> 화면: FollowPage

**Response** `204 No Content`

---

### GET /api/follows/search?q={query} — 유저 검색

> 화면: FollowPage (검색 모드)

**Request** Query Parameter
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| q | string | 검색어 (이름) |

**Response** `200 OK`
```json
[
  {
    "userId": 5,
    "name": "Jake",
    "bio": "연속 30일",
    "avatarBg": "linear-gradient(135deg, #f59e0b, #ef4444)",
    "avatarUrl": null,
    "isFollowing": true
  }
]
```

---

## 4. Word (단어)

### GET /api/words/days/{dayNumber} — Day별 단어 목록

> 화면: StudyMain

**Response** `200 OK`
```json
{
  "dayNumber": 1,
  "words": [
    {
      "wordId": 1,
      "word": "Apple",
      "meaning": "사과",
      "phonetic": "[æpl]"
    },
    {
      "wordId": 2,
      "word": "Banana",
      "meaning": "바나나",
      "phonetic": "[bəˈnænə]"
    }
  ]
}
```

---

## 5. Progress (학습 진도)

### GET /api/progress/days — Day별 진도 목록

> 화면: DayBoard

**Response** `200 OK`
```json
{
  "currentDay": 2,
  "days": [
    { "dayNumber": 1, "status": "COMPLETED" },
    { "dayNumber": 2, "status": "IN_PROGRESS" },
    { "dayNumber": 3, "status": "LOCKED" }
  ]
}
```

| status | 설명 |
|--------|------|
| COMPLETED | 학습 완료 |
| IN_PROGRESS | 현재 학습 가능 |
| LOCKED | 잠김 (이전 Day 미완료) |

---

### POST /api/progress/days/{dayNumber}/complete — Day 학습 완료

> 화면: StudyMain (모든 카드 소진 후)

**Response** `200 OK`
```json
{
  "dayNumber": 1,
  "status": "COMPLETED",
  "nextDay": 2
}
```

---

### PUT /api/progress/days/{dayNumber}/words/{wordId} — 단어 외움/미흡 처리

> 화면: StudyMain (카드 스와이프)

**Request**
```json
{
  "result": "MEMORIZED"
}
```

| result | 설명 |
|--------|------|
| MEMORIZED | 외움 (왼쪽 스와이프) |
| WEAK | 미흡 (오른쪽 스와이프) |

**Response** `200 OK`

---

### GET /api/progress/stats — 학습 통계

> 화면: MyPage (학습 통계 섹션)

**Response** `200 OK`
```json
{
  "memorizedWords": 364,
  "totalWords": 1800,
  "completedDays": 12,
  "totalDays": 60
}
```

---

## 6. Ranking (랭킹)

### GET /api/ranking — 팔로잉 기반 랭킹

> 화면: RankingBoard

**Response** `200 OK`
```json
{
  "me": {
    "userId": 1,
    "name": "Yonggyu",
    "progress": 72,
    "achievedAt": "2026-03-04T09:10:00+09:00",
    "avatarBg": "linear-gradient(135deg, #7c3aed, #2563eb)",
    "avatarUrl": null
  },
  "rankings": [
    {
      "rank": 1,
      "userId": 6,
      "name": "Jake",
      "progress": 91,
      "achievedAt": "2026-03-04T07:15:00+09:00",
      "avatarBg": "linear-gradient(135deg, #f59e0b, #ef4444)",
      "avatarUrl": null
    },
    {
      "rank": 2,
      "userId": 7,
      "name": "Hana",
      "progress": 88,
      "achievedAt": "2026-03-04T07:50:00+09:00",
      "avatarBg": "linear-gradient(135deg, #a855f7, #ec4899)",
      "avatarUrl": null
    }
  ]
}
```

---

## 7. Setting (설정)

### GET /api/settings — 설정 조회

> 화면: SettingsContent

**Response** `200 OK`
```json
{
  "language": "한국어",
  "theme": "light",
  "volume": 70
}
```

---

### PUT /api/settings — 설정 수정

> 화면: SettingsContent

**Request**
```json
{
  "language": "日本語",
  "theme": "dark",
  "volume": 50
}
```

**Response** `200 OK`
```json
{
  "language": "日本語",
  "theme": "dark",
  "volume": 50
}
```

---

### POST /api/settings/reset-progress — 학습 초기화

> 화면: SettingsContent (초기화 버튼)

**Response** `200 OK`

---

## 에러 응답 공통 형식

```json
{
  "status": 400,
  "code": "INVALID_REQUEST",
  "message": "이메일 형식이 올바르지 않습니다."
}
```

| HTTP Status | 설명 |
|-------------|------|
| 400 | 잘못된 요청 |
| 401 | 인증 필요 |
| 403 | 권한 없음 |
| 404 | 리소스 없음 |
| 409 | 중복 (이메일 등) |
| 500 | 서버 에러 |
