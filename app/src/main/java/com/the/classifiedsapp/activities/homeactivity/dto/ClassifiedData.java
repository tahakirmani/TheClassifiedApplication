
package com.the.classifiedsapp.activities.homeactivity.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ClassifiedData implements Parcelable {

    //Custom Fields
    private String parsedDate;


    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("image_ids")
    @Expose
    private List<String> imageIds = null;
    @SerializedName("image_urls")
    @Expose
    private List<String> imageUrls = null;
    @SerializedName("image_urls_thumbnails")
    @Expose
    private List<String> imageUrlsThumbnails = null;


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ClassifiedData createFromParcel(Parcel in) {
            return new ClassifiedData(in);
        }

        public ClassifiedData[] newArray(int size) {
            return new ClassifiedData[size];
        }
    };


    protected ClassifiedData(Parcel in) {
        parsedDate = in.readString();
        createdAt = in.readString();
        price= in.readString();
        name= in.readString();
        uid = in.readString();
        imageIds =  in.createStringArrayList();
        imageUrls = in.createStringArrayList();
        imageUrlsThumbnails = in.createStringArrayList();

    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<String> getImageIds() {
        return imageIds;
    }

    public void setImageIds(List<String> imageIds) {
        this.imageIds = imageIds;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<String> getImageUrlsThumbnails() {
        return imageUrlsThumbnails;
    }

    public void setImageUrlsThumbnails(List<String> imageUrlsThumbnails) {
        this.imageUrlsThumbnails = imageUrlsThumbnails;
    }


    public String getParsedDate() {
        return parsedDate;
    }

    public void setParsedDate(String parsedDate) {
        this.parsedDate = parsedDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(parsedDate);
        dest.writeString(createdAt);
        dest.writeString(price);
        dest.writeString(name);
        dest.writeString(uid);
        dest.writeStringList(imageIds);
        dest.writeStringList(imageUrls);
        dest.writeStringList(imageUrlsThumbnails);
    }


}
