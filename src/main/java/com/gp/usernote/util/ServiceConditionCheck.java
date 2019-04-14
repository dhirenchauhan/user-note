package com.gp.usernote.util;

import javax.persistence.EntityNotFoundException;

/**
 * Class : ServiceConditionCheck
 * Purpose : This is a utility class to Provide Entity Check
 * @author dhiren
 *
 */

public class ServiceConditionCheck {
	private ServiceConditionCheck() {
	}

	public static <T> T checkEntityExists(final T entity) {
		return checkEntityExists(entity, null);
	}

	public static <T> T checkEntityExists(final T entity, String message) {
		if (entity == null) {
			throw new EntityNotFoundException(message);
		}
		return entity;
	}

	public static void checkEntityExists(final boolean entityExists) {
		if (!entityExists) {
			throw new EntityNotFoundException();
		}
	}

	public static void checkOkArgument(final boolean okArgument) {
		if (!okArgument) {
			throw new EntityNotFoundException();
		}
	}
}
