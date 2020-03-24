package model;

public class ImageLink {
	private int imageLinkId;
	private String imageLink;
	private boolean isAvatar;
	private int phoneId;
	
	public int getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}
	public int getImageLinkId() {
		return imageLinkId;
	}
	public void setImageLinkId(int imageLinkId) {
		this.imageLinkId = imageLinkId;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public boolean isAvatar() {
		return isAvatar;
	}
	public void setAvatar(boolean isAvatar) {
		this.isAvatar = isAvatar;
	}
	
	

}
