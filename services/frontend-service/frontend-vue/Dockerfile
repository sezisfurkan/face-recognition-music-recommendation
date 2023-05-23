FROM nginx:1.21.3-alpine

COPY dist/ /usr/share/nginx/html

EXPOSE 5173

CMD ["nginx", "-g", "daemon off;"]
