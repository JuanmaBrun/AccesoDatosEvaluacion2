## README del Proyecto Red Social
# Introducción
Este proyecto consiste en una aplicación de red social desarrollada con Java utilizando el framework Spring.
La aplicación permite a los usuarios registrarse, iniciar sesión, crear publicaciones, seguir a otros usuarios, entre otras funcionalidades típicas de una red social.
# Estructura del Proyecto
El proyecto está estructurado de manera apropiada, siguiendo las convenciones de Spring:
css
Copy code
proyecto-red-social/
│
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/
│ │ │ └── vedruna/
│ │ │ └── twitterapi/
│ │ │ ├── config/
│ │ │ ├── controllers/
│ │ │ ├── dto/
│ │ │ ├── jwt/
│ │ │ ├── persistence/
│ │ │ │ ├── model/
│ │ │ │ └── repository/
│ │ │ ├── security/
│ │ │ └── services/
│ │ └── resources/
└── pom.xml


# Dependencias
El proyecto utiliza las siguientes dependencias principales:
**Spring Boot:** Spring Boot es un framework de desarrollo de aplicaciones Java que facilita la creación de aplicaciones autónomas y listas para producción. Proporciona una configuración automática y convenciones de estructura de proyecto que simplifican el desarrollo.
**Spring Data JPA:** Spring Data JPA es parte del ecosistema de Spring que proporciona una capa de abstracción sobre JPA (Java Persistence API). Permite a los desarrolladores interactuar con la base de datos utilizando interfaces y métodos estándar, sin la necesidad de escribir consultas SQL manualmente.
**Spring Security:** Spring Security es un framework de seguridad para aplicaciones Java que proporciona autenticación y autorización a nivel de aplicación. Permite proteger los endpoints de la API, controlar el acceso a recursos protegidos y gestionar la autenticación de usuarios de manera segura.
**MySQL Connector:** MySQL Connector es el conector JDBC (Java Database Connectivity) para MySQL. Permite a las aplicaciones Java conectarse y comunicarse con bases de datos MySQL.
**Bcrypt:** Bcrypt es una biblioteca de cifrado de contraseñas que utiliza un algoritmo de cifrado de hash robusto y seguro. Se utiliza comúnmente para almacenar contraseñas de manera segura en aplicaciones web, evitando la exposición de contraseñas en texto plano en la base de datos.
**JSON Web Token (JWT):** JWT es un estándar abierto (RFC 7519) que define un formato compacto y autónomo para transmitir información de manera segura entre partes como un objeto JSON. En el contexto de la seguridad de aplicaciones web, JWT se utiliza comúnmente para la autenticación y autorización de usuarios, proporcionando tokens firmados digitalmente que pueden ser verificados y confiables. En este proyecto, JWT se utiliza en conjunción con Spring Security para manejar la autenticación y la generación de tokens de acceso.


# Configuración
La configuración de la aplicación se encuentra en el archivo application.properties y contiene los siguientes ajustes:
URL de la Fuente de Datos:
spring.datasource.url=jdbc:mysql://localhost:3306/twitterapi_2ev
Esta propiedad especifica la URL de conexión a la base de datos MySQL. En este caso, la aplicación se conecta a una base de datos llamada twitterapi_2ev en el servidor local MySQL que se ejecuta en el puerto 3306.

# Nombre de Usuario de la Fuente de Datos:
spring.datasource.username=root
Esta propiedad especifica el nombre de usuario utilizado para autenticarse en la base de datos MySQL. En este caso, se utiliza el nombre de usuario root.

# Contraseña de la Fuente de Datos:
spring.datasource.password=root
Esta propiedad especifica la contraseña utilizada para autenticarse en la base de datos MySQL. En este caso, se utiliza la contraseña root.
# Controlador de la Fuente de Datos:
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

Esta propiedad especifica el nombre de la clase del controlador JDBC utilizado para la conexión a la base de datos MySQL. En este caso, se utiliza el controlador com.mysql.cj.jdbc.Driver para MySQL.

# Dialecto de Hibernate:
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
Esta propiedad especifica el dialecto de Hibernate utilizado para generar consultas SQL compatibles con el motor de base de datos MySQL.

# Actualización Automática del Esquema de Hibernate:
spring.jpa.hibernate.ddl-auto=update

Esta propiedad especifica el comportamiento de Hibernate para actualizar automáticamente el esquema de la base de datos. En este caso, se configura para que Hibernate actualice automáticamente el esquema de la base de datos según los cambios en las entidades Java.
Estas configuraciones son esenciales para establecer la conexión con la base de datos, configurar el comportamiento de Hibernate y garantizar el correcto funcionamiento de la aplicación en relación con la persistencia de datos. Es importante mantener estas configuraciones actualizadas y seguras, especialmente en entornos de producción.
# Endpoints de la API
La API REST expone los siguientes endpoints:
/registro: Permite a los usuarios registrarse en la plataforma (público).
/login: Permite a los usuarios iniciar sesión para obtener un token JWT (público).
/editar-descripcion: Permite a los usuarios autenticados editar su descripción (privado).
/buscar-usuario/{username}: Permite buscar usuarios por su nombre de usuario (público).
/usuarios/{id}/siguiendo: Obtiene todos los usuarios seguidos por un usuario específico (privado).
/usuarios/{id}/seguidores: Obtiene todos los usuarios que siguen a un usuario específico (privado).
/publicaciones: Obtiene todas las publicaciones (privado).
/publicaciones/{idUsuario}: Obtiene todas las publicaciones de un usuario específico (público).
/publicaciones/siguiendo: Obtiene todas las publicaciones de los usuarios seguidos por un usuario (privado).
/insertar-publicacion: Permite a los usuarios autenticados insertar una nueva publicación (privado).
/editar-publicacion/{id}: Permite a los usuarios autenticados editar una publicación existente (privado).
/borrar-publicacion/{id}: Permite a los usuarios autenticados borrar una publicación existente (privado).


# Implementación de la Seguridad
En este proyecto, la seguridad se implementa utilizando Spring Security en conjunto con JSON Web Tokens (JWT). Este enfoque proporciona una capa de seguridad robusta para proteger los endpoints de la API y garantizar que solo los usuarios autenticados puedan acceder a los recursos protegidos.
# Autenticación
Cuando un usuario realiza una solicitud para acceder a un recurso protegido, el proceso de autenticación se desencadena. El usuario debe proporcionar sus credenciales de inicio de sesión (como nombre de usuario y contraseña) en la solicitud. Estas credenciales son enviadas al servidor y verificadas con la base de datos para validar la identidad del usuario.
Una vez que las credenciales son validadas con éxito, se genera un token de acceso JWT. Este token contiene información sobre el usuario autenticado y se firma digitalmente utilizando una clave secreta. El token se devuelve al cliente en la respuesta HTTP.
JSON Web Tokens (JWT)
Un token JWT es una cadena codificada que consta de tres partes separadas por puntos: el encabezado, el cuerpo (payload) y la firma. El encabezado especifica el tipo de token y el algoritmo de firma utilizado. El cuerpo contiene los datos del usuario (como ID de usuario, roles, etc.). La firma se utiliza para verificar la integridad del token y garantizar que no haya sido alterado.
Una vez que el cliente recibe el token JWT, este token se incluye en todas las solicitudes posteriores como parte de la cabecera de autorización (Authorization: Bearer <token>). El servidor verifica la firma del token para validar su autenticidad y extrae los datos del usuario del cuerpo del token para autorizar la solicitud.
# Autorización
Spring Security proporciona una capa de autorización para controlar el acceso a los recursos protegidos. Se pueden definir reglas de autorización basadas en roles de usuario, permitiendo o denegando el acceso a ciertos recursos en función de los roles del usuario autenticado.
En este proyecto, se pueden definir diferentes roles de usuario (como ROLE_USER y ROLE_ADMIN) y asignar estos roles a los usuarios durante el proceso de registro o administración. Las reglas de autorización se configuran en los controladores o en la configuración de Spring Security para especificar qué roles tienen acceso a qué endpoints de la API.
Consideraciones Finales
Este proyecto cumple con los requisitos establecidos en el enunciado, proporcionando una estructura adecuada y funcionalidades completas para una aplicación de red social. Se sugiere seguir mejorando y optimizando el código, así como considerar posibles extensiones y nuevas funcionalidades en el futuro.
Documentación Adicional
Para obtener más detalles sobre la implementación de cada funcionalidad, se recomienda revisar el código fuente y los comentarios en cada archivo.
