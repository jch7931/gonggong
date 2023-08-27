package com.example.gonggong.gongong.service;

import com.example.gonggong.gongong.data.dto.GalleryDto;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public interface GongGongService {
    public ArrayList<GalleryDto> getGalleryList1() throws IOException, ParseException;

    public GalleryDto getGallery(String galContentId);

    public void saveGallery(GalleryDto galleryDto);

    public void saveAllGallery(ArrayList<GalleryDto> galleryDtoList);
}
