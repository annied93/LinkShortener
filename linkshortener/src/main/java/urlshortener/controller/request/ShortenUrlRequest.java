package urlshortener.controller.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Create the form for taking in the long URL for processing. This form has one attribute - the URL string.
 */

public class ShortenUrlRequest {
    @NotNull
    @Size(min = 5)
    private String longUrl;

    /**
     * Get the long URL.
     * @return the string representing the long URL
     */
    public String getLongUrl() {
        return longUrl;
    }

    /**
     * Set the long URL.
     * @param longUrl the long URL to be set into the field of this object.
     */
    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

}
