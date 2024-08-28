package com.nttdata.steps;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class StoreOrderSteps {

    private static final String BASE_URL = "https://petstore.swagger.io/v2/store/order";

    @Step("Crear una orden con ID dinámico")
    public long crearOrdenConIdDinamico(Map<String, String> data) {
        // Generar un ID dinámico basado en el tiempo
        long dynamicId = System.currentTimeMillis();

        // Preparar los datos de la orden
        Map<String, Object> mutableData = new HashMap<>(data);
        mutableData.put("id", dynamicId);
        mutableData.put("petId", Integer.parseInt(data.get("petId")));
        mutableData.put("quantity", Integer.parseInt(data.get("quantity")));
        mutableData.put("complete", Boolean.parseBoolean(data.get("complete")));
        mutableData.put("status", data.get("status"));

        // Enviar la solicitud POST para crear la orden
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .body(mutableData)
                .log().all()
                .post(BASE_URL)
                .then()
                .log().all();

        return dynamicId;
    }

    @Step("Validar el código de respuesta {0}")
    public void validarCodigoRespuesta(int statusCode) {
        SerenityRest.restAssuredThat(response -> response.statusCode(statusCode));
    }

    @Step("Consultar la orden con ID {0}")
    public void consultarOrden(long orderId) {
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .log().all()
                .get(BASE_URL + "/" + orderId)
                .then()
                .log().all();
    }

    @Step("Validar el contenido de la orden")
    public void validarContenidoOrden(Map<String, String> expectedData) {
        expectedData.forEach((key, value) -> {
            SerenityRest.restAssuredThat(response -> response.body(key, equalTo(value)));
        });
    }

    public void validarMensajeDeError(String errorMessage) {
        SerenityRest.restAssuredThat(response -> response.body("message", equalTo(errorMessage)));
    }

    public boolean crearYVerificarOrden(Map<String, String> data) {
        long dynamicId = crearOrdenConIdDinamico(data);
        SerenityRest.lastResponse().then().statusCode(200); // Verificar que la creación fue exitosa

        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .log().all()
                .get(BASE_URL + "/" + dynamicId)
                .then()
                .log().all();

        return SerenityRest.lastResponse().statusCode() == 200;
    }

    public void validarDatosEnviados() {
        consultarOrden(Long.parseLong(SerenityRest.lastResponse().body().path("id").toString()));
    }
}