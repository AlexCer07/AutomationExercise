package Listeners;

import AbstractElements.AdHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class AdListener implements WebDriverListener {

    private final AdHandler adHandler;

    public AdListener(AdHandler adHandler) {
        this.adHandler = adHandler;
    }

    @Override
    public void beforeClick(WebElement element) {
        adHandler.closeAdsIfPresent();
    }

    @Override
    public void afterClick(WebElement element) {
        adHandler.closeAdsAfterClick();
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        adHandler.closeAdsIfPresent();
    }
}