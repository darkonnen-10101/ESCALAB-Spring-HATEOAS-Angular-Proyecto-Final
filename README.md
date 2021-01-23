# Proyecto final ESCALAB - Spring Boot HATEOAS

Excelente curso de especialización en Spring Boot por parte de Josse Niño & Escalab Academy

Agregadas todas las dependencias de seguridad y autenticación necesarias para correr la app.

Screenshot de la database en postgresql (tablas oauth token) y pruebas de stress sencillas.

PostController con endpoint /posts/hateoas obedece a la tercera forma según el Modelo de madurez de Richardson (por temas de tiempo, solo hice backend, sin embargo no es difícil implementar lógica en Angular, crear componentes y vistas, según lo visto en clases y en el proyecto de ejemplo).

Entidad documentada -> Post.

DTO creado -> PostDTO. Lo devuelve el endpoint /posts/hateoas en PostController (incluye tags y user withSelfRel()) 

Todos los controllers documentados.

[Click en este link para ir al ERD del proyecto!](https://drive.google.com/file/d/11hL1tf5_2xVNIBKu1C41obfKX95nigiH/view)

Muchas gracias por todo, profe!
