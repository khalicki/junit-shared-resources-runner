package org.eanlr.testing.sharedresources;

import java.lang.annotation.*;
import java.util.HashMap;
import java.util.Map;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class JUnitSharedResourceRunner extends BlockJUnit4ClassRunner {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    public @interface WithSharedResources {
        Class<?>[] value();
    }

    private static Map<Class<SharedResource>, SharedResource> resourceMap = new HashMap<Class<SharedResource>, SharedResource>();

    public JUnitSharedResourceRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
        Class<?>[] classes = getSharedResourceClasses(clazz);
        initializeResources(classes);
    }

    @SuppressWarnings("unchecked")
    private void initializeResources(Class<?>[] classes) throws InitializationError {
        //System.out.println("Initializing resources with classes");
        for(Class<?> annotationClass : classes) {

            Class<SharedResource> sharedResourceClass = (Class<SharedResource>) annotationClass;
            SharedResource instance = resourceMap.get(sharedResourceClass);
            if(instance == null) {
                instance = createSharedResource(annotationClass, sharedResourceClass);
                resourceMap.put(sharedResourceClass, instance);
            }

        }
    }

    private SharedResource createSharedResource(Class<?> annotationClass, Class<SharedResource> sharedResourceClass) throws InitializationError {
        try {
            SharedResource sharedResource = (SharedResource) sharedResourceClass.newInstance();
            sharedResource.initialize();
            return sharedResource;
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new InitializationError("Unable to create shared resource '" + annotationClass.getName() + "'. Constructor failed.");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new InitializationError("Unable to create shared resource '" + annotationClass.getName() + "'. Constructor should be public.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitializationError("Unable to create shared resource '" + annotationClass.getName() + "'. Initialize method failed: " + e.getMessage());
        }
    }

    private Class<?>[] getSharedResourceClasses(Class<?> clazz) throws InitializationError {
        WithSharedResources annotation = clazz.getAnnotation(WithSharedResources.class);
        if (annotation == null)
            throw new InitializationError(
                    "Missing @WithSharedResources annotation for unit test '" + clazz.getName()
                            + "'");
        return annotation.value();
    }

    // retrive shared resource by class
    public static <T extends SharedResource> T getResource(Class<T> sharedResourceClass) {
        return (T) resourceMap.get(sharedResourceClass);
    }

}
