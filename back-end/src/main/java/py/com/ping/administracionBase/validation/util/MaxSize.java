/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.ping.administracionBase.validation.util;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import py.com.ping.utilitarios.interfaces.AbstractControlerValidationGroups;

/** 
 * .
 * 
 * Anotation que se encarga de validar que una cadena no sea null ni posea un caracter vacio.
 * Al mostrar el atributo de la clase en la vista (.xhtml) y si el mismo es del
 * tipo string al atributo se le setea la cadena vacia o sea '' y eso es almacenado en la BD.
 * Al setearle la cadena vacia el atributo deja de ser NULL es por eso que el Anotation 
 * Constraint @NotNull no valida y la fase de validacion jsf da como correcto cuando deberia ser invalido.
 * Este anotation fue creado para eso, para validar en los tipos String que no se guarde 
 * un valor null ni vacio.
 * 
 * @author gdb <dominbla@gmail.com>
 */
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = MaxSizeValidation.class)
@Documented
// Obs.: Podria haber utilizado el anotation @NotEmpty pero utilizare este para desarrollar un constraint propio.
public @interface MaxSize {

    String message() default "Supera el tamaño maximo permitido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String campoNombre();
    int tamañoMaximo();

}
