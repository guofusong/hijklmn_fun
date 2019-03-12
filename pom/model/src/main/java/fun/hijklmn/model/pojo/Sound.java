package fun.hijklmn.model.pojo;

import java.util.Date;

public class Sound {

	private String souId;

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

	public String getSouId() {
		return souId;
	}

	public void setSouId(String souId) {
		this.souId = souId == null ? null : souId.trim();
	}

	public String getSoundName() {
		return soundName;
	}

	public void setSoundName(String soundName) {
		this.soundName = soundName == null ? null : soundName.trim();
	}

	public String getSoundType() {
		return soundType;
	}

	public void setSoundType(String soundType) {
		this.soundType = soundType == null ? null : soundType.trim();
	}

	public String getSoundTopPic() {
		return soundTopPic;
	}

	public void setSoundTopPic(String soundTopPic) {
		this.soundTopPic = soundTopPic == null ? null : soundTopPic.trim();
	}

	public String getSoundUrl() {
		return soundUrl;
	}

	public void setSoundUrl(String soundUrl) {
		this.soundUrl = soundUrl == null ? null : soundUrl.trim();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source == null ? null : source.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	public Integer getFavorite() {
		return favorite;
	}

	public void setFavorite(Integer favorite) {
		this.favorite = favorite;
	}

	public String getFavoriteIp() {
		return favoriteIp;
	}

	public void setFavoriteIp(String favoriteIp) {
		this.favoriteIp = favoriteIp == null ? null : favoriteIp.trim();
	}

	public Byte getIsValid() {
		return isValid;
	}

	public void setIsValid(Byte isValid) {
		this.isValid = isValid;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1 == null ? null : attribute1.trim();
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2 == null ? null : attribute2.trim();
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3 == null ? null : attribute3.trim();
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4 == null ? null : attribute4.trim();
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5 == null ? null : attribute5.trim();
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6 == null ? null : attribute6.trim();
	}
}