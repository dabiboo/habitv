package com.dabi.habitv.core.task;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

public abstract class AbstractTask<R> implements Callable<R> {

	protected static final Logger LOG = Logger.getLogger(AbstractTask.class);

	private String category = null;

	@Override
	public final R call() {
		R result;
		try {
			started();
			result = doCall();
			ended();
		} catch (final Exception e) {
			failed(e);
			throw new TaskFailedException(e);
		}
		return result;
	}

	protected abstract void added();

	protected abstract void failed(Exception e);

	protected abstract void ended();

	protected abstract void started();

	protected abstract R doCall() throws Exception;

	public final void addedTo(final String category) {
		this.category = category;
		added();
	}

	public final String getCategory() {
		return category;
	}

}
