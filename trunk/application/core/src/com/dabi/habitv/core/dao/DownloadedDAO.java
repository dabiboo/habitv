package com.dabi.habitv.core.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.dabi.habitv.core.config.HabitTvConf;
import com.dabi.habitv.framework.plugin.exception.TechnicalException;
import com.dabi.habitv.utils.FileUtils;

public class DownloadedDAO {

	private static final Logger LOG = Logger.getLogger(DownloadedDAO.class);

	private final String indexDir;

	private final String category;

	private final String channelName;

	private boolean indexExist;

	public DownloadedDAO(final String channelName, final String category, final String indexDir) {
		super();
		this.indexDir = indexDir;
		this.category = category;
		this.channelName = channelName;
		final File indexDirectory = new File(indexDir);
		if (indexDirectory.exists()) {
			indexExist = new File(getFileIndex()).exists();
		} else {
			indexExist = false;
			if (!indexDirectory.mkdir()) {
				throw new TechnicalException("Folder can't be created" + indexDirectory.getAbsolutePath());
			}
		}
	}

	private String getFileIndex() {
		return (indexDir + "/" + FileUtils.sanitizeFilename(channelName + "_" + getCategory() + ".index"));
	}

	public Set<String> findDownloadedFiles() {

		BufferedReader lecteurAvecBuffer = null;
		String ligne;

		final Set<String> fileList = new HashSet<>();
		try {

			lecteurAvecBuffer = new BufferedReader(new InputStreamReader(new FileInputStream(getFileIndex()), HabitTvConf.ENCODING));
			while ((ligne = lecteurAvecBuffer.readLine()) != null) {
				fileList.add(ligne);
			}
		} catch (final FileNotFoundException exc) {
			// will return null
			LOG.debug("", exc);
		} catch (final IOException e) {
			throw new TechnicalException(e);
		} finally {
			if (lecteurAvecBuffer != null) {
				try {
					lecteurAvecBuffer.close();
				} catch (final IOException e) {
					throw new TechnicalException(e);
				}
			}
		}
		return fileList;
	}

	public synchronized void addDownloadedFiles(final String... files) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(getFileIndex(), true), HabitTvConf.ENCODING));
			for (final String file : files) {
				writer.println(file);
			}
			writer.close();
		} catch (final IOException e) {
			throw new TechnicalException(e);
		}
	}

	public boolean isIndexCreated() {
		return indexExist;
	}

	void initIndex() {
		final String fileIndex = getFileIndex();
		(new File(fileIndex)).delete();
		LOG.info("réinitialisation de l'index " + fileIndex);
		indexExist = false;
	}

	protected String getCategory() {
		return category;
	}
}
