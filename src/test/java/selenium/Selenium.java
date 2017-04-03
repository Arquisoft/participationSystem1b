package selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import controllers.Application;
import util.SeleniumUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class Selenium {
	private WebDriver driver;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		driver = new HtmlUnitDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * Iniciar sesión con exito como usuario
	 * 
	 * @throws Exception
	 */
	@Test
	public void inicarSesion() throws Exception {
		driver.get(baseUrl + "/");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "login", 25);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("nombre2@gmail.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("login")).click();
		SeleniumUtils.textoPresentePagina(driver, "Titulo");
		SeleniumUtils.textoPresentePagina(driver, "Contenido");
	}

	/**
	 * Iniciar sesión sin exito como user
	 * 
	 * @throws Exception
	 */
	@Test
	public void inicarSesionMal() throws Exception {
		driver.get(baseUrl + "/");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "login", 25);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("nombre2@gmail.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("pass");
		driver.findElement(By.id("login")).click();
		SeleniumUtils.textoPresentePagina(driver, "Error");
		SeleniumUtils.textoPresentePagina(driver,
				"Se ha producido algún error, por favor contacte con un administrador");
	}

	/**
	 * Cerrar sesion desde usuario
	 * 
	 * @throws Exception
	 */
	@Test
	public void cerrarSesion() throws Exception {
		driver.get(baseUrl + "/");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "login", 25);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("nombre2@gmail.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("login")).click();
		SeleniumUtils.textoPresentePagina(driver, "Titulo");
		driver.findElement(By.linkText("Cerrar Sesion")).click();
		driver.get(baseUrl + "/cerrarSesion");

	}

	/**
	 * Sumar un voto a "SI" a una sugerencia
	 * 
	 * @throws Exception
	 */
	@Test
	public void votarSiSugerencia() throws Exception {
		driver.get(baseUrl + "/");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "login", 25);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("nombre2@gmail.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("login")).click();
		SeleniumUtils.textoPresentePagina(driver, "Titulo");
		driver.findElement(By.id("Si")).click();
		SeleniumUtils.textoPresentePagina(driver, "1");
	}

	/**
	 * Sumar un voto a "NO" a una sugerencia
	 * 
	 * @throws Exception
	 */
	@Test
	public void votarNoSugerencia() throws Exception {
		driver.get(baseUrl + "/");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "login", 25);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("nombre2@gmail.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("login")).click();
		SeleniumUtils.textoPresentePagina(driver, "Titulo");
		driver.findElement(By.id("No")).click();
		driver.findElement(By.id("No")).click();
		SeleniumUtils.textoPresentePagina(driver, "2");
	}

	/**
	 * Crear una sugerencia
	 */
	@Test
	public void crearSugerencia() {
		driver.get(baseUrl + "/");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "login", 25);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("nombre2@gmail.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("login")).click();
		SeleniumUtils.textoPresentePagina(driver, "Titulo");
		driver.findElement(By.linkText("Añadir Sugerencia")).click();
		driver.findElement(By.id("titulo")).clear();
		driver.findElement(By.id("titulo")).sendKeys("Sugerencia 4");
		driver.findElement(By.id("contenido")).clear();
		driver.findElement(By.id("contenido")).sendKeys("asdadfafw");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		SeleniumUtils.textoPresentePagina(driver, "Sugerencia 4");
	}

	/**
	 * Arrepentirse de crear una sugerencia y volver a la lista de sugerencias
	 */
	@Test
	public void irCrearSugerenciaYVolver() {
		driver.get(baseUrl + "/");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "login", 25);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("nombre2@gmail.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("login")).click();
		SeleniumUtils.textoPresentePagina(driver, "Titulo");
		driver.findElement(By.linkText("Añadir Sugerencia")).click();
		driver.findElement(By.linkText("Sugerencias")).click();
	}

	/**
	 * Comentar sugerencia
	 */
	@Test
	public void comentarSugerencia() {
		 driver.get(baseUrl + "/");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("password");
		    driver.findElement(By.id("email")).clear();
		    driver.findElement(By.id("email")).sendKeys("nombre2@gmail.com");
		    driver.findElement(By.id("login")).click();
		    driver.findElement(By.linkText("Añadir Sugerencia")).click();
		    driver.findElement(By.id("titulo")).clear();
		    driver.findElement(By.id("titulo")).sendKeys("Sugerencia 4");
		    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		    driver.findElement(By.linkText("Sugerencia 4")).click();
		    driver.findElement(By.id("comentario")).clear();
		    driver.findElement(By.id("comentario")).sendKeys("Comentario 1");
		    driver.findElement(By.id("guardar")).click();
		    SeleniumUtils.textoPresentePagina(driver, "Comentario 1");

	}
	
	/**
	 * Votar "Si" comentario
	 */
	@Test
	public void votarSiComentario() {
		 driver.get(baseUrl + "/");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("password");
		    driver.findElement(By.id("email")).clear();
		    driver.findElement(By.id("email")).sendKeys("nombre2@gmail.com");
		    driver.findElement(By.id("login")).click();
		    driver.findElement(By.linkText("Añadir Sugerencia")).click();
		    driver.findElement(By.id("titulo")).clear();
		    driver.findElement(By.id("titulo")).sendKeys("Sugerencia 4");
		    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		    driver.findElement(By.linkText("Sugerencia 4")).click();
		    driver.findElement(By.id("comentario")).clear();
		    driver.findElement(By.id("comentario")).sendKeys("Comentario 1");
		    driver.findElement(By.id("guardar")).click();
		    SeleniumUtils.textoPresentePagina(driver, "Comentario 1");
		    driver.findElement(By.id("Si")).click();
			SeleniumUtils.textoPresentePagina(driver, "1");		    

	}
	
	/**
	 * Votar "No" comentario
	 */
	@Test
	public void votarNoComentario() {
		 driver.get(baseUrl + "/");
		    driver.findElement(By.id("password")).clear();
		    driver.findElement(By.id("password")).sendKeys("password");
		    driver.findElement(By.id("email")).clear();
		    driver.findElement(By.id("email")).sendKeys("nombre2@gmail.com");
		    driver.findElement(By.id("login")).click();
		    driver.findElement(By.linkText("Añadir Sugerencia")).click();
		    driver.findElement(By.id("titulo")).clear();
		    driver.findElement(By.id("titulo")).sendKeys("Sugerencia 4");
		    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		    driver.findElement(By.linkText("Sugerencia 4")).click();
		    driver.findElement(By.id("comentario")).clear();
		    driver.findElement(By.id("comentario")).sendKeys("Comentario 1");
		    driver.findElement(By.id("guardar")).click();
		    SeleniumUtils.textoPresentePagina(driver, "Comentario 1");
		    driver.findElement(By.id("No")).click();
			SeleniumUtils.textoPresentePagina(driver, "1");		    

	}
	
	

	/**
	 * Iniciar sesión con exito como administrador
	 * 
	 * @throws Exception
	 */
	@Test
	public void iniciarSesionAdmin() throws Exception {
		driver.get(baseUrl + "/");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "login", 25);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("login")).click();
		SeleniumUtils.textoPresentePagina(driver, "Editar");
		SeleniumUtils.textoPresentePagina(driver, "Borrar");

	}

	/**
	 * Iniciar sesión sin exito como administrador
	 * 
	 * @throws Exception
	 */
	@Test
	public void inicarSesionAdminMal() throws Exception {
		driver.get(baseUrl + "/");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "login", 25);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("pass");
		driver.findElement(By.id("login")).click();
		SeleniumUtils.textoPresentePagina(driver, "Error");
		SeleniumUtils.textoPresentePagina(driver,
				"Se ha producido algún error, por favor contacte con un administrador");

	}

	// /**
	// * Cerrar sesion desde administrador
	// *
	// * @throws Exception
	// */
	// @Test
	// public void cerrarSesionAdmin() throws Exception {
	// driver.get(baseUrl + "/");
	// SeleniumUtils.EsperaCargaPagina(driver, "id", "login", 25);
	// driver.findElement(By.id("email")).clear();
	// driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
	// driver.findElement(By.id("password")).clear();
	// driver.findElement(By.id("password")).sendKeys("password");
	// driver.findElement(By.id("login")).click();
	// SeleniumUtils.textoPresentePagina(driver, "Titulo");
	// driver.findElement(By.linkText("Cerrar Sesion")).click();
	// driver.get(baseUrl + "/cerrarSesion");
	//
	// }
	//
	// /**
	// * Editar tarea
	// *
	// * @throws Exception
	// */
	// @Test
	// public void editarTarea() throws Exception {
	// driver.get(baseUrl + "/");
	// SeleniumUtils.EsperaCargaPagina(driver, "id", "login", 25);
	// driver.findElement(By.id("email")).clear();
	// driver.findElement(By.id("email")).sendKeys("admin@gmail.com");
	// driver.findElement(By.id("password")).clear();
	// driver.findElement(By.id("password")).sendKeys("password");
	// driver.findElement(By.id("login")).click();
	// SeleniumUtils.textoPresentePagina(driver, "Titulo");
	// driver.findElement(By.xpath("(//button[@type='submit'])[5]")).click();
	// driver.findElement(By.id("inputType")).clear();
	// driver.findElement(By.id("inputType")).sendKeys("Sugerencia 3");
	// driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	// driver.findElement(By.linkText("Sugerencias")).click();
	//
	// }

}
