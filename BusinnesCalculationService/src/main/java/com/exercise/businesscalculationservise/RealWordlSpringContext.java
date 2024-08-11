package com.exercise.businesscalculationservise;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.exercise.businesscalculationservise")
public class RealWordlSpringContext {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(RealWordlSpringContext.class)) {
            System.out.println(context.getBean(BusinesscalculationserviseApplication.class).FindMax());
        }//

    }
}
