package com.nttdata.glue;

import com.nttdata.steps.CrearUsuario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

public class CrearUsuarioStepsDefs {

    @Steps
    CrearUsuario crearUsuario;

    @When("creo el usuario con username {string}, firstname {string}, lastname {string}")
    public void crearUsuario(String username, String firstName, String lastName){
        crearUsuario.crearUsuario(username, firstName, lastName);
    }

    @Then("el código de respuesta es {int}")
    public void elCódigoDeRespuestaEs(int statusCode) {
        crearUsuario.validarCodigoRespuesta(statusCode);
    }

    @And("el type es {string}")
    public void elTypeEs(String type) {
        crearUsuario.validarType(type);
    }

//    @When("busco el usuario con username \"jperez")
//    public void buscoElUsuarioConUsernameJperez() throws Throwable {
//        crearUsuario.buscarUsuario("jperez");
//        Assert.assertTrue("El usuario no existe", crearUsuario.buscarUsuario("jperez"));
//    }

    @When("busco el usuario con username {string}")
    public void buscoElUsuarioConUsername(String username) {
        boolean existe = crearUsuario.buscarUsuario(username);
        Assert.assertTrue("El usuario no existe", existe);
    }

    @Then("el username es {string}")
    public void elUsernameEs(String username) {
        crearUsuario.validarUsername(username);
    }

    @Given("el usuario con username {string}")
    public void elUsuarioConUsername(String arg0) {
        crearUsuario.buscarUsuario(arg0);
    }

    @When("modifico el usuario con username {string}")
    public void modificoElUsuarioConUsername(String username) {
        crearUsuario.modificarUsuario(username);
    }

    @Given("consulto el usuario con nombre {string}")
    public void consultoElUsuarioConNombre(String arg0) {
        crearUsuario.consultarUsuario(arg0);
    }

    @When("actualizo el usuario {string} con nuevo username {string}, firstname {string}, lastname {string}, email {string}, phone {string}")
    public void actualizoElUsuarioConNuevoUsernameFirstnameLastnameEmailPhone(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
        crearUsuario.actualizarUsuario(arg0, arg1, arg2, arg3, arg4, arg5);
    }
}
