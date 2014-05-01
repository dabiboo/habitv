package com.dabi.habitv.api.plugin.dto;

import java.io.Serializable;

import com.dabi.habitv.api.plugin.exception.InvalidEpisodeException;

public class EpisodeDTO implements Comparable<EpisodeDTO>, Serializable {

	private static final long serialVersionUID = 1042642346549389101L;

	private final CategoryDTO category;

	private final String name;

	private final String id;

	private int num = 0;

	public EpisodeDTO(final CategoryDTO category, final String name, final String id) {
		this.category = category;
		this.name = name;
		this.id = id;
	}

	public void check() throws InvalidEpisodeException {
		if (!checkMinSize(name)) {
			throw new InvalidEpisodeException(name, InvalidEpisodeException.CauseField.NAME);
		}
		if (!checkMinSize(id)) {
			throw new InvalidEpisodeException(id, InvalidEpisodeException.CauseField.URL);
		}
	}

	private static boolean checkMinSize(final String category) {
		return category != null && category.length() > 0;
	}

	public String getName() {
		return name;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	@Override
	public int hashCode() {
		int ret;
		if (this.id != null) {
			ret = this.id.hashCode();
		} else {
			ret = category.hashCode() + name.hashCode();
		}
		return ret;
	}

	@Override
	public boolean equals(final Object obj) {
		boolean ret = false;
		if (obj instanceof EpisodeDTO) {
			final EpisodeDTO episode = (EpisodeDTO) obj;
			if (this.id != null && this.id.equals(episode.id)) {
				ret = true;
			} else {
				ret = category.equals(episode.getCategory()) && episode.getName().equals(name);
			}
		}
		return ret;
	}

	@Override
	public String toString() {
		return getFullName();
	}

	public String getFullName() {
		return getCategory().getChannel() + "-" + getCategory().getName() + "-" + getName() + "-"+getNum();
	}

	public String getId() {
		return id;
	}

	@Override
	public int compareTo(final EpisodeDTO o) {
		int ret = 0;
		if (!equals(o)) {
			ret = category.compareTo(o.category);
			if (ret == 0) {
				ret = name.compareTo(o.name);
			}
		}
		return ret;
	}

	public int getNum() {
		return num;
	}

	public void setNum(final int i) {
		this.num = i;
	}

}
