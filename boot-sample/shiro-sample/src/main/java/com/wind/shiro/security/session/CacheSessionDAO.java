package com.wind.shiro.security.session;

import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheSessionDAO extends EnterpriseCacheSessionDAO{

    private Logger logger = LoggerFactory.getLogger(getClass());

    public CacheSessionDAO() {
        super();
    }


}
