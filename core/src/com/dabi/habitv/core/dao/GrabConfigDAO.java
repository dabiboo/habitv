package com.dabi.habitv.core.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.dabi.habitv.core.config.HabitTvConf;
import com.dabi.habitv.framework.plugin.api.dto.CategoryDTO;
import com.dabi.habitv.framework.plugin.exception.TechnicalException;
import com.dabi.habitv.grabconfig.entities.Category;
import com.dabi.habitv.grabconfig.entities.Channel;
import com.dabi.habitv.grabconfig.entities.GrabConfig;
import com.dabi.habitv.grabconfig.entities.Parameter;
import com.dabi.habitv.utils.FileUtils;

public class GrabConfigDAO {

	private final String grabConfigFile;

	public GrabConfigDAO(final String grabConfigFile) {
		super();
		this.grabConfigFile = grabConfigFile;
	}

	public void saveGrabConfig(
			final Map<String, Set<CategoryDTO>> channel2Categories) {
		final GrabConfig config = new GrabConfig();
		addChannels(channel2Categories, config);
		marshal(config);
	}

	private void addChannels(
			final Map<String, Set<CategoryDTO>> channel2Categories,
			final GrabConfig config) {
		for (final Entry<String, Set<CategoryDTO>> entry : channel2Categories
				.entrySet()) {
			final Channel channel = new Channel();
			channel.setName(entry.getKey());
			channel.setStatus(StatusEnum.NEW.ordinal());
			for (final CategoryDTO categoryDTO : entry.getValue()) {
				if (categoryDTO.check()) {
					channel.getCategory().add(buildCategory(categoryDTO));
				}
			}
			config.getChannel().add(channel);
		}
	}

	private Category buildCategory(final CategoryDTO categoryDTO) {
		final Category category = new Category();
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setExtension(categoryDTO.getExtension());
		category.setToDownload(false);
		category.setStatus(StatusEnum.NEW.name());
		for (final String exclude : categoryDTO.getExclude()) {
			category.getExclude().add(exclude);
		}
		for (final String include : categoryDTO.getInclude()) {
			category.getInclude().add(include);
		}
		for (final CategoryDTO subCategoryDTO : categoryDTO.getSubCategories()) {
			category.getCategory().add(buildCategory(subCategoryDTO));
		}
		return category;
	}

	private void marshal(final GrabConfig config) {
		final JAXBContext jaxbContext;
		FileOutputStream outputFile = null;
		try {
			jaxbContext = JAXBContext.newInstance(GrabConfig.class.getPackage()
					.getName());
			final Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING,
					HabitTvConf.ENCODING);
			outputFile = new FileOutputStream(grabConfigFile);
			marshaller.marshal(config, outputFile);
		} catch (JAXBException | FileNotFoundException e) {
			throw new TechnicalException(e);
		} finally {
			if (outputFile != null) {
				try {
					outputFile.close();
				} catch (final IOException e) {
					throw new TechnicalException(e);
				}
			}
		}
	}

	private static Set<CategoryDTO> buildCategoryListDTO(
			final LoadModeEnum loadMode, final String channelName,
			final List<Category> categories) {
		final Set<CategoryDTO> categoryDTOs = new HashSet<>(categories.size());
		CategoryDTO categoryDTO;
		for (final Category category : categories) {
			final Set<CategoryDTO> subCategoriesDTO = buildCategoryListDTO(
					loadMode, channelName, category.getCategory());
			if (category.getToDownload() == null || category.getToDownload()
					|| !subCategoriesDTO.isEmpty()
					|| loadMode.equals(LoadModeEnum.ALL)) {
				categoryDTO = new CategoryDTO(channelName, category.getName(),
						category.getId(), category.getInclude(),
						category.getExclude(), category.getExtension());
				categoryDTO.addSubCategories(subCategoriesDTO);
				if (category.getParameter() != null
						&& !category.getParameter().isEmpty()) {
					for (final Parameter parameter : category.getParameter()) {
						categoryDTO.addParameter(parameter.getKey(),
								parameter.getValue());
					}
				}
				categoryDTOs.add(categoryDTO);
			}
		}
		return categoryDTOs;
	}

	private GrabConfig unmarshal() {
		GrabConfig grabConfig = null;
		try {
			final JAXBContext jaxbContext = JAXBContext
					.newInstance(GrabConfig.class.getPackage().getName());
			final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			FileUtils.setValidation(unmarshaller, HabitTvConf.GRAB_CONF_XSD);
			grabConfig = ((GrabConfig) unmarshaller
					.unmarshal(new InputStreamReader(new FileInputStream(
							grabConfigFile), HabitTvConf.ENCODING)));
		} catch (final JAXBException e) {
			throw new TechnicalException(e);
		} catch (final UnsupportedEncodingException e) {
			throw new TechnicalException(e);
		} catch (final FileNotFoundException e) {
			throw new TechnicalException(e);
		}
		return grabConfig;
	}

	private Map<String, Set<CategoryDTO>> buildCategoryDTO(
			final GrabConfig grabConfig, final LoadModeEnum loadMode) {
		final Map<String, Set<CategoryDTO>> channel2Category = new HashMap<>();
		for (final Channel channel : grabConfig.getChannel()) {
			final Set<CategoryDTO> buildCategoryListDTO = buildCategoryListDTO(
					loadMode, channel.getName(), channel.getCategory());
			if (!buildCategoryListDTO.isEmpty()) {
				channel2Category.put(channel.getName(), buildCategoryListDTO);
			}
		}
		return channel2Category;
	}

	enum LoadModeEnum {
		ALL, TO_DOWNLOAD_ONLY;
	}

	Map<String, Set<CategoryDTO>> load(final LoadModeEnum loadMode) {
		return buildCategoryDTO(unmarshal(), loadMode);
	}

	public Map<String, Set<CategoryDTO>> load() {
		return buildCategoryDTO(unmarshal(), LoadModeEnum.TO_DOWNLOAD_ONLY);
	}

	public boolean exist() {
		return (new File(grabConfigFile)).exists();
	}

	public void updateGrabConfig(
			final Map<String, Set<CategoryDTO>> channel2Categories) {
		final HashMap<String, Set<CategoryDTO>> channel2CategoriesTemp = new HashMap<>(
				channel2Categories);
		final GrabConfig grabConfig = unmarshal();
		StatusEnum channelStatus;
		for (final Channel channel : grabConfig.getChannel()) {
			final Set<CategoryDTO> categoryChannel = channel2CategoriesTemp
					.get(channel.getName());
			if (categoryChannel != null) {
				updateCategory(channel.getCategory(), categoryChannel);
				channel2CategoriesTemp.remove(channel.getName());
				channelStatus = StatusEnum.EXIST;
			} else {
				channelStatus = StatusEnum.DELETED;
			}
			channel.setStatus(channelStatus.ordinal());
		}
		addChannels(channel2CategoriesTemp, grabConfig);
		marshal(grabConfig);
	}

	private void updateCategory(final List<Category> categoryList,
			final Collection<CategoryDTO> categoryDTOList) {
		final Map<String, CategoryDTO> catNameToCat = new HashMap<>();
		for (final CategoryDTO categoryDTO : categoryDTOList) {
			catNameToCat.put(categoryDTO.getName(), categoryDTO);
		}
		StatusEnum statusEnum;
		for (final Category category : categoryList) {
			final CategoryDTO associatedCatDTO = catNameToCat.get(category
					.getName());
			if (associatedCatDTO != null) {
				catNameToCat.remove(category.getName());
				if (!category.getCategory().isEmpty()) {
					updateCategory(category.getCategory(),
							associatedCatDTO.getSubCategories());
				}
				statusEnum = StatusEnum.EXIST;
			} else {
				statusEnum = StatusEnum.DELETED;
			}
			category.setStatus(statusEnum.name());
		}
		for (final CategoryDTO categoryDTO : catNameToCat.values()) {
			categoryList.add(buildCategory(categoryDTO));
		}
	}
}
