# Node.js 20 버전 사용
FROM node:20-alpine

WORKDIR /app

# 패키지 정보 복사
COPY package*.json ./

# 라이브러리 설치
RUN npm install

# 소스 코드 복사
COPY . .

# 개발 서버 실행 (외부 접속 허용 --host)
CMD ["npm", "run", "dev", "--", "--host"]