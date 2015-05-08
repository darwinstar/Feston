package Objects;

import java.io.Serializable;

public class Posts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6306853201465995658L;
	private String postid;
	private String title_lt;
	private String title_en;
	private String content_lt;
	private String content_en;
	private String date;
	private int position;
	private String category;
	private String image;

	public String getPostid() {
		return postid;
	}

	public void setPostid(String postid) {
		this.postid = postid;
	}

	public String getTitle_lt() {
		return title_lt;
	}

	public void setTitle_lt(String title_lt) {
		this.title_lt = title_lt;
	}

	public String getTitle_en() {
		return title_en;
	}

	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}

	public String getContent_lt() {
		return content_lt;
	}

	public void setContent_lt(String content_lt) {
		this.content_lt = content_lt;
	}

	public String getContent_en() {
		return content_en;
	}

	public void setContent_en(String content_en) {
		this.content_en = content_en;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
