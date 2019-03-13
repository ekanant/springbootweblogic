package com.demo.springbootweblogic.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import com.demo.springbootweblogic.util.Util;

/**
 * Servlet Filter implementation class TestFilter
 */
// Declare by annotation
// @WebFilter(urlPatterns = "/mvc/*")
public class LocaleFilter implements Filter {

    private static final String SESSION_LOCALE = "SESSION_LOCALE";
    private static final String DEFAULT_LOCALE = "th-TH";
    private static final HashMap<String, Boolean> LOCALES = new HashMap() {
        {
            put("th-TH", true);
            put("en-TH", true);
        }
    };

    @Override
    public void destroy() {

    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;

        String locale = Util.getStr(request.getParameter("locale"));
        if (locale.isEmpty()) {
            locale = Util.getStr(request.getSession().getAttribute(SESSION_LOCALE));
        }
        if (locale.isEmpty() || !isLocaleValid(locale)) {
            locale = DEFAULT_LOCALE;
        }

        request.getSession().setAttribute(SESSION_LOCALE, locale);
        Config.set(request.getSession(), Config.FMT_LOCALE, Locale.forLanguageTag(locale));

        chain.doFilter(request, response);
    }

    private static boolean isLocaleValid(String locale) {
        return LOCALES.containsKey(locale);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
