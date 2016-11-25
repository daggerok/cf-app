package daggerok.config;

import lombok.SneakyThrows;
import lombok.val;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class CsrfHeaderFilter extends OncePerRequestFilter {

    static final String XSRF_TOKEN = "XSRF-TOKEN";

    @Override
    @SneakyThrows
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) {

        val csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        if (nonNull(csrf)) {

            val cookie = WebUtils.getCookie(request, XSRF_TOKEN);
            val token = csrf.getToken();

            if (isNull(cookie) || nonNull(token) && !token.equals(cookie.getValue())) {

                val cookieWithToken = new Cookie(XSRF_TOKEN, token);

                cookieWithToken.setPath("/");
                response.addCookie(cookieWithToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
