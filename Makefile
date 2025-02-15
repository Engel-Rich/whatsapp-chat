start:
	docker-compose up --build -d
start -d:
	docker-compose up --build -d
stop:
	docker-compose down
restart:
	docker-compose down && docker-compose up --build -d
reset:
	docker system prune -af
