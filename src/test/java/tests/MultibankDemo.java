package tests;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import pages.BasePage;
import pages.HomePage;
import pages.MultibankValidations;
import utils.DataTable;
import utils.Driver;
import utils.Report;
import utils.ResultTables;

import java.io.FileInputStream;

public class MultibankDemo extends BaseTest {

    @Before
    public void runBeforTest() throws Exception {
        System.out.println("@Before each test.");
        driver = null;

        try {
            Driver driverConfig = new Driver("chrome");
            driver = driverConfig.getDriver();

        } catch (Exception e) {
            String testHeader = "<b>" + "Prueba #</b>" + counter + "<br>";
            testHeader += "<b>" + " SETEO DE DATOS FALLIDO " + "</b>";
            report.startTest(testHeader, null, null, null);
            report.testFail(e.getMessage());
            throw e;
        }

    }

    @Test
    public void MultibankTest() throws Throwable {

        try {
            /* * * INICIO DE PRUEBA * * */

            MultibankValidations basePage = new MultibankValidations(driver, report);
            basePage.goToHomePage();


            Thread.sleep(2000);

            basePage.LogInValidations();

            /* * * FIN DE PRUEBA * * */
            // TODO determinar el estado verdadero del test con algun metodo del reporte
            this.testResult = "PASS";
        } catch (Exception e) {
            this.testResult = "FAIL";
            e.printStackTrace();

            throw e;
        }
    }
}
