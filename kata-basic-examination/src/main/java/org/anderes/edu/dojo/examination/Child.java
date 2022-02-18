package org.anderes.edu.dojo.examination;

public class Child implements Comparable<Child> {

    private final int age;
    private final String name;

    public Child(int age, String name) {
        super();
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Child other) {
        return this.age - other.age;
    }
}
