<div align="right">

[![한국어](https://img.shields.io/badge/한국어-0075C8?style=for-the-badge&logo=south-korea&logoColor=white)](README.md) [![日本語](https://img.shields.io/badge/日本語-BC002D?style=for-the-badge&logo=japan&logoColor=white)](README.ja.md)

</div>

# 📚 Voca1800 - Gamified Vocabulary Learning Platform

**Voca1800**は、1日30単語ずつ、計60日間で1,800個の必須単語を完璧に暗記できるように設計されたWebアプリケーションです。
単なる暗記にとどまらず、**友達との進捗競争(Ranking)**や**ゲーミフィケーション(Gamification)**要素を取り入れ、学習意欲を最大化しました。

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

Voca1800は、**学習(Learning)**、**パーソナライズ(Personal)**、**ソーシャル(Social)**の順序で段階的な開発を進めています。

| 優先順位 | カテゴリー | 画面名 | コア機能 (What) | UI/UX特徴 (How) |
| :---: | :---: | :--- | :--- | :--- |
| 🥇 **優先度1** | **メイン** | **ダッシュボード**<br>(Dashboard) | • 学習進捗の確認<br>• 学習するDayを選択 (1~60日)<br>• クイズ/ランキングへのクイック移動 | • **Grid System:** Day 1~60 ボタン配置<br>• **Status Color:** 🟢完了 / 🔵進行中 / 🔒ロック |
| 🥇 **優先度1** | **学習** | **単語学習**<br>(Study Mode) | • 単語暗記 (フラッシュカード)<br>• Dayごとの学習完了処理 | • **Flashcard:** 表面(英語) ↔ 裏面(韓国語/日本語)<br>• **Flip Motion:** クリックで回転するアニメーション |
| 🥈 **優先度2** | **アカウント** | **マイページ**<br>(My Page) | • 自分の情報照会<br>• フォロワー/フォロイング状況 | • **Profile:** プロフィール写真、レベル、ニックネーム表示<br>• **Toggle:** ダークモード対応 |
| 🥈 **優先度2** | **設定** | **プロフィール修正**<br>(Settings) | • 個人情報の修正<br>• パスワード変更<br>• **会員退会** | • **Security:** 現在のパスワード確認後に変更<br>• **Danger Zone:** 退会時に警告ポップアップを提供 |
| 🥉 **優先度3** | **ソーシャル** | **ランキング**<br>(Ranking) | • 友達との進捗競争<br>• 友達追加画面へ移動 | • **Progress Bar:** Day進捗率の可視化<br>• **Ranking:** 自分の順位を強調表示 (Sticky) |
| 🥉 **優先度3** | **ソーシャル** | **友達検索**<br>(Search) | • ライバル(友達)を探す<br>• フォロー/アンフォロー | • **Search:** ニックネーム/メール検索<br>• **Follow:** 即座にランキングリストへ追加 |
| 🥉 **優先度3** | **ソーシャル** | **友達リスト**<br>(Friend List) | • フォロワー/フォロイング管理<br>• 友達関係の整理 | • **Tab View:** フォロワー/フォロイングのタブ区分<br>• **Action:** アンフォローおよび削除ボタン |
| 🎮 **Bonus** | **お楽しみ** | **ランダムクイズ**<br>(Speed Quiz) | • 復習用4択クイズ<br>• スコア獲得ゲーム | • **Timer:** 制限時間(タイマー)機能<br>• **Feedback:** 正解/不正解を即座に確認 |

<br>
