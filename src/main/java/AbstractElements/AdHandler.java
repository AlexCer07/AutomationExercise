package AbstractElements;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class AdHandler {

    private WebDriver driver;

    public AdHandler(WebDriver driver) {
        this.driver = driver;
    }

    public void closeAdsIfPresent() {
        removeVignetteAd();
        removeAnchorAd();
        removeSideRailAds();
    }

    // -------------------------------------------------------
    // Vignette: pantalla completa — se elimina directo del DOM
    // El botón "Close" está en un iframe cross-origin inaccesible,
    // así que removemos el elemento contenedor desde el documento principal
    // -------------------------------------------------------
    private void removeVignetteAd() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("ins[data-vignette-loaded='true']")
            ));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            Long removed = (Long) js.executeScript(
                    "var ads = document.querySelectorAll(\"ins[data-vignette-loaded='true']\");" +
                            "ads.forEach(el => el.remove());" +
                            "return ads.length;"
            );

            if (removed != null && removed > 0) {
                System.out.println("✅ Vignette ad eliminado del DOM (" + removed + " elemento/s).");
            }

        } catch (TimeoutException e) {
            // No apareció vignette, continuar normalmente
        } catch (Exception e) {
            System.out.println("ℹ️ removeVignetteAd: " + e.getMessage());
        }
    }

    // -------------------------------------------------------
    // Anchor: banner fijo en la parte inferior
    // -------------------------------------------------------
    private void removeAnchorAd() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "document.querySelectorAll('ins[data-anchor-status]')" +
                            ".forEach(el => el.remove());"
            );
            System.out.println("✅ Anchor ad eliminado.");
        } catch (Exception ignored) {}
    }

    // -------------------------------------------------------
    // Side Rails: anuncios laterales izquierdo y derecho
    // NUEVO: aparecen en este HTML con class *-side-rail-dismiss-btn
    // -------------------------------------------------------
    private void removeSideRailAds() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "document.querySelectorAll('ins[data-side-rail-status]')" +
                            ".forEach(el => el.remove());"
            );
            System.out.println("✅ Side rail ads eliminados.");
        } catch (Exception ignored) {}
    }
}