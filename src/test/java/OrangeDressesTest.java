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


public class OrangeDressesTest {
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

        WebElement women = driver.findElement(WOMEN);
        women.click();

        WebElement dresses = driver.findElement(DRESSES);
        dresses.findElement(DRESSES_MENU).click();

        WebElement eveningDresses = driver.findElement(DRESSES);
        eveningDresses.findElement(EV_DRESSES).click();

        driver.findElement(LIST).click();

        WebElement details = driver.findElement(BUTTONS);
        details.findElement(MORE).click();

        WebElement add = driver.findElement(ADD);
        add.findElement(ADD_TO_CART).click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CLOSE_POPUP));

        WebElement closePopUp = driver.findElement(CLOSE_POPUP);
        closePopUp.findElement(CONTINUE_SHOP).click();

        WebElement productPrice = driver.findElement(PRODUCT_PRICE);
        String productPriceTxt = productPrice.getText();
        String replacedProductPriceTxt = productPriceTxt.replaceAll("[$]", "");
        Double productPriceDbl = Double.parseDouble(replacedProductPriceTxt);

        WebElement viewCart = driver.findElement(VIEW_CART);
        driver.findElement(VIEW_CART).click();

        WebElement prodPriceInCart = driver.findElement(PRODUCT_PRICE_IN_CART);
        String prodPriceInCartTxt = driver.findElement(PRODUCT_PRICE_IN_CART).getText();
        String replacedProdPriceInCartTxt = prodPriceInCartTxt.replaceAll("[$]", "");
        Double prodPriceInCartDbl = Double.parseDouble(replacedProdPriceInCartTxt);

        Assertions.assertEquals(productPriceDbl, prodPriceInCartDbl, "Wrong price!");

        WebElement womenMenu = driver.findElement(WOMEN);
        driver.findElement(WOMEN).click();

        WebElement dressesMenu = driver.findElement(DRESSES);
        dressesMenu.findElement(DRESSES_MENU).click();

        List<WebElement> dressesList = driver.findElements(DRESS_ARRAY);
        WebElement randomDress = dressesList.get(2);
        WebElement anotherDressDetails = driver.findElement(BUTTONS);
        anotherDressDetails.findElement(MORE).click();

        WebElement addAnotherDress = driver.findElement(ADD);
        addAnotherDress.findElement(ADD_TO_CART).click();

        WebDriverWait waiting = new WebDriverWait(driver, 30);
        waiting.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CLOSE_POPUP));

        WebElement closeAnotherPopUp = driver.findElement(CLOSE_POPUP);
        closeAnotherPopUp.findElement(CONTINUE_SHOP).click();

        WebElement anotherProductPrice = driver.findElement(PRODUCT_PRICE);
        String anotherProductPriceTxt = driver.findElement(PRODUCT_PRICE).getText();
        String replacedAnotherProductPriceTxt = anotherProductPriceTxt.replaceAll("[$]", "");
        Double anotherProductPriceDbl = Double.parseDouble(replacedAnotherProductPriceTxt);

        Double sumOfDresses = productPriceDbl + anotherProductPriceDbl;

        sumOfDresses = Math.round(sumOfDresses*100)/100.0d;

        WebElement viewCartAll = driver.findElement(VIEW_CART);
        driver.findElement(VIEW_CART).click();

        WebElement allProdPriceInCart = driver.findElement(TOTAL_PRICE);
        String allProdPriceInCartTxt = allProdPriceInCart.getText();
        String replacedAllProdPriceInCartTxt = allProdPriceInCartTxt.replaceAll("[$]", "");
        Double allProdPriceInCartDbl = Double.parseDouble(replacedAllProdPriceInCartTxt);

        Assertions.assertEquals(sumOfDresses, allProdPriceInCartDbl, "Wrong price!");

    }
    @AfterEach
    public void closeBrowser (){
        driver.close();
    }
}
