package urlshortener.modelview;

/**
 * This interface represents the URL storage method.
 */
public interface UrlStorage {
    String findLongUrlByShortUrl(String shortUrl);

    String storeUrl(String longUrl);
}
