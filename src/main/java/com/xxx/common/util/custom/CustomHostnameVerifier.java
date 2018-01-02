package com.xxx.common.util.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by sks on 2018/1/2.
 */

public class CustomHostnameVerifier implements HostnameVerifier {

    private Logger logger = LoggerFactory.getLogger(CustomHostnameVerifier.class);

    public boolean verify(String hostname, SSLSession session) {
        logger.info("[verify]hostname:{}, peerHost:{}", hostname, session.getPeerHost());
        return hostname.equals(session.getPeerHost());
    }

}