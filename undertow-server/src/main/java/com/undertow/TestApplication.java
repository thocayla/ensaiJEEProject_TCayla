package com.undertow;

/**
 * Created by thomas on 09/11/16.
 */
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class TestApplication extends Application {


    @Override
    public Set<Class<?>> getClasses() {

        final Set<Class<?>> clazzes = new HashSet<Class<?>>();

        clazzes.add(StatusEndpoint.class);

        return clazzes;
    }

}
