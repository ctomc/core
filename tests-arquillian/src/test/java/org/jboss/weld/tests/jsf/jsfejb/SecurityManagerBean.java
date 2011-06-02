package org.jboss.weld.tests.jsf.jsfejb;


import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;


@Named("securityManager")
@Stateless(name = "securityManager", mappedName = "SecurityManagerBean")
public class SecurityManagerBean implements SecurityManager {


    @Produces
    @RequestScoped
    @Engine
    public StoreConfig getCurrentStore() {
        return new StoreConfig("test");
    }


}
