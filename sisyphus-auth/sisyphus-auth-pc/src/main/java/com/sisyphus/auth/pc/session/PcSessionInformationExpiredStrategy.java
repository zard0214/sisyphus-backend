package com.sisyphus.auth.pc.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

/**
 * @author zhecheng.zhao
 * @date Created in 08/06/2021 19:04
 */
public class PcSessionInformationExpiredStrategy
        extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {
    /**
     * @param invalidSessionUrl
     */
    public PcSessionInformationExpiredStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
