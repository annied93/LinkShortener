package urlshortener.modelview;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is the class that implements the Storage Interface.
 * This class contains the Map to store the URLs obtained, and a counter to determine the
 * index of the stored URL in the map.
 */
@Service
public class UrlStorageImpl implements UrlStorage{
    private Map<Integer, String> urlMap = new ConcurrentHashMap<>();
    private int counter = 0;

    /**
     * Find long URL by the short URL string
     * @param shortUrl the short URL input
     * @return the string representing the original long URL
     */
    @Override
    public String findLongUrlByShortUrl(String shortUrl) {
        int id = ShortLinkConverter.shortUrlToId(shortUrl);
        return urlMap.get(id);
    }

    /**
     * Store the long URL and return the shortened URL
     * @param longUrl the long URL to store
     * @return the shortened URL
     */
    @Override
    public String storeUrl(String longUrl) {
        counter++;
        urlMap.put(counter, longUrl);
        return ShortLinkConverter.idToUrl(counter);
    }
}
