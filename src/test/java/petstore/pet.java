// 1 - Pacote
package petstore;

// 2 - Bibliotecas

import io.restassured.internal.RestAssuredResponseOptionsGroovyImpl;
import javafx.animation.PathTransitionBuilder;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.omg.IOP.CodecPackage.TypeMismatchHelper.extract;

// 3 - Classe
public class pet {
    // 3.1 - Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet

    // 3.2 - Métodos e Funções
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Incluir - Create - Post
    @Test(priority = 1)
    // Identifica o método ou função como um teste para o TestNG
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe Gherkin
        // Dado - Quando - Então
        // Given - When - Then

       given() // Script Rest
                .contentType("application/json") // comum em API REST - antigas era "text/xml"
                .log().all()
                .body(jsonBody)
                .when()  // Quando
                .post(uri)
                .then()  // Então
                .log().all()
                .statusCode(200)
                .body("name", is("Sheila"))
               .body("status", is("available"))
               //.body("category.nome", contains("dog"))
               .body("category.nome", is("dog"));

    }
    //Método Consulta
    @Test(priority = 2)
    public void consultarpet(){
        String petId = "77910321";

        given()
                    .contentType("aplication/json")
                    .log() .all()
                .when()
                    .get(uri + "/" + petId)
                .then()
                    .log() .all()
                    .statusCode(200)
                .body("name", is("Sheila"))
                .body("category.name", is("dog"))
                .body("status", is("available"));
        
            .extract()
                .path("category.name");

        String token;
        System.out.println("O token é" + token);

    }


    }

}