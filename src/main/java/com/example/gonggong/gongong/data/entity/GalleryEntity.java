package com.example.gonggong.gongong.data.entity;

import com.example.gonggong.gongong.data.dto.GalleryDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "gallery")
public class GalleryEntity {

    @Id
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

    public GalleryDto toDto(){
        return GalleryDto.builder()
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
