package com.nttdata.glue;

import com.nttdata.steps.ConsultarInventario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class ConsultarInventarioStepsDefs {

    @Steps
    ConsultarInventario consultarInventario;

    @When("consulto el inventario de la tienda")
    public void consultoElInventarioDeLaTienda() {
        consultarInventario.consultarInventario();
    }

    @Then("el código de respuesta del inventario es {int}")
    public void elCodigoDeRespuestaDelInventarioEs(int statusCode) {
        consultarInventario.validarCodigoRespuesta(statusCode);
    }

    @Then("el inventario contiene las propiedades adicionales")
    public void elInventarioContieneLasPropiedadesAdicionales() {
        consultarInventario.validarPropiedadesAdicionales();
    }
}
