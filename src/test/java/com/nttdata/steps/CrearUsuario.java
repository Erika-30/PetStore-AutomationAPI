package com.nttdata.steps;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class CrearUsuario {

    private static String CREATE_USER = "https://petstore.swagger.io/v2/user";

    @Step("Crear usuario {0} en PetStore")
    public void crearUsuario(String username, String firstName, String lastName){
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \""+username+"\",\n" +
                        "  \"firstName\": \""+firstName+"\",\n" +
                        "  \"lastName\": \""+lastName+"\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .log().all()
                .post(CREATE_USER)
                .then()
                .log().all()
        ;

    }

    public void validarType(String type) {
        restAssuredThat(response -> response.body("'type'", equalTo(type)));
        System.out.println("Type: " + SerenityRest.lastResponse().body().path("type").toString());
        System.out.println(SerenityRest.lastResponse().print());
    }

    public void validarCodigoRespuesta(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }

    public boolean buscarUsuario(String jperez) {
        boolean existe = false;
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .log().all()
                .get(CREATE_USER + "/" + jperez)
                .then()
                .log().all()
        ;
        if (SerenityRest.lastResponse().statusCode() == 200) {
            existe = true;
        }
        return existe;
    }

    public void validarUsername(String username) {
        restAssuredThat(response -> response.body("username", equalTo(username)));
    }

    public void modificarUsuario(String username) {
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \""+username+"\",\n" +
                        "  \"firstName\": \"string"+"\",\n" +
                        "  \"lastName\": \"string"+"\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .log().all()
                .put(CREATE_USER + "/" + username)
                .then()
                .log().all()
        ;
    }

    public void consultarUsuario(String arg0) {
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .log().all()
                .get(CREATE_USER + "/" + arg0)
                .then()
                .log().all()
        ;
    }

    public void actualizarUsuario(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \""+arg1+"\",\n" +
                        "  \"firstName\": \""+arg2+"\",\n" +
                        "  \"lastName\": \""+arg3+"\",\n" +
                        "  \"email\": \""+arg4+"\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"phone\": \""+arg5+"\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .log().all()
                .put(CREATE_USER + "/" + arg0)
                .then()
                .log().all()
        ;
    }
}

