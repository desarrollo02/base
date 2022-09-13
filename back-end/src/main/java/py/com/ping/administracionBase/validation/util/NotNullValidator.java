/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.administracionBase.validation.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * Implementacion de la interface @interface NotNullString 
 * @author gdb <dominbla@gmail.com>
 */


public class NotNullValidator implements ConstraintValidator<NotNullString, String> {

    String nombreCampo;
    
    @Override
    public void initialize(NotNullString constraintAnnotation) {
        this.nombreCampo = constraintAnnotation.campoNombre();
    
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        boolean isValid;
        isValid= object != null && !object.isEmpty();
        if(!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate( "Campo "+nombreCampo+" es requerido."  ).addConstraintViolation();
        }
        return isValid;
    }

}
