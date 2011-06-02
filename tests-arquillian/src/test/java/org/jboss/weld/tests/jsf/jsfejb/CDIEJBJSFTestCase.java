package org.jboss.weld.tests.jsf.jsfejb;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.weld.tests.jsf.JsfTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

/**
 * Tests that EJB beans can use CDI injection with JSF
 *
 * @author <a href="mailto:cerar@parsek.net">Tomaz Cerar</a>
 */
@RunWith(Arquillian.class)
public class CDIEJBJSFTestCase {
    //private static final Logger log = LoggerFactory.getLogger(CDIInjectionIntoEJBTestCase.class);

    @Deployment
    public static Archive<?> deploy() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "cdiejbjsf.war");

        JavaArchive jar = ShrinkWrap.create(JavaArchive.class,"ejbbeans.jar");
        jar.addPackage(CDIEJBJSFTestCase.class.getPackage());

        //war.addManifestResource(new StringAsset(""), "beans.xml");

        jar.addManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        jar.addManifestResource(new StringAsset("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<ejb-jar xmlns=\"http://java.sun.com/xml/ns/javaee\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/ejb-jar_3_1.xsd\"\n" +
                "         version=\"3.1\">\n" +
                "</ejb-jar>"), "ejb-jar.xml");
        war.addLibrary(jar);
        war.addWebResource(EmptyAsset.INSTANCE, "beans.xml")
                .addWebResource(JsfTest.class.getPackage().getName() + "/faces-config.xml")
                .setWebXML(JsfTest.class.getPackage().getName() + "/web.xml");
        //war.as(ZipExporter.class).exportZip(new File("target/cdiejbjsf.war"), true);

        return war;
    }

    @Inject
    private Store store;


    @Test
    public void testCDIInjectionIntoEJB() {
        Assert.assertEquals("test", store.getStoreConfig().getId());

    }
}
