package com.exercise.businesscalculationservise;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class MangoDbService implements DataService {
    public int[] retrieveData() {
        return new int[] { 11, 22, 33, 44 };
    }

}