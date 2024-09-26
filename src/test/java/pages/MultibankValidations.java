package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Report;

public class MultibankValidations {

    private WebDriver driver;
    private Report report;
    protected static WebDriverWait wait_60s;
    protected static WebDriverWait wait_30s;
    protected static WebDriverWait wait_10s;
    protected static WebDriverWait wait_5s;
    protected static WebDriverWait wait_2s;
    private static final String fs = File.separator;

    public MultibankValidations(WebDriver driver, Report report) {
        this.driver = driver;
        this.report = report;
        BasePage.wait_60s = new WebDriverWait(driver, Duration.ofSeconds(60));
        BasePage.wait_30s = new WebDriverWait(driver, Duration.ofSeconds(30));
        BasePage.wait_10s = new WebDriverWait(driver, Duration.ofSeconds(10));
        BasePage.wait_5s = new WebDriverWait(driver, Duration.ofSeconds(5));
        BasePage.wait_2s = new WebDriverWait(driver, Duration.ofSeconds(2));
        PageFactory.initElements(this.driver, this);
    }

    public void goToHomePage() throws Throwable {
        try {

//            String url = System.getProperty("user.dir") + fs + "src" + fs + "test" + fs + "resources" + fs
//                    + "practicePage" + fs + "index.html";
//            System.out.println(url);
//			driver.navigate().to("file://" + url);
            driver.get("https://www.multibank.com.pa/es");
            report.testPass("Se ingresa a la página de inicio", "goToHomePage", true);
        } catch (Throwable t) {

            report.testFail("no se desplegó la página de inicio.", "goToHomePage", true);
            throw t;
        }
    }

    public void LogInValidations() throws IOException {

        this.report = new Report();

        try {
            //Validar que se vea el logo de multibank
            if(driver.findElement(By.xpath("//img[@class='logo-site']")).isDisplayed()){
                System.out.println("Validación correcta, se muestra logo en página de inicio\n");
                report.testPass("Validar que se ve el logo ", "HomePage", false);
            } else {
                System.out.println("Validación incorrecta, No se muestra logo en página de inicio\n");
                report.testFail("Validar que se ve el logo ");
            }

            //Se hace click en banca en linea
            driver.findElement(By.xpath("//li/a[contains(text(),'Banca en Línea')]")).click();
            System.out.println("Se hizo click en banca en linea\n");

            //Se valida que entro a banca en linea
            if(driver.findElement(By.xpath("//input[@id='step01']")).isDisplayed()){
                System.out.println("Validación correcta, se accede a banca en línea\n");
                report.testPass("Validar que se accede a banca en línea ", "BancaLinea", false);
            } else {
                System.out.println("Validación incorrecta, No se se accede a banca en línea\n");
                report.testFail("Validar que se accede a banca en línea ");
            }

            //Escribir en campo de ingresar usuario
            driver.findElement(By.xpath("//li/a[contains(text(),'Banca en Línea')]")).sendKeys("chicharito");
            System.out.println("Se escribio el usuario\n");

            //Dar click en botón continuar
            driver.findElement(By.xpath("//span[contains(text(),'Siguiente')]")).click();
            System.out.println("Se dio click en continuar\n");

            //Se valida que el botón funciona
            if(driver.findElement(By.xpath("//input[@id='step02']")).isDisplayed()){
                System.out.println("Validación correcta, se pasa a contraseña\n");
                report.testPass("Validar que se pasa a contraseña", "Contraseña", false);
            } else {
                System.out.println("Validación incorrecta, No se pasa a contraseña\n");
                report.testFail("Validar que se pasa a contraseña");
            }

            //Ingresar contraseña
            driver.findElement(By.xpath("//input[@id='step02']")).sendKeys("Contraseña");
            System.out.println("Se escribio contraseña\n");

        }
        catch(Exception ex){
            System.out.println("LogInValidations finalizado con error\n" + ex);
        }
    }
}

