package com.ef.parser.util.validators;

import com.ef.parser.domain.ParserOptions;

public final class ParserOptionsValidator {
	private ParserOptionsValidator() {
		// Pure utility class, do NOT instantiate.
	}
	
	private static boolean isValidDuration(final int duration) {
        boolean isDurationValid = false;
        
        if (duration == ParserOptions.DAILY || duration == ParserOptions.HOURLY) {
        	isDurationValid = true;
        }
        return isDurationValid;
    }
	
	private static boolean isValidThreshold(final int threshold, final int duration) {
        boolean isDurationValid = false;
        
        if (duration == ParserOptions.DAILY || duration == ParserOptions.HOURLY) {
        	isDurationValid = true;
        }
        return isDurationValid;
    }
	
	public static boolean isValid(
			final int duration,
			final int threshold) {
		boolean isParserOptionsValid = false;
		
		isParserOptionsValid = isValidDuration(duration) &&
							   isValidThreshold(threshold, duration);
		return isParserOptionsValid;
	}
}
