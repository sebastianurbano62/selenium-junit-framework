package tests;


import org.junit.Before;
import org.junit.Test;
import pages.MultibankValidations;
import utils.Driver;


public class MultibankDemo extends BaseTest {

    private String className = this.getClass().getSimpleName();

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
            report.startTest(testHeader, driver, "escenario1", "100");
            report.testFail(e.getMessage());
            throw e;
        }

    }

    @Test
    public void MultibankTest() throws Throwable {

        initializeTest("Multibank", className);

        try {

            /* * * INICIO DE PRUEBA * * */

            MultibankValidations multibankpage = new MultibankValidations(driver, report);
            multibankpage.goToHomePage();


            Thread.sleep(2000);

            multibankpage.LogInValidations();

            /* * * FIN DE PRUEBA * * */
            // TODO determinar el estado verdadero del test con algun metodo del reporte
            this.testResult = "PASS";
        } catch (Exception e) {
            this.testResult = "FAIL";
            e.printStackTrace();

            throw e;
        }
    }

    private void initializeTest(String escenario, String className) throws Throwable {

        String brakeline = "<br>";
        String testID = "100";
        String testBrowser = "chrome";
        String testDescription = "Prueba de multibankbancalinea";
        String testParameter = "bancalinea";
        String testCategory = "bancalinea";
        String result_ID = counter + "_" + testID + "_" + className;

        System.out.println("Initializing TC #" + testID);

        String testHeader = "<b>" + "ID # " + testID + "</b>" + brakeline;
        testHeader += "Escenario: " + "<b>" + escenario + "</b>" + brakeline;
        testHeader += "Navegador: " + "<b>" + testBrowser + "</b>" + brakeline;
        testHeader += "Categoría: " + "<b>" + testCategory + "</b>" + brakeline;
        testHeader += "Parámetro: " + "<b>" + testParameter + "</b>";

        report.startTest(testHeader, driver, testDescription, result_ID);

    }
}
