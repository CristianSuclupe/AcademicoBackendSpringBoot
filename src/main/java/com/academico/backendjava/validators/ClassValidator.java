package com.academico.backendjava.validators;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.academico.backendjava.entities.Class;
import com.academico.backendjava.exceptions.HttpException;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ClassValidator {

    public void validateMaxStudents(Class class1) {
        if(class1.getMaximunCapacity() <= class1.getCurrentAmount()) throw new HttpException(HttpStatus.BAD_REQUEST, "Esta clase no permite más estudientes");
    }

    public void validateInscriptionDate(Class class1) {
        Date today = new Date();
        if(class1.getDeadLine().before(today)) throw new HttpException(HttpStatus.BAD_REQUEST, "La fecha de inscripción ya expiró");
    }

}
