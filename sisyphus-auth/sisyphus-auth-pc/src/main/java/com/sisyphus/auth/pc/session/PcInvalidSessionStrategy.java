package com.sisyphus.auth.pc.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 19:04
 */
public class PcInvalidSessionStrategy
        extends AbstractSessionStrategy implements InvalidSessionStrategy {
    /**
     * @param invalidSessionUrl
     */
    public PcInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        onSessionInvalid(request, response);
    }

    @Override
    protected boolean isConcurrency() {
        return false;
    }
}
