package com.ef.parser.util.validators;

import org.apache.commons.validator.routines.InetAddressValidator;

public class IpValidator {
	private IpValidator() {
		// Pure utility class, do NOT instantiate.
	}
	
	public static boolean isIpValid(String ip) {
		InetAddressValidator ipv4IpValidator = InetAddressValidator.getInstance();
		return ipv4IpValidator.isValid(ip);
	}
}
