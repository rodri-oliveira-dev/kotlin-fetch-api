package br.com.rodrigo.annotation

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import jakarta.inject.Singleton
import javax.validation.Constraint

@MustBeDocumented
@Target(AnnotationTarget.FIELD, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [SenhaValidator::class])
annotation class ForcaSenha(val message: String = "Senha fraca")


@Singleton
class SenhaValidator : ConstraintValidator<ForcaSenha, String> {
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<ForcaSenha>,
        context: ConstraintValidatorContext
    ): Boolean {
        value?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(value) != null
        } ?: return false
    }

}