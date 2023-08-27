package com.example.gonggong.gongong.data.dao.impl;

import com.example.gonggong.gongong.data.dao.GalleryDao;
import com.example.gonggong.gongong.data.entity.GalleryEntity;
import com.example.gonggong.gongong.data.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GalleryDaoImpl implements GalleryDao {
    GalleryRepository galleryRepository;

    @Autowired
    public GalleryDaoImpl(GalleryRepository galleryRepository){
        this.galleryRepository = galleryRepository;
    }

    @Override
    public GalleryEntity getGallery(String galContentId) {
        return galleryRepository.findById(galContentId).orElse(null);
    }

    @Override
    public GalleryEntity saveGallery(GalleryEntity galleryEntity) {
        return galleryRepository.save(galleryEntity);
    }

    @Override
    public void saveAllGallery(ArrayList<GalleryEntity> galleryEntityList) {
        galleryRepository.saveAll(galleryEntityList);
    }
}
