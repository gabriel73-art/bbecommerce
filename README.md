# bbecommerce
Titulo: Backend Java para Ecommerce


Ejemplo básico con Autenticacion, Roles, Configuración de CORS para el backend de una tienda desarrollada en Spring Boot

Puntos a tener en cuenta:

-Como Gestor de Base de Datos se escogió Postgresql. Para desplegarlo localmente ir al application.properties y modificar el usuario y la contraseña
-Una vez ya corriendo el servidor en el puerto 8080 ir a la tabla Role en la base de datos y agregar los valores: ROLE_ADMIN, ROLE_EDITOR,ROLE_USER con sus respectivos id.
-Luego ejecutar la ruta que lleva para registrar un usuario. Cuando este ya se encuentre registradosu role va a se USER. Acto seguido vaya a la tabla role_user en el PgAdmin y cambie el id que corresponde al ROLE_USER por el id que corresponde al ROLE_ADMIN. Ya tiene su user Admin.
-Registre otro ususario para que tenga además un user normal.
-Tenga en cuenta los headers en la solicitud http, revise que el content-type esté configurado a application/json.
-Para la autenticación el token devuelto es de tipo Bearer Token.
