package org.jboss.weld.tests.jsf.jsfejb;

import java.io.Serializable;


public class StoreConfig implements Serializable {

    private String id;

    public StoreConfig() {
    }

    public StoreConfig(String id) {
        this.id = id;
    }

     public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
