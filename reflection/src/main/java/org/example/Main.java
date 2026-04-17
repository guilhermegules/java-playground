package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    static void main() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Object obj = new Car("Civic", "black");

        System.out.println(obj);

        // From object
        Class<?> c1 = obj.getClass();
        System.out.println(c1);
        System.out.println(c1.getName());
        System.out.println(c1.getModifiers());

        Field field = c1.getDeclaredField("name");
        field.setAccessible(true); // allow private access

        Car c = new Car("Chevette", "blue");
        field.set(c, "Camaro");

        System.out.println(field.get(c));

        Method method = c1.getMethod("getName");

        System.out.println(method.invoke(c));

        Constructor<?> constructor = c1.getConstructor();
        Car cc = (Car) constructor.newInstance();

        System.out.println(cc);

        // From class literal
        Class<?> c2 = Car.class;
        System.out.println(c2);

        // From name (dynamic!)
        Class<?> c3 = Class.forName("org.example.Car");
        System.out.println(c3);
    }
}
