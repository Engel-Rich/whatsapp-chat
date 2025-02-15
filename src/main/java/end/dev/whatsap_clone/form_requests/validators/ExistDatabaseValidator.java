package end.dev.whatsap_clone.form_requests.validators;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistDatabaseValidator implements ConstraintValidator<ExistDatabase,Integer> {

    private  Class<?> entity;
    private  boolean required;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistDatabase constraintAnnotation) {
        this.entity = constraintAnnotation.entity();
        this.required = constraintAnnotation.required();
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        if (id==null) {
            return !required;
        }

        try {
            return entityManager.find(entity,id)!=null;
        }
        catch (Exception e){
            return  false;
        }

    }
}
