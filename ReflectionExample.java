
/*In this example, we use reflection to create a new instance of the 
    Person class, call its setName method to set its name to "Alice", 
    and use reflection to set its age to 30 by accessing the private 
    age field. We then use reflection to call the getName method to 
    get the person's name and access the private age field to get 
    the person's age. */

import java.lang.reflect.*;

public class ReflectionExample {

    public static void main(String[] args) {
        try {
            // get the class object for the Person class
            Class<?> personClass = Class.forName("Person");

            // create a new instance of the Person class using reflection
            Object person = personClass.newInstance();

            // get a reference to the setName method and invoke it on the person object
            Method setNameMethod = personClass.getMethod("setName", String.class);
            setNameMethod.invoke(person, "Alice");

            // get a reference to the age field and set it to 30
            Field ageField = personClass.getDeclaredField("age");
            ageField.setAccessible(true);
            ageField.setInt(person, 30);

            // print out the name and age of the person object using reflection
            Method getNameMethod = personClass.getMethod("getName");
            String name = (String) getNameMethod.invoke(person);
            int age = ageField.getInt(person);
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

class Person {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }
}
