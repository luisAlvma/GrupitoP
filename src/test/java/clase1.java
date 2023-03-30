import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class clase1 {
    
    @Before
    public void Configuracion() {
    	
    }
    
    @Test
    public void testLogin() throws InterruptedException {
    	
    	
    	
    	
    	
        System.setProperty("webdriver.chrome.driver", "./src/test/resources//Driver/chromedriver.exe"); // Ruta al archivo chromedriver.exe

        WebDriver driver= new ChromeDriver();
    
    
        driver.get("https://bancaporinternetuat.interbank.pe/login"); // URL de la página de inicio de sesión
       
        // Seleccionar los elementos necesarios para la prueba
        
        WebElement accountNumberField = driver.findElement(By.xpath("//input[@id='25']")); // Selector del campo de número de cuenta
        accountNumberField.click();
       
        WebElement dniField = driver.findElement(By.xpath("//input[@id='39']")); // Selector del campo de DNI
        
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='46']")); // Selector del campo de clave
        
        WebElement Desplegable = driver.findElement(By.xpath("//i[contains(@class,'icon icon-ibk-angle-down')]"));
        WebElement SeleDni = driver.findElement(By.xpath("//li[contains(.,'DNI')]"));
        WebElement Check =driver.findElement(By.xpath("//span[contains(@class,'label')]"));
        WebElement btnLogin = driver.findElement(By.xpath("//button[contains(.,'Ingresar')]"));
       
        
        
        
        // Abrir el archivo Excel que contiene los datos de prueba
        String filePath = "./src/test/resources/DataCargar/archivo.xlsx";
        Workbook workbook = null;
        boolean firstRow = true; // Variable booleana para indicar si la fila actual es la primera
        
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(inputStream); // Inicializar el objeto Workbook
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Sheet sheet = workbook.getSheetAt(0); // Seleccionar la primera hoja del archivo Excel
        
        // Iterar sobre cada fila del archivo Excel para realizar una prueba de inicio de sesión con cada conjunto de datos de prueba
        
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue; // Saltar la fila si está vacía
            }
            
            // Leer los datos de la fila actual del archivo Excel
       
            String accountNumber = "";
            String dni = "";
            String password = "";
            Cell accountNumberCell = row.getCell(0);
            if (accountNumberCell.getCellType() == CellType.NUMERIC) {
                accountNumber = String.valueOf((long) accountNumberCell.getNumericCellValue());
            } else {
                accountNumber = accountNumberCell.getStringCellValue();
            }
            Cell dniCell = row.getCell(1);
            if (dniCell.getCellType() == CellType.NUMERIC) {
                dni = String.valueOf((long) dniCell.getNumericCellValue());
            } else {
                dni = dniCell.getStringCellValue();
            }
            Cell passwordCell = row.getCell(2);
            if (passwordCell.getCellType() == CellType.NUMERIC) {
                password = String.valueOf((long) passwordCell.getNumericCellValue());
            } else {
                password = passwordCell.getStringCellValue();
            }
            System.out.print(accountNumber);
            System.out.print(dni);
            System.out.print(password);

            
            // Ingresar el número de cuenta y el DNI y contraseña
            accountNumberField.sendKeys(accountNumber);
            
            Thread.sleep(2000);
            Desplegable.click();
            
            SeleDni.click();
            Thread.sleep(100);
          
            dniField.sendKeys(dni);
            
            Thread.sleep(100);
            
    
            passwordField.click();
            
          //  WebElement virtualKeyboardButton = driver.findElement(By.xpath("//div[@class='keyboard__loader']")); // Selector del botón de teclado virtual
            
            //virtualKeyboardButton.sendKeys(password);
           System.out.print(" ingresar contra");
            Thread.sleep(9000);
           System.out.print("termino tiempo de ingresar contra");
            
            //Seleccionar Recordar
            Check.click();
            Thread.sleep(2000);
            //login
            btnLogin.click();
            System.out.print("Se envio la info esperando a que cargue la pagina");
            Thread.sleep(10000);
            System.out.print("buscando elemento");
            
           
         // Identifica el elemento del menú
            WebDriverWait wait = new WebDriverWait(driver, 10);
            
            WebElement menu = driver.findElement(By.xpath("//a[@href='#']"));

            // Espera hasta que el elemento sea visible y esté interactuable
          
            wait.until(ExpectedConditions.elementToBeClickable(menu));

            menu.click();
            
            Thread.sleep(5000);
            System.out.print("Boton menu:"+ menu);
            
           //detectamos elemento 
            WebElement Close = driver.findElement(By.xpath("//a[@href='javascript:void(0);'][contains(.,'Cerrar sesión')]"));
            
            Thread.sleep(2000);
            //cerrar session
            
             Close.click();
             
             System.out.print("cerrando sesion");
             
             WebElement limpiarNc = driver.findElement(By.xpath("(//i[contains(@class,'icon-appended--clickeable')])[1]"));
          // Espera hasta que el elemento sea visible y esté interactuable
             WebDriverWait waitlogin = new WebDriverWait(driver, 10);
             wait.until(ExpectedConditions.elementToBeClickable(limpiarNc));
             
             
             //limpiar
            
             limpiarNc.click(); 
             
             
        }
}
    
    
    
    
    
    
}

    