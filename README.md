# **bbecommerce:Backend Java para Ecommerce**

## Ejemplo básico con Autenticacion, Roles, Configuración de CORS para el backend de una tienda desarrollada en Spring Boot

### Puntos a tener en cuenta:

1. Como Gestor de Base de Datos se escogió Postgresql. Para desplegarlo localmente ir al application.properties y modificar el usuario y la contraseña
2. Una vez ya compilado el proyecto que se generaron las tablas en la BD y el servidor Tomcat corre en el puerto 8080, ir a la tabla Role en la base de datos y agregar los valores: ROLE_ADMIN, ROLE_EDITOR,ROLE_USER con sus respectivos id.
3. Luego ejecutar la ruta (/signup) para registrar un usuario. Cuando este ya se encuentre registrado su rol será USER por default. Acto seguido vaya a la tabla role_user en el PgAdmin y cambie el id que corresponde al ROLE_USER por el id que corresponde al ROLE_ADMIN. Ya tiene su user Admin.
4. Registre otro ususario para que tenga además un user con el rol ROLE_USER.
5. Tenga en cuenta los headers en las solicitudes http, revise que el content-type esté configurado a application/json.
6. Para la autenticación el token devuelto es de tipo Bearer Token.
