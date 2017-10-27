package br.unisinos.desenvsoft3.service.generic.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ValidationContext<T> {

	private final T bean;
	private final List<Validation> validations = new ArrayList<>();
	
	
	public static <T> ValidationContext<T> forBean(T bean) {
		ValidationContext<T> context = new ValidationContext<>(bean);
		return context;
	}
	
	private ValidationContext(T bean) {
		this.bean = bean;
	}
	
	public ValidationContext<T> ifValid(Predicate<T> validation, String onError) {
		Validation validationObject = new Validation();
		validationObject.predicate = validation;
		validationObject.onErrorMessage = onError;
		validations.add(validationObject);
		return this;
	}
	
	public GenericResponse thenDo(Consumer<T> action) {
		for (Validation validation : validations) {
			if(!validation.test(bean)) {
				return GenericResponse.error(validation.onErrorMessage);
			}
		}
		
		action.accept(bean);
		return GenericResponse.ok();
	}
	
	private class Validation {
		Predicate<T> predicate;
		String onErrorMessage;
		
		boolean test(T bean) {
			return predicate.test(bean);
		}
	}
}
