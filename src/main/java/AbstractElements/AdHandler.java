package AbstractElements;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AdHandler extends AbstractElements {

    private final WebDriver driver;
    private final JavascriptExecutor js;

    public AdHandler(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public void closeAdsIfPresent() {
        removeVignetteAd();
        removeAnchorAd();
        removeSideRailAds();
    }

    public void closeAdsAfterClick() {

        if (waitForPossibleAlert()) {
            log.debug("Alerta detectada después del clic." + "Se omite limpieza de anuncios.");
            return;
        }
        closeAdsIfPresent();
    }

    private boolean waitForPossibleAlert() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    // -------------------------------------------------------
    // Vignette
    // -------------------------------------------------------
    private void removeVignetteAd() {

        try {
            Long removed = (Long) js.executeScript(
                    """
                    const selectors = [
                        "ins[data-vignette-loaded='true']",
                        "ins.adsbygoogle[data-vignette-loaded]",
                        "div[id^='google_ads_iframe']",
                        "iframe[id^='google_ads_iframe']",
                        "div[id^='aswift_']"
                    ];

                    let removed = 0;

                    selectors.forEach(selector => {

                        document.querySelectorAll(selector)
                            .forEach(element => {

                                element.remove();
                                removed++;

                            });

                    });

                    return removed;
                    """
            );

            /*
             * Google Vignette puede dejar el hash:
             * #google_vignette
             * aunque el anuncio ya haya sido eliminado.
             */
            String url = driver.getCurrentUrl();

            assert url != null;
            if (url.contains("#google_vignette")) {

                js.executeScript(
                        """
                        history.replaceState(
                            null,
                            '',
                            window.location.pathname +
                            window.location.search
                        );
                        """
                );

                log.debug("Hash #google_vignette eliminado.");
            }

            /*
             * Algunos anuncios bloquean el scroll modificando
             * estilos del body/html.
             */
            js.executeScript(
                    """
                    document.documentElement.style.overflow = '';
                    document.body.style.overflow = '';
                    document.body.style.position = '';
                    """
            );

            if (removed != null && removed > 0) {
                log.debug("Vignette eliminado ({} elemento/s).", removed);
            }

        } catch (Exception e) {
            log.debug("removeVignetteAd: {}", e.getMessage());
        }
    }

    // -------------------------------------------------------
    // Anchor ads
    // -------------------------------------------------------
    private void removeAnchorAd() {

        try {
            Long removed = (Long) js.executeScript(
                    """
                    const ads =
                        document.querySelectorAll(
                            "ins[data-anchor-status]"
                        );

                    const count = ads.length;

                    ads.forEach(element => element.remove());

                    return count;
                    """
            );

            if (removed != null && removed > 0) {
                log.debug("Anchor ad eliminado ({} elemento/s).", removed);
            }

        } catch (Exception e) {
            log.debug("removeAnchorAd: {}", e.getMessage());
        }
    }

    // -------------------------------------------------------
    // Side rail ads
    // -------------------------------------------------------
    private void removeSideRailAds() {

        try {
            Long removed = (Long) js.executeScript(
                    """
                    const ads =
                        document.querySelectorAll(
                            "ins[data-side-rail-status]"
                        );

                    const count = ads.length;

                    ads.forEach(element => element.remove());

                    return count;
                    """
            );

            if (removed != null && removed > 0) {
                log.debug("Side rail ads eliminados ({} elemento/s).", removed);
            }

        } catch (Exception e) {
            log.debug("removeSideRailAds: {}", e.getMessage());
        }
    }
}
