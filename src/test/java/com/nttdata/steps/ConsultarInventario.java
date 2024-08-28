package com.nttdata.steps;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.hasKey;

public class ConsultarInventario {

    private static final String INVENTORY_URL = "https://petstore.swagger.io/v2/store/inventory";

    @Step("Consultar inventario de la tienda")
    public void consultarInventario() {
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .log().all()
                .get(INVENTORY_URL)
                .then()
                .log().all();
    }

    @Step("Validar el código de respuesta")
    public void validarCodigoRespuesta(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }


    @Step("Validar que el inventario contiene las propiedades adicionales")
    public void validarPropiedadesAdicionales() {
        restAssuredThat(response -> response.body("$", hasKey("available")));
        restAssuredThat(response -> response.body("$", hasKey("pending")));
        restAssuredThat(response -> response.body("$", hasKey("sold")));
    }






}
