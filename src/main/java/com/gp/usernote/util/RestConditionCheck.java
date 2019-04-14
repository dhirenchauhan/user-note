package com.gp.usernote.util;

import com.gp.usernote.exception.BadRequestException;
import com.gp.usernote.exception.ResourceNotFoundException;

import static java.util.Objects.isNull;

/**
 * Class : RestConditionCheck
 * Purpose : This is a utility class to support Rest Pre condition check
 * @author dhiren
 *
 */

public final class RestConditionCheck {
	private RestConditionCheck() {
	}

	public static <T> T checkNotNull(final T reference) {
		return checkNotNull(reference, null);
	}

	public static <T> T checkNotNull(final T reference, final String message) {
		if (isNull(reference)) {
			throw new ResourceNotFoundException(message);
		}

		return reference;
	}

	public static <T> T checkRequestElementNotNull(final T reference) {
		return checkNotNull(reference, null);
	}

	public static <T> T checkRequestElementNotNull(final T reference, final String message) {
		if (isNull(reference)) {
			throw new BadRequestException(message);
		}

		return reference;
	}
}

