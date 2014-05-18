package com.dabi.habitv.api.plugin.exception;

/**
 * Telechargement echoue
 */
public class DownloadFailedException extends Exception {

	private static final long serialVersionUID = -7917171517194292179L;

	public DownloadFailedException(final Exception exception) {
		super(exception.getMessage(), exception);
	}

	public DownloadFailedException(final String message) {
		super(message);
	}

}
