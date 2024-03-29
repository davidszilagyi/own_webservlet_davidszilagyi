package src.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.reflections.Reflections;
import src.annotation.WebRoute;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by David Szilagyi on 2017. 06. 13..
 */
public class MainHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        Reflections ref = new Reflections("src.routes");
        Set<Class<?>> annotated = ref.getTypesAnnotatedWith(WebRoute.class);
        for (Class<?> cls : annotated) {
            Annotation annotation = cls.getAnnotation(WebRoute.class);
            if (annotation instanceof WebRoute && ((WebRoute) (annotation)).path().equals(t.getRequestURI().getPath())) {
                Method[] methods = cls.getMethods();
                for(Method method: methods) {
                    if(method.getAnnotation(WebRoute.class).method().equals(t.getRequestMethod())) {
                        try {
                            method.invoke(cls.newInstance(), t);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            }
        }
        String response = "\nThis is the main route";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}