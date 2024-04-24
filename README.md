### Práctica RestAssured - Proyecto Stars Wars 
---

###Tecnologías utilizadas:
<img src="https://github.com/Candeagainn/StarsWarsProject/assets/104110428/85f218f3-8095-45ef-8ff8-dc0bc867bd69">
Este es un proyecto de de API Testing usando Java, RestAssured e implementando POJOS
En esta práctica hicimos testing a una API real. Se realizó el proyecto con una arquitectura adecuada para organizar y poder reutilizar las llamadas a la API, usando POJOS.
Se hizo un test para cada request diferente, realizando las siguientes acciones:

1. Probar el endpoint people/2/ y comprobar que la respuesta sea un código 200, que el atributo "skin color" sea "gold" y la cantidad de peliculas en las que aparece, se debe verificar que sean 6
---
2. Solicitar el endpoint de la segunda pelicula en la que people/2 estuvo presente (pero usando la respuesta que obtuvimos de la primera llamada). Verificar que la fecha de estreno tenga el formato correcta y que la respuesta incluya personajes, planetas, naves, vehiculos y especies. Esos elementos no deben estar vacíos. 
---
3. Solicitar  el endpoint del primer planeta presente en la llamada al endpoint de la pelicula que hicimos en el test 2 (pero usando la respuesta de la llamada anterior). Verificar que la gravedad y el terreno coincidan con los valores devueltos por la respuesta, usando fixtures.
---
4. En la misma respuesta que obtuvimos al llamar al planeta, tomar el url de la respuesta y llamarlo. Validar que la respuesta sea exactamente la misma que la del llamado anterior.
---
5. Hacer una llamada al films/7 y verificar que la respuesta sea un código de error 404.


---
### RestAssured Practice

For this practice, we are going to test a real API using JAVA, RestAssured, any test runner and an appropriate framework architecture to organize and reuse the API calls (Do not forget POJOS!). 
We will use the Star Wars API (swapi) for this exercise, the base URL is https://swapi.dev/ and we need to Implement tests (a different test for each request) performing the following actions:

1) Test the endpoint people/2/ and check the success response, the skin color to be gold, and the amount of films it appears on (should be 6).
2) Request the endpoint of the second movie in which people/2/ was present (using the response from people/2/). Check the release date to be in the correct date format, and the response to include characters, planets, starships, vehicles and species, each element including more than 1 element.
3) Request the endpoint of the first planet present in the last film's response (using the previous response). Check the gravity and the terrains matching the exact same values returned by the request (Use fixtures to store and compare the data of terrains and gravity).
4) On the same response from the planet, grab the url element on the response, and request it. Validate the response being exactly the same from the previous one.

Request the /films/7/ and check the response having a 404 code.
