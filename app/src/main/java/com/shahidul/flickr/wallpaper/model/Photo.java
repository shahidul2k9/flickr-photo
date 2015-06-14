package com.shahidul.flickr.wallpaper.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.shahidul.flickr.wallpaper.util.PhotoUtils;

public class Photo implements Parcelable {
	private String photoId;
	private String farmId;
	private String serverId;
	private String secret;
	private String thumbnailImageUrl;
	private String imageUrl;

	public Photo(String farmId, String photoId, String serverId, String secret) {
		this.farmId = farmId;
		this.photoId = photoId;
		this.serverId = serverId;
		this.secret = secret;
	}


	public String getFarId() {
		return farmId;
	}

	public void setFarId(String farmId) {
		this.farmId = farmId;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getThumbnailImageUrl() {
		if (thumbnailImageUrl == null)
			thumbnailImageUrl = PhotoUtils.createURL(
					PhotoUtils.THUMB_URL_KEY, this);
		return thumbnailImageUrl;
	}

	public void setThumbnailImageUrl(String thumbnailImageUrl) {
		this.thumbnailImageUrl = thumbnailImageUrl;
	}

	public String getImageUrl() {
		if (imageUrl == null)
			imageUrl = PhotoUtils.createURL(PhotoUtils.IMAGE_URL_KEY, this);
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(photoId);
		dest.writeString(farmId);
		dest.writeString(serverId);
		dest.writeString(secret);
		dest.writeString(getThumbnailImageUrl());
		dest.writeString(getImageUrl());

	}

	public Photo(Parcel in) {
		photoId = in.readString();
		farmId = in.readString();
		serverId = in.readString();
		secret = in.readString();
		thumbnailImageUrl = in.readString();
		imageUrl = in.readString();
	}
	public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
		public Photo createFromParcel(Parcel in) {
			return new Photo(in);
		}

		public Photo[] newArray(int size) {
			return new Photo[size];
		}
	};

}
