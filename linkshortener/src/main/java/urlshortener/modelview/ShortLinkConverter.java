package urlshortener.modelview;

/**
 * This class serves as a converter to turn the url into a distinct shortened URL.
 * It does this by converting the link_id number and convert it to base 62, and
 * use the Character map to determine the string of the shortened URL.
 * This class contains a Character Map and a base length of the character.
 */
public class ShortLinkConverter {
    private static final String CHARMAP = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = CHARMAP.length();

    /**
     * Convert the index to the string URL.
     * @param link_id the index to be converted
     * @return The string representing the shortened index
     */
    public static String idToUrl(int link_id) {
        StringBuilder sb = new StringBuilder();

        while (link_id > 0) {
            int rem = link_id & BASE;
            sb.append(CHARMAP.charAt(rem));

            link_id = link_id/BASE;
        }

        return sb.reverse().toString();
    }


    /**
     * Convert the string URL to the index.
     * @param short_url the shortened URL to be converted
     * @return the index of the long URL in the storage
     */
    public static int shortUrlToId(String short_url) {
        int link_id = 0;

        for (char c : short_url.toCharArray()) {
            int index = CHARMAP.indexOf(c);
            link_id = link_id * BASE + index;
        }

        return link_id;
    }
}
