package org.example;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Audience {
    String name;
    int age;
    boolean isCinemaCitizen;

    @Override
    public String toString() {
            return "Audience{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", isCinemaCitizen=" + isCinemaCitizen +
                    '}';
    }
}
