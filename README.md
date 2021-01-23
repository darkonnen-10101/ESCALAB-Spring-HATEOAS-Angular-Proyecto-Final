# Proyecto final ESCALAB - Spring Boot HATEOAS

Excelente curso de especialización en Spring Boot por parte de Josse Niño & Escalab Academy

Agregadas todas las dependencias de seguridad y autenticación necesarias para correr la app.

[Link de la carpeta con screenshots](https://github.com/Adel-Cabrera/ESCALAB-Spring-HATEOAS-Angular-Proyecto-Final/tree/main/Screenshots) de la database en postgresql (tablas oauth token) y pruebas de stress sencillas para endpoints users/{id} y /posts/hateoas.

PostController con endpoint /posts/hateoas obedece a la tercera forma según el Modelo de madurez de Richardson (por temas de tiempo, solo hice backend, sin embargo no es difícil implementar lógica en Angular, crear componentes y vistas, según lo visto en clases y en el proyecto de ejemplo).

[Link de archivo json](https://github.com/Adel-Cabrera/ESCALAB-Spring-HATEOAS-Angular-Proyecto-Final/tree/main/Example) para incluir un ejemplo de respuesta al consultar el endpoint.

Entidades documentadas -> Post & AppUser.

DTO creado -> PostDTO. Lo devuelve el endpoint /posts/hateoas en PostController (incluye user y tags, ambos con withSelfRel()) 

Todos los controllers documentados.

[Click en este link para ir al ERD del proyecto](https://drive.google.com/file/d/11hL1tf5_2xVNIBKu1C41obfKX95nigiH/view)

Muchas gracias por todo, profe!
