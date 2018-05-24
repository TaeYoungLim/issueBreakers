package net.front.tag.vo;


public class TagVo {
	private int tagId;
	private int tagRefferenceId;
	private String tagValue;
	private String tagType;
	private int count;
	
	public TagVo() {
		super();
	}
	
	public TagVo(int tagRefferenceId, String tagType) {
		super();
		this.tagRefferenceId = tagRefferenceId;
		this.tagType = tagType;
	}

	public TagVo(int tagRefferenceId, String tagValue, String tagType) {
		super();
		this.tagRefferenceId = tagRefferenceId;
		this.tagValue = tagValue;
		this.tagType = tagType;
	}
	
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public int getTagRefferenceId() {
		return tagRefferenceId;
	}
	public void setTagRefferenceId(int tagRefferenceId) {
		this.tagRefferenceId = tagRefferenceId;
	}
	public String getTagValue() {
		return tagValue;
	}
	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}
	public String getTagType() {
		return tagType;
	}
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
