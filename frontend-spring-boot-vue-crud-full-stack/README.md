app	
spring-boot-fullstack-crud-full-stack-with-maven
configured with the following ports

backend server web port	8093
\spring-boot-fullstack-crud-full-stack-with-maven\src\main\resources\application.properties	
server.port=8093

front cli server port	8089
\spring-boot-fullstack-crud-full-stack-with-maven\frontend-spring-boot-vue-crud-full-stack\package.json	
"serve": "vue-cli-service serve --port 8089"	

**important**	backend server and front end server MUST have different PORTS !!!!	

change to map to (back end) server port	\spring-boot-fullstack-crud-full-stack-with-maven\frontend-spring-boot-vue-crud-full-stack\src\service\CourseDataService.js	
COURSE_API_URL	http://localhost:8093

access app at	
http://localhost:8090/	
port 8090 is REassigned by client server running on port 8089

