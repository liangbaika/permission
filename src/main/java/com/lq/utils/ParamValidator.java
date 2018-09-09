package com.lq.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lq.exception.ParamException;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * 校验参数
 */
public class ParamValidator {

    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    private static <T> Map<String, String> validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> result = validator.validate(t, groups);
        if (result.isEmpty()) {
            return Collections.emptyMap();
        } else {
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator<ConstraintViolation<T>> iterator = result.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> validation = iterator.next();
                errors.put(validation.getPropertyPath().toString(), validation.getMessage());
            }
            return errors;
        }
    }

    private static Map<String, String> validateList(Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        Iterator<?> iterator = collection.iterator();
        Map errors = null;
        do {
            if (!iterator.hasNext()) {
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object, new Class[0]);
        } while (errors.isEmpty());
        return errors;
    }

    private static Map<String, String> validateObj(Object first, Object... objs) {
        if (objs != null && objs.length > 0) {
            return validateList(Lists.asList(first, objs));
        } else {
            return validate(first, new Class[0]);
        }
    }

    public static void check(Object param) {
        Map<String, String> res = validateObj(param);
        if (!CollectionUtils.isEmpty(res)) {
            throw new ParamException(res.toString());
        }
    }
}
