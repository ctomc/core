package org.jboss.weld.tests.jsf.jsfejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;


@Stateless(name = "store", mappedName = "store")
public class Store implements Serializable {


    @Inject
    private SecurityManager securityManager;
    @Inject
    @Engine
    private StoreConfig storeConfig;

    public StoreConfig getStoreConfig() {
        return storeConfig;
    }
}
