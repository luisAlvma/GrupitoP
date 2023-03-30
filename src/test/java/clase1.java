import java.io.FileInputStream;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class clase1 {
    
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
            Thread.sleep(2000);
            SeleDni.click();
            Thread.sleep(2000);
          
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
            Thread.sleep(1500);
            //login
            btnLogin.click();
            Thread.sleep(20000);
            
            //seleccionar elementos
            WebElement Menu = driver.findElement(By.xpath("//a[@class='header__profile-menu']"));
            WebElement Close = driver.findElement(By.xpath("//a[@href='javascript:void(0);'][contains(.,'Cerrar sesión')]"));
            //cerrar session
             Menu.isSelected();
             Close.isSelected();
            
            
            
        }
        }
        
}

    