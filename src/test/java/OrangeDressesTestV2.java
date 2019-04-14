import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class OrangeDressesTestV2 {

    private final String URL = "http://automationpractice.com";
    private WebDriver driver;
    private final By WOMEN = By.xpath(".//a[contains (@title, 'Women')]");
    private final By DRESSES = By.xpath(".//ul[contains(@class, 'tree dynamized')]");
    private final By DRESSES_MENU = By.xpath(".//a[contains(text(), 'Dresses')]");
    private final By EV_DRESSES = By.xpath(".//a[contains(text(), 'Evening')]");
    private final By LIST = By.xpath(".//li[contains (@id, 'list')]");
    private final By BUTTONS = By.xpath(".//div[contains(@class, 'right-block')]");
    private final By MORE = By.xpath(".//a[contains(@class, 'lnk_view')]");
    private final By ADD = By.xpath(".//p[contains(@id, 'add_to_cart')]");
    private final By ADD_TO_CART = By.xpath(".//button[contains(@class, 'exclusive')]");
    private final By CLOSE_POPUP = By.xpath(".//div[contains(@class, 'button-container')]");
    private final By CONTINUE_SHOP = By.xpath(".//span[contains(@class, 'continue')]");
    private final By PRODUCT_PRICE = By.xpath(".//span[contains (@id, 'our_price_display')]");
    private final By VIEW_CART = By.xpath(".//a[contains(@title, 'shopping')]");
    private final By PRODUCT_PRICE_IN_CART = By.xpath(".//span[@id = 'total_product_price_4_16_0']");
    private final By DRESS_ARRAY = By.xpath(".//a[contains(@class, 'product-name')]");
    private final By TOTAL_CART = By.xpath("[.//tr[contains(@class, 'cart_total_price')]");
    private final By TOTAL_PRICE = By.xpath(".//td[contains(@id, 'total_product')]");


    @Test
    public void dressesCheck() {
        System.setProperty("webdriver.chrome.driver", "/Users/Angelovsky/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);


        String firstDressPrice = getProductPrice(true);
        Double firstDressPriceDbl = parseDouble(firstDressPrice);

        driver.findElement(VIEW_CART).click();

        String prodPriceInCartTxt = driver.findElement(PRODUCT_PRICE_IN_CART).getText();
        Double productPriceInCartDbl = parseDouble(prodPriceInCartTxt);

        Assertions.assertEquals(firstDressPriceDbl, productPriceInCartDbl, "Wrong price!");

        String anotherDressPrice = getProductPrice(false);
        Double anotherProductPriceDbl = parseDouble(anotherDressPrice);

        Double sumOfDresses = firstDressPriceDbl + anotherProductPriceDbl;

        sumOfDresses = Math.round(sumOfDresses*100)/100.0d;

        driver.findElement(VIEW_CART).click();

        String allProdPriceInCartTxt = driver.findElement(TOTAL_PRICE).getText();
        Double allProductPriceInCartDbl = parseDouble(allProdPriceInCartTxt);

        Assertions.assertEquals(sumOfDresses, allProductPriceInCartDbl, "Wrong price!");

    }


    private String getProductPrice(boolean firstDress) {

        driver.findElement(WOMEN).click();
        driver.findElement(DRESSES).findElement(DRESSES_MENU).click();

        if (firstDress) {
            driver.findElement(DRESSES).findElement(EV_DRESSES).click();
            driver.findElement(LIST).click();
        }

        driver.findElement(BUTTONS).findElement(MORE).click();
        driver.findElement(ADD).findElement(ADD_TO_CART).click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CLOSE_POPUP));

        driver.findElement(CLOSE_POPUP).findElement(CONTINUE_SHOP).click();

        WebElement productPrice = driver.findElement(PRODUCT_PRICE);
        String productPriceTxt = productPrice.getText();

        return productPriceTxt;

    }

    private Double parseDouble(String priceStr){
        Double price = Double.parseDouble(priceStr.replaceAll("[$]", ""));
        return price;
    }

    @AfterEach
    public void closeBrowser (){
        driver.close();
    }
}
