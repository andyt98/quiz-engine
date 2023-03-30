docker run -d \
  -p 5432:5432 \
  --name postgresqldb \
  -e POSTGRES_USER=andy \
  -e POSTGRES_PASSWORD=password \
  -e POSTGRES_DB=quizdb \
  -v data:/var/lib/postgresql/data \
  postgres
