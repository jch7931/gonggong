package com.example.gonggong.gongong.data.handler;

import com.example.gonggong.gongong.data.dto.GalleryDto;
import com.example.gonggong.gongong.data.entity.GalleryEntity;

import java.util.ArrayList;

public interface GalleryDataHandler {
    GalleryEntity getGalleryEntity(String galContentId);

    GalleryEntity saveGalleryEntity(GalleryDto galleryDto);

    void saveAllGalleryEntity(ArrayList<GalleryDto> galleryDtoList);
}
