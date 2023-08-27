package com.example.gonggong.gongong.data.dao;

import com.example.gonggong.gongong.data.dto.GalleryDto;
import com.example.gonggong.gongong.data.entity.GalleryEntity;

import java.util.ArrayList;

public interface GalleryDao {
    GalleryEntity getGallery(String galContentId);

    GalleryEntity saveGallery(GalleryEntity galleryEntity);

    void saveAllGallery(ArrayList<GalleryEntity> galleryEntityList);
}
