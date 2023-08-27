package com.example.gonggong.gongong.data.handler;

import com.example.gonggong.gongong.data.dao.GalleryDao;
import com.example.gonggong.gongong.data.dto.GalleryDto;
import com.example.gonggong.gongong.data.entity.GalleryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GalleryDataHandlerImpl implements GalleryDataHandler{

    GalleryDao galleryDao;

    @Autowired
    public GalleryDataHandlerImpl(GalleryDao galleryDao) {
        this.galleryDao = galleryDao;
    }

    @Override
    public GalleryEntity getGalleryEntity(String galContentId) {
        return galleryDao.getGallery(galContentId);
    }

    @Override
    public GalleryEntity saveGalleryEntity(GalleryDto galleryDto) {
        GalleryEntity galleryEntity
                = new GalleryEntity(galleryDto.getGalContentId(),
                galleryDto.getGalContentTypeId(),
                galleryDto.getGalTitle(),
                galleryDto.getGalWebImageUrl(),
                galleryDto.getGalCreatedtime(),
                galleryDto.getGalModifiedtime(),
                galleryDto.getGalPhotographyMonth(),
                galleryDto.getGalPhotographyLocation(),
                galleryDto.getGalPhotographer(),
                galleryDto.getGalSearchKeyword()
                );
        return galleryDao.saveGallery(galleryEntity);
    }

    @Override
    public void saveAllGalleryEntity(ArrayList<GalleryDto> galleryDtoList) {
        ArrayList<GalleryEntity> galleryEntityList = new ArrayList<>();
        for (int i = 0; i < galleryDtoList.size(); i++) {
            GalleryEntity galleryEntity
                    = new GalleryEntity(galleryDtoList.get(i).getGalContentId(),
                    galleryDtoList.get(i).getGalContentTypeId(),
                    galleryDtoList.get(i).getGalTitle(),
                    galleryDtoList.get(i).getGalWebImageUrl(),
                    galleryDtoList.get(i).getGalCreatedtime(),
                    galleryDtoList.get(i).getGalModifiedtime(),
                    galleryDtoList.get(i).getGalPhotographyMonth(),
                    galleryDtoList.get(i).getGalPhotographyLocation(),
                    galleryDtoList.get(i).getGalPhotographer(),
                    galleryDtoList.get(i).getGalSearchKeyword()
            );
            galleryEntityList.add(galleryEntity);
        }

        galleryDao.saveAllGallery(galleryEntityList);
    }
}
