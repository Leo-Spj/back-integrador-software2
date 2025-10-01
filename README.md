# Para Ingresar a Swagger
- http://localhost:8080/swagger-ui.html

# Configuraciones en el properties
- spring.flyway.enabled=true
- spring.flyway.locations=classpath:db/migration

# Asegúrate de tener la conexión a la base de datos configurada
- spring.datasource.url=jdbc:mysql://localhost:3306/inkatravel_db
- spring.datasource.username={ Agregar Credenciales para la DB }
- spring.datasource.password={ Agregar Credenciales para la DB }
- spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

- spring.security.user.name={ Agregar tu Usuario para ingresar a Swagger }
- spring.security.user.password={ Agregar tu Clave para ingresar a Swagger }

# JWT Config
- jwt.secret=AgregarUnaClaveConMasDe32Chars!!
- jwt.expiration=3600000
