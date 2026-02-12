<div align="right">

[![한국어](https://img.shields.io/badge/한국어-0075C8?style=for-the-badge&logo=south-korea&logoColor=white)](README.md) [![日本語](https://img.shields.io/badge/日本語-BC002D?style=for-the-badge&logo=japan&logoColor=white)](README.ja.md)

</div>

# 📚 Voca1800 - Gamified Vocabulary Learning Platform

**Voca1800**은 하루 30단어씩 총 60일 동안 1,800개의 필수 단어를 완벽하게 암기하도록 돕는 웹 애플리케이션입니다.
단순한 암기를 넘어, **친구와의 진도 경쟁(Ranking)**과 **게이미피케이션(Gamification)** 요소를 도입하여 학습 동기를 극대화했습니다.

<br>

## 🛠️ Tech Stack

| Category | Technology |
| :--- | :--- |
| **Frontend** | ![Vue.js](https://img.shields.io/badge/Vue.js-35495E?style=flat&logo=vue.js&logoColor=4FC08D) ![Vite](https://img.shields.io/badge/Vite-646CFF?style=flat&logo=vite&logoColor=white) ![Vuetify](https://img.shields.io/badge/Vuetify-1867C0?style=flat&logo=vuetify&logoColor=white) |
| **Backend** | ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white) ![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white) ![JPA](https://img.shields.io/badge/Spring_Data_JPA-gray?style=flat) |
| **Database** | ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=flat&logo=postgresql&logoColor=white) |
| **Infrastructure** | ![Google Cloud](https://img.shields.io/badge/Google_Cloud-4285F4?style=flat&logo=google-cloud&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat&logo=docker&logoColor=white) |
| **Tools** | ![pgAdmin](https://img.shields.io/badge/pgAdmin_4-336791?style=flat&logo=postgresql&logoColor=white) ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=flat&logo=intellij-idea&logoColor=white) ![VS Code](https://img.shields.io/badge/Visual_Studio_Code-007ACC?style=flat&logo=visual-studio-code&logoColor=white) |
<br>

## 🛤️ Development Roadmap

Voca1800은 **학습(Learning)**, **개인화(Personal)**, **소셜(Social)** 순서로 단계별 개발을 진행합니다.

| 우선순위 | 구분 | 화면명 | 핵심 기능 (What) | UI/UX 특징 (How) |
| :---: | :---: | :--- | :--- | :--- |
| 🥇 **1순위** | **메인** | **대시보드**<br>(Dashboard) | • 학습 진도 확인<br>• 학습할 Day 선택 (1~60일)<br>• 퀴즈/랭킹 빠른 이동 | • **Grid System:** Day 1~60 버튼 배치<br>• **Status Color:** 🟢완료 / 🔵진행중 / 🔒잠김 |
| 🥇 **1순위** | **학습** | **단어 학습**<br>(Study Mode) | • 단어 암기 (플래시카드)<br>• Day별 학습 완료 처리 | • **Flashcard:** 앞면(영어) ↔ 뒷면(한글/일어)<br>• **Flip Motion:** 클릭 시 뒤집히는 애니메이션 |
| 🥈 **2순위** | **계정** | **마이 페이지**<br>(My Page) | • 내 정보 조회<br>• 팔로워/팔로잉 현황 | • **Profile:** 프로필 사진, 레벨, 닉네임 표시<br>• **Toggle:** 다크모드 지원 |
| 🥈 **2순위** | **설정** | **프로필 수정**<br>(Settings) | • 개인정보 수정<br>• 비밀번호 변경<br>• **회원 탈퇴** | • **Security:** 현재 비밀번호 확인 후 변경<br>• **Danger Zone:** 탈퇴 시 경고 팝업 제공 |
| 🥉 **3순위** | **소셜** | **랭킹**<br>(Ranking) | • 친구들과 진도 경쟁<br>• 친구 추가 화면 이동 | • **Progress Bar:** Day 진도율 시각화<br>• **Ranking:** 내 순위 강조 표시 (Sticky) |
| 🥉 **3순위** | **소셜** | **친구 검색**<br>(Search) | • 경쟁자(친구) 찾기<br>• 팔로우/언팔로우 | • **Search:** 닉네임/이메일 검색<br>• **Follow:** 즉시 랭킹 리스트에 추가 |
| 🥉 **3순위** | **소셜** | **친구 목록**<br>(Friend List) | • 팔로워/팔로잉 관리<br>• 친구 관계 정리 | • **Tab View:** 팔로워/팔로잉 탭 구분<br>• **Action:** 언팔로우 및 삭제 버튼 |
| 🎮 **Bonus** | **재미** | **랜덤 퀴즈**<br>(Speed Quiz) | • 복습용 4지 선다<br>• 점수 획득 게임 | • **Timer:** 제한시간(타이머) 기능<br>• **Feedback:** 정답/오답 즉시 확인 |

<br>
