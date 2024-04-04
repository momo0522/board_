# 빌드 단계
FROM node:18 as build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . ./
RUN npm run build

# 실행 단계
FROM nginx:latest
COPY --from=build /app/build /usr/share/nginx/html
# 기본 Nginx 설정 파일을 대체하기 위해 추가
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]