package com.oorni.common;

public class OorniException extends Exception {
	private static final long serialVersionUID = 1L;
	
		/**
		 * Default Constructor.
		 */
		public OorniException() {
			super();
		}

		/**
		 * <p>
		 * Constructor with one Argument.
		 * </p>
		 * 
		 * @param msg - a String Value - Exception message
		 */
		public OorniException(final String msg) {
			super(msg);
		}

		/**
		 * Constructor with two Argument.
		 * 
		 * @param msg - a String Value - Exception message
		 * @param cause -Throwable object
		 */
		public OorniException(final String msg, final Throwable cause) {
			super(msg, cause);
		}

		/**
		 * Constructor with one Argument.
		 * 
		 * @param cause - Throwable object
		 */
		public OorniException(final Throwable cause) {
			super(cause);
		}

}
