package Objects;

import java.io.Serializable;

/**
 * Created by Tadas on 7/4/2014.
 */
public class Artists implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5657932452682261115L;
	private String artistId;
	private String title;
	private String company;
	private String country;
	private String city;
	private String image;
	private String description_lt;
	private String description_en;
	private int position;
	private int importance;
	private int selected;
	private int positionAdapter;

	private String ends;
	private String starts;
	private String stageTitle;

	public String getEnds() {
		return ends;
	}

	public void setEnds(String ends) {
		this.ends = ends;
	}

	public String getStarts() {
		return starts;
	}

	public void setStarts(String starts) {
		this.starts = starts;
	}

	public String getStageTitle() {
		return stageTitle;
	}

	public void setStageTitle(String stageTitle) {
		this.stageTitle = stageTitle;
	}

	public int getPositionAdapter() {
		return positionAdapter;
	}

	public void setPositionAdapter(int positionAdapter) {
		this.positionAdapter = positionAdapter;
	}

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

	public String getDescription_lt() {
		return description_lt;
	}

	public void setDescription_lt(String description_lt) {
		this.description_lt = description_lt;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}
}
