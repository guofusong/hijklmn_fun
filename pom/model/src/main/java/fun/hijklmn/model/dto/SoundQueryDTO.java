package fun.hijklmn.model.dto;

import java.util.Date;

public class SoundQueryDTO extends QueryReqDTO {

	private String soundName;

	private String soundType;

	private String soundTopPic;

	private String soundUrl;

	private String author;

	private String source;

	private String content;

	private Date onlineTime;

	private Integer favorite;

	private String favoriteIp;

	private Byte isValid;

	private String attribute1;

	private String attribute2;

	private String attribute3;

	private String attribute4;

	private String attribute5;

	private String attribute6;

	public String getSoundName() {
		return soundName;
	}

	public String getSoundType() {
		return soundType;
	}

	public String getSoundTopPic() {
		return soundTopPic;
	}

	public String getSoundUrl() {
		return soundUrl;
	}

	public String getAuthor() {
		return author;
	}

	public String getSource() {
		return source;
	}

	public String getContent() {
		return content;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public Integer getFavorite() {
		return favorite;
	}

	public String getFavoriteIp() {
		return favoriteIp;
	}

	public Byte getIsValid() {
		return isValid;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setSoundName(String soundName) {
		this.soundName = soundName;
	}

	public void setSoundType(String soundType) {
		this.soundType = soundType;
	}

	public void setSoundTopPic(String soundTopPic) {
		this.soundTopPic = soundTopPic;
	}

	public void setSoundUrl(String soundUrl) {
		this.soundUrl = soundUrl;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public void setFavorite(Integer favorite) {
		this.favorite = favorite;
	}

	public void setFavoriteIp(String favoriteIp) {
		this.favoriteIp = favoriteIp;
	}

	public void setIsValid(Byte isValid) {
		this.isValid = isValid;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

}
