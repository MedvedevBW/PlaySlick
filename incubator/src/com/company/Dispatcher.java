package com.company;

import com.company.annotations.Path;
import com.company.annotations.PathVariable;
import com.company.controller.UniversalController;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dispatcher {

    private Components components = new Components();

    public String handle(String request) {
        String result;
        try {
            result = getRelativeAnnotatedMethod(components.getAuthorController(), request);
        } catch (Exception e) {
            System.out.println(e);
            result = "INTERNAL SERVER ERROR";
        }

        return result;
    }

    private String getRelativeAnnotatedMethod(UniversalController controller, String request) throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException  {
        Class<?> klass = controller.getClass();

        final List<Method> allMethods = new ArrayList<Method>(Arrays.asList(klass.getDeclaredMethods()));
        for (final Method method : allMethods) {
            if (method.isAnnotationPresent(Path.class)) {
                Annotation pathAnnotation = method.getAnnotation(Path.class);

                String annotationPathValue = ((Path) pathAnnotation).value();

                Parameter[] parameters = method.getParameters();

                for (Parameter p : parameters) {
                    if (p.isAnnotationPresent(PathVariable.class)) {
                        Annotation pathVariableAnnotation = p.getAnnotation(PathVariable.class);

                        String annotationParameterValue = ((PathVariable) pathVariableAnnotation).value();

                        String before = annotationPathValue.substring(0, annotationPathValue.indexOf("{" + annotationParameterValue + "}"));

                        if (request.startsWith(before)) {
                            return (String) method.invoke(controller, Integer.valueOf(request.substring(before.length(), request.length())));
                        }
                    }
                }

            }
        }

        return "NOT FOUND";
    }
}
