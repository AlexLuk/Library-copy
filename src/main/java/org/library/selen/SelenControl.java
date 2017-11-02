package org.library.selen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SelenControl
{
    private final static Logger logger = LoggerFactory.getLogger( SelenControl.class );
    private static WebDriver _driver = null;
    private static WebDriverWait _wait = null;

    public SelenControl(){}

    /**
     * Starts the browser session
     */
    public boolean start()
    {
        System.setProperty( "webdriver.chrome.driver", "lib/chromedriver.exe");

        try
        {
            _driver = new ChromeDriver();
        }catch( Exception ex ){ logger.error ( "Error starting Chrome" ); return false; }
        return true;
    }

    /**
     * Opens page in the browser
     *
     *  @param	url page url
     */
    public void openPage( String url ) throws Exception
    {
        _driver.get( url );
    }

    public String getUrl() throws Exception
    {
        return _driver.getTitle();
    }

    /**
     * Gets the page element with the specified css path
     *
     *  @param	css element's css path
     *  @return element
     */
    public WebElement getElem( String css ) throws Exception
    {
        return _driver.findElement( By.cssSelector( css ) );
    }

    public WebElement getElemTag(String htmlTag) throws Exception{

        WebElement webElement= _driver.findElement( By.tagName( htmlTag));
        logger.warn(webElement.toString());
        return webElement;
    }


    public WebElement getElemID( String id ) throws Exception
    {
        return _driver.findElement(By.id(id));
    }

    /**
     * Returns web element based on it's xpath
     * @param xpath - xpath of element
     * @return - element we saerch
     * @throws Exception
     */
    public WebElement getElemXpath( String xpath ) throws Exception
    {
        return _driver.findElement(By.xpath(xpath));
    }


    /**
     * Gets page elements with the specified css path
     *
     *  @param	css elements' css path
     *  @param  index   child element index
     *  @return element
     */
    public WebElement getElemInList( String css, String index ) throws Exception
    {
        // converting the index value into int
        int ind = Integer.parseInt( index );

        if( ind < 1 )
            ind = 1;

        // searching for elements
        List<WebElement> elemsList = _driver.findElements( By.cssSelector( css ) );

        waitSomeTime( 9000 );

        // returning the required element
        return elemsList.get( ( ind - 1 ) );
    }

    /**
     *  Clicks the page element
     *
     * @param webElem Element
     */
    public void clickElem( WebElement webElem ) throws Exception
    {
        // clicking the element
        webElem.click();

        // waiting for the page to load
        waitSomeTime( 500 );
    }


    /**
     * Wait for specified amount of time untill element appears
     *
     * @param milliseconds - time to wait
     * @param expectedElemXpath - elements xpath
     */
    public void wait(int milliseconds, String expectedElemXpath){
        WebDriverWait _wait = new WebDriverWait(_driver,milliseconds);
        _wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(expectedElemXpath)));
    }

    /**
     *  Sets the text of the page element with the specified css path
     *
     * @param webElem Element
     * @param text  text to set for the element
     */
    public void setElemText( WebElement webElem, String text ) throws Exception
    {
        webElem.sendKeys( text );
    }

    /**
     * Checks a keyword is present in the child element's text
     *
     * @param webElem parent element
     * @param css child element's css path
     * @param headerTextMatch   test content of the child element's header
     * @return flag of content existence
     */
    public boolean checkChildElementContent( WebElement webElem, String css, String headerTextMatch ) throws Exception
    {
        // searching for the element's header element
        WebElement header = webElem.findElement( By.cssSelector( css ) );

        // getting the text of the header
        String headerText = header.getText();

        // comparing header text to the required match
        return headerText.contains( headerTextMatch );
    }

    /**
     * Closes the Selenium connection
     */
    public void stop()
    {
        // closing the session and stopping Selenium
        if( _driver != null )
        {
            _driver.close();
            _driver.quit();
        }
    }
    /********************************************************************************************************************/
    /**************************************** private *******************************************************************/

    /**
     * Puts the Thread asleep for a given amount of time
     *
     * @param	milliSec    int amount of time
     */
    private void waitSomeTime( int milliSec )
    {
        // waiting for milliSec
        try {
            Thread.sleep( milliSec );
        } catch( InterruptedException ex ) { Thread.currentThread().interrupt(); }
    }
}
