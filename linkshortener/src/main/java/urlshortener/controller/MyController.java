package urlshortener.controller;

import urlshortener.modelview.UrlStorage;
import urlshortener.controller.request.ShortenUrlRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The controller of Spring MVC program. It processes the input URL and map the HTTP request to
 * the appropriate method. Overall, it will output the shortened URL and make sure that the produced
 * URL can redirect to the original site.
 */
@Controller
public class MyController {
    @Autowired
    private UrlStorage urlStorage;

    /**
     * Map the method to the HTTP GET request from the url "/" .
     * @param request the request from the user to shorten the input URL
     * @return
     */
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String getInputData(ShortenUrlRequest request) {
        return "shortener";
    }

    /**
     * Map the method to the HTTP GET request from the shortened URL produced in order to achieve redirection.
     * @param shortUrl the shortened URL produced
     * @param response HTTP-specific servlet response
     * @throws Exception if the original URL is unavailable (Status code 404)
     */
    @RequestMapping(value="/{shortUrl}", method=RequestMethod.GET)
    public void redirectUrl(@PathVariable String shortUrl, HttpServletResponse response) throws Exception {
        String longUrl = urlStorage.findLongUrlByShortUrl(shortUrl);
        if (longUrl != null) {
            response.addHeader("Location", longUrl);
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * Map the method to process POST request from the site and generate the shortened link.
     * @param hReq the HTTP request
     * @param req the shortening-URL request object marked with @Valid to gather the attributes in the filled out space
     * @param res for testing and retrieve validation errors
     * @return
     */
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ModelAndView linkShortener (HttpServletRequest hReq, @Valid ShortenUrlRequest req, BindingResult res) {
        String longUrl = req.getLongUrl();
        if (!validate(longUrl)) {
            res.addError(new ObjectError("longUrl", "Invalid input format: " + longUrl));
        }
        ModelAndView mav = new ModelAndView("shortener");
        if (!res.hasErrors()) {
            String shortUrl = urlStorage.storeUrl(longUrl);
            String reqUrl = hReq.getRequestURL().toString();
            String protocolPrefix = reqUrl.substring(0,reqUrl.indexOf(hReq.getRequestURI(), "http://".length()));
            mav.addObject("shortenedUrl", protocolPrefix + "/" + shortUrl);
        }

        return mav;
    }

    /**
     * Check if the URL is a valid URL by importing it into the URL class of Java
     * @param url the URL string
     * @return true if the URL is valid, false otherwise
     */
    private boolean validate(String url) {
        boolean valid = true;
        try {
            new URL(url);
        } catch (MalformedURLException mue) {
            valid = false;
        }
        return valid;

    }





}
