package org.jboss.weld.tests.jsf.jsfejb;

import javax.ejb.Local;
import java.io.Serializable;

@Local
public interface SecurityManager extends Serializable {


    StoreConfig getCurrentStore();


}
