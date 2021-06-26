
package com.the.classifiedsapp.activities.homeactivity.dto;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ClassifiedDTO {


    @SerializedName("results")
    @Expose
    private List<ClassifiedData> results = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public List<ClassifiedData> getResults() {
        return results;
    }

    public void setResults(List<ClassifiedData> results) {
        this.results = results;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }


}
