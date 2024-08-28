package com.nttdata.glue;

import com.nttdata.steps.StoreOrderSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.Map;

public class StoreOrderStepsDefs {

    @Steps
    StoreOrderSteps storeOrderSteps;

    private long lastOrderId; // Variable para almacenar el último ID de la orden creada

    @When("creo una orden en la tienda con los siguientes datos")
    public void creoUnaOrdenEnLaTiendaConLosSiguientesDatos(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        lastOrderId = storeOrderSteps.crearOrdenConIdDinamico(dataMap); // Guarda el ID de la orden creada
    }

    @Then("el código de respuesta de la creación de la orden es {int}")
    public void elCodigoDeRespuestaDeLaCreacionDeLaOrdenEs(int statusCode) {
        storeOrderSteps.validarCodigoRespuesta(statusCode);
    }

    @When("consulto la orden con ID {long}")
    public void consultoLaOrdenConID(long orderId) {
        storeOrderSteps.consultarOrden(orderId);
    }

    @When("consulto la última orden creada")
    public void consultoLaUltimaOrdenCreada() {
        storeOrderSteps.consultarOrden(lastOrderId);
    }

    @Then("el código de respuesta de la consulta de la orden es {int}")
    public void elCodigoDeRespuestaDeLaConsultaDeLaOrdenEs(int statusCode) {
        storeOrderSteps.validarCodigoRespuesta(statusCode);
    }

    @Then("la orden contiene los siguientes datos")
    public void laOrdenContieneLosSiguientesDatos(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        storeOrderSteps.validarContenidoOrden(dataMap);
    }

    @When("intento crear una orden en la tienda con los siguientes datos")
    public void intentoCrearUnaOrdenEnLaTiendaConLosSiguientesDatos(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMap(String.class, String.class);
        storeOrderSteps.crearOrdenConIdDinamico(dataMap);
    }

    @Then("el código de respuesta de la creación fallida de la orden es {int}")
    public void elCodigoDeRespuestaDeLaCreacionFallidaDeLaOrdenEs(int statusCode) {
        storeOrderSteps.validarCodigoRespuesta(statusCode);
    }

    @Then("el mensaje de error es {string}")
    public void elMensajeDeErrorEs(String errorMessage) {
        storeOrderSteps.validarMensajeDeError(errorMessage);
    }

    @And("la respuesta de la creación de la orden contiene los datos enviados")
    public void laRespuestaDeLaCreacionDeLaOrdenContieneLosDatosEnviados() {
        storeOrderSteps.validarDatosEnviados();
    }
}