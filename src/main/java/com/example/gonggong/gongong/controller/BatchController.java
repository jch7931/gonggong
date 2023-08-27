package com.example.gonggong.gongong.controller;

import com.example.gonggong.gongong.data.dto.GalleryDto;
import com.example.gonggong.gongong.service.GongGongService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/v1/")
public class BatchController {
    private GongGongService gongGongService;

    @Autowired
    public BatchController(GongGongService gongGongService) { this.gongGongService = gongGongService; }

    @PostMapping(value = "/save/galleryList1")
    public String upsertGalleryList1() throws IOException, ParseException {
        ArrayList<GalleryDto> galleryDtoList = gongGongService.getGalleryList1();

//        for (GalleryDto galleryDto : galleryDtoList) {
//            gongGongService.saveGallery(galleryDto);
//        }
        gongGongService.saveAllGallery(galleryDtoList);


        return "good";
    }

    @GetMapping(value = "/upsert/galleryList1/{galContentId}")
    public GalleryDto getGallery(@PathVariable String galContentId) {
        return gongGongService.getGallery(galContentId);
    }
}
