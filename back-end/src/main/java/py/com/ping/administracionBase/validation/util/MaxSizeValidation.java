/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.administracionBase.validation.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import py.com.ping.utilitarios.interfaces.AbstractControlerValidationGroups;

/*
 * Implementacion de la interface @interface NotNullString 
 * @author gdb <dominbla@gmail.com>
 */


public class MaxSizeValidation implements ConstraintValidator<MaxSize, Object> {

    String nombreCampo;
    int tamañoMaximo;
    
    @Override
    public void initialize(MaxSize constraintAnnotation) {
        this.nombreCampo = constraintAnnotation.campoNombre();
        this.tamañoMaximo = constraintAnnotation.tamañoMaximo();
    
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
        boolean isValid;
        isValid= object != null && object.toString().length()<=tamañoMaximo;
        if(!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate( "Campo "+nombreCampo+" supera el tamaño maximo permitido. Tamaño permitido es de:"+tamañoMaximo+" caracteres"  ).addConstraintViolation();
        }
        return isValid;
    }

    Class<AbstractControlerValidationGroups> groups(){
        return AbstractControlerValidationGroups.class;
    }
}
