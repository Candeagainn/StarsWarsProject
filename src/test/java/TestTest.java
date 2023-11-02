// Todos los imports necesarios //

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import java.util.List;



public class TestTest {

    /*----------------------------------------------------------------
    Definimos antes, las variables de alcance global que vamos a usar
    en cada uno de los tests, porque si las dejamos en cada método, no vamos
    a poder llamarlas luego desde nuestros  diferentes métodos de test.
     ---------------------------------------------------------------------*/

    private CharacterPOJO elPojo;
    private FilmsPOJO elPojoFilms;
    Response respuesta;
    Response respuesta2;
    Response respuesta3;
    String formatoFechaEsperado;
    String gravity;
    String terrain;


    /*----------------------------------------------------------------
    En este método de testNG, asignamos todas las variables que
     tomamos de nuestra API, que se van a cargar antes de correr cada test
     ---------------------------------------------------------------------*/
    @BeforeClass
    public void setUp(){

    respuesta = RestAssured.given().get("https://swapi.dev/api/people/2");

 /*----------------------------------------------------------------
    Acá usamos el JsonPath de RestAssured para capturar cada uno de los valores
    que nos retorna el body cuando hacemos la llamada, y los guardamos en variables.
     ---------------------------------------------------------------------*/
    JsonPath jsonPathCharacter = respuesta.jsonPath();
    String skinColor = jsonPathCharacter.getString("skin_color");
    List<String> films = jsonPathCharacter.getList("films");

    formatoFechaEsperado = "\\d{4}-\\d{2}-\\d{2}";

 /*----------------------------------------------------------------
    Y creamos el objeto POJO que corresponde al endpoint de *people/2* ,
     con los argumentos que tenemos guardados en nuestras variables
     ---------------------------------------------------------------------*/
    elPojo = new CharacterPOJO(skinColor, films);


     /*----------------------------------------------------------------
    Acá hacemos lo mismo, pero con otro objeto POJO, el que corresponde al endpoint films/2.
    Usamos otro JsonPath (respuesta2), no podemos usar el anterior porque apunta
    al endpoint de people/2 y nosotros queremos ahora trabajar con el de films/2
     ---------------------------------------------------------------------*/
    respuesta2 = RestAssured.given().get(elPojo.films.get(1));
        JsonPath jsonPathFilms = respuesta2.jsonPath();
        String fechaRespuesta = jsonPathFilms.getString("release_date");
        List<String> personajes = jsonPathFilms.getList("characters");
        List<String> planetas = jsonPathFilms.getList("planets");
        List<String> naves = jsonPathFilms.getList("starships");
        List<String> vehiculos = jsonPathFilms.getList("vehicles");
        List<String> especies = jsonPathFilms.getList("species");

         /*----------------------------------------------------------------
            Creamos un nuevo POJO para el endpoint films con los atributos
            guardados anteriormente
     ---------------------------------------------------------------------*/
        elPojoFilms = new FilmsPOJO(fechaRespuesta, personajes, planetas, naves, vehiculos, especies);

         /*----------------------------------------------------------------
        Acá creamos nuevamente un nuevo JsonPath que apunte a otro endpoint,
        el del planets/4
     ---------------------------------------------------------------------*/
        respuesta3 = RestAssured.given().get(elPojoFilms.getPlanets().get(0));
        JsonPath jsonPathPlanet = respuesta3.jsonPath();

        /*----------------------------------------------------------------
        No vamos a necesitar un POJO para este endpoint porque el ejercicio
        pide que usemos un fixture, solamente extraemos los valores y los almacenamos
        en variables.
     ---------------------------------------------------------------------*/
        gravity = jsonPathPlanet.getString("gravity");
        terrain = jsonPathPlanet.getString("terrain");


    }
    @Test
    public void primerTest() {
        Assert.assertEquals(respuesta.getStatusCode(), 200);
        Assert.assertEquals(elPojo.skinColor, "gold");
        Assert.assertEquals(elPojo.films.size(), 6);

    }
    @Test
    public void segundoTest(){

        Assert.assertTrue(elPojoFilms.getDate().matches(formatoFechaEsperado));

        Assert.assertFalse(
                elPojoFilms.getCharacters().isEmpty()
                &&elPojoFilms.getPlanets().isEmpty()
                &&elPojoFilms.getStarships().isEmpty()
                &&elPojoFilms.getVehicles().isEmpty()
                &&elPojoFilms.getSpecies().isEmpty());
    }

    /*----------------------------------------------------------------
        El tercer Test lo hice así porque el ejercicio me pide utilizar
        la respuesta que obtuve en el llamado anterior, por lo tanto para no repetir
        código, directamente usé la "respuesta3" y concatené varios métodos.
     ---------------------------------------------------------------------*/

    @Test
    public void tercerTest(){

        Assert.assertEquals(gravity, (respuesta3.getBody().jsonPath().getString("gravity")));
        Assert.assertEquals(terrain, (respuesta3.getBody().jsonPath().getString("terrain")));
    }



    /*----------------------------------------------------------------
        Este test es un poco más complejo. El ejercicio me pide que de la
        Respuesta del endpoint planets/4, tome el "URL" y lo capture.

        Que luego haga una llamada a ese URL y compare que lo
        que me devuelva sea exactamente lo mismo que la llamada anterior.
     ---------------------------------------------------------------------*/
    @Test

    public void cuartoTest(){

        String url = respuesta3.getBody().jsonPath().getString("url");
        Response planetResponse = RestAssured.given().get(url);

        Assert.assertEquals(respuesta3.getBody().asString(), planetResponse.getBody().asString());
    }

    @Test
    public void quintoTest(){
        Response respuesta4 = RestAssured.given().get("https://swapi.dev/api/films/7");
        Assert.assertEquals(respuesta4.getStatusCode(), 404);
    }
}
