FROM node:latest as build
WORKDIR /frontend
COPY . .
RUN npm install
RUN npm run build
CMD mvn spring-boot:run