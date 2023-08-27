package com.example.gonggong.gongong.data.dto;

import com.example.gonggong.gongong.data.entity.GalleryEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GalleryDto {
    private String galContentId;
    private String galContentTypeId;
    private String galTitle;
    private String galWebImageUrl;
    private String galCreatedtime;
    private String galModifiedtime;
    private String galPhotographyMonth;
    private String galPhotographyLocation;
    private String galPhotographer;
    private String galSearchKeyword;

    public GalleryEntity toEntity(){
        return GalleryEntity.builder()
                .galContentId(galContentId)
                .galContentTypeId(galContentTypeId)
                .galTitle(galTitle)
                .galWebImageUrl(galWebImageUrl)
                .galCreatedtime(galCreatedtime)
                .galModifiedtime(galModifiedtime)
                .galPhotographyMonth(galPhotographyMonth)
                .galPhotographyLocation(galPhotographyLocation)
                .galPhotographer(galPhotographer)
                .galSearchKeyword(galSearchKeyword)
                .build();
    }
}
