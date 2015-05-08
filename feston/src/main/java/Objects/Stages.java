package Objects;

import java.io.Serializable;

public class Stages implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int stageid;
	private String title;
	private String subtitle;
	private String description_lt;
	private String description_en;
	private String address;
	private String latitude;
	private String longitude;
	private String image;

	private String stagename;
	private String artisttitle;
	private String scheduletitle;
	private String artistimage;
	private String start;
	private String end;
	private String artistid;
	private int artistSelected;
	private int positionAdapter;

	public int getPositionAdapter() {
		return positionAdapter;
	}

	public void setPositionAdapter(int positionAdapter) {
		this.positionAdapter = positionAdapter;
	}

	public int getArtistSelected() {
		return artistSelected;
	}

	public void setArtistSelected(int artistSelected) {
		this.artistSelected = artistSelected;
	}

	public String getArtistid() {
		return artistid;
	}

	public void setArtistid(String artistid) {
		this.artistid = artistid;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStagename() {
		return stagename;
	}

	public void setStagename(String stagename) {
		this.stagename = stagename;
	}

	public String getArtisttitle() {
		return artisttitle;
	}

	public void setArtisttitle(String artisttitle) {
		this.artisttitle = artisttitle;
	}

	public String getScheduletitle() {
		return scheduletitle;
	}

	public void setScheduletitle(String scheduletitle) {
		this.scheduletitle = scheduletitle;
	}

	public String getArtistimage() {
		return artistimage;
	}

	public void setArtistimage(String artistimage) {
		this.artistimage = artistimage;
	}

	public int getStageid() {
		return stageid;
	}

	public void setStageid(int stageid) {
		this.stageid = stageid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescription_lt() {
		return description_lt;
	}

	public void setDescription_lt(String description_lt) {
		this.description_lt = description_lt;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
