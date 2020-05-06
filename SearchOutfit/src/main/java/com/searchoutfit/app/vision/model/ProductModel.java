package com.searchoutfit.app.vision.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductModel {

    private String name;
    private String url;
    private double score;
    private String imgUrl;

    public void setImgUrl(String displayName){
        this.imgUrl = "https://storage.googleapis.com/productsearch1337/" + displayName;
    }
}
