package com.example.gonggong.gongong.service.impl;

import com.example.gonggong.gongong.data.dto.GalleryDto;
import com.example.gonggong.gongong.data.entity.GalleryEntity;
import com.example.gonggong.gongong.data.handler.GalleryDataHandler;
import com.example.gonggong.gongong.service.GongGongService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

@Service
@Transactional
public class GongGongServiceImpl implements GongGongService {

    GalleryDataHandler galleryDataHandler;

    @Autowired
    public GongGongServiceImpl(GalleryDataHandler galleryDataHandler) {
        this.galleryDataHandler = galleryDataHandler;
    }
    public ArrayList<GalleryDto> getGalleryList1() throws IOException, ParseException {
        ArrayList<GalleryDto> galleryDtoList = new ArrayList<GalleryDto>();

        String apiUrl = "http://apis.data.go.kr/B551011/PhotoGalleryService1/galleryList1";

        String serviceKey = "MkhnWqHzst373l%2FA%2BsPdIwQAQzBfW%2FRwheQ1iefJCG3vJMhH09k5zlaE6%2BxJUh51glL00DfwpG8BLmZtGu8ehg%3D%3D";
        String pageNo = "1";	        // 페이지 번호
        String numOfRows = "100";    	// 한 페이지 결과 수
        String MobileOS = "ETC";        // OS 구분 : IOS (아이폰), AND (안드로이드), WIN (윈도우폰), ETC(기타)
        String MobileApp = "AppTest";   // 서비스명(어플명)
        String arrange = "A";           // 정렬구분 : A=촬영일, B=제목, C=수정일
        String _type = "json";          //응답메세지 형식 : REST방식의 URL호출 시 json값 추가(디폴트 응답메세지 형식은XML)


        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileOS","UTF-8") + "=" + URLEncoder.encode(MobileOS, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("MobileApp","UTF-8") + "=" + URLEncoder.encode(MobileApp, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("arrange","UTF-8") + "=" + URLEncoder.encode(arrange, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode(_type, "UTF-8"));

        /*
         * GET방식으로 전송해서 파라미터 받아오기
         */
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result= sb.toString();

        JSONParser paser = new JSONParser(); //JSON Parser객채를 만듭니다. parser를 통해서 파싱을 합니다.
        JSONObject obj = (JSONObject) paser.parse(result); //Parser로 문자열 데이터를 JSON데이터로 변환합니다.

        // response 가져오기
        JSONObject parse_response = (JSONObject) obj.get("response"); //response key값에 맞는  Value인 JSON객체를 가져옵니다.

        // response 로 부터 body 찾아오기
        JSONObject parse_body = (JSONObject) parse_response.get("body");

        // body 로 부터 items 받아오기
        JSONObject parse_items = (JSONObject) parse_body.get("items");

        // items로 부터 itemlist 를 받아옵니다. itemlist : 뒤에 [ 로 시작하므로 jsonarray입니다.
        JSONArray parse_itemlist = (JSONArray) parse_items.get("item");

        if(0 != parse_itemlist.size()) {
            for(int i = 0; i < parse_itemlist.size(); i++) {
                GalleryDto galleryDto = new GalleryDto();
                JSONObject imsi = (JSONObject)  parse_itemlist.get(i);
                galleryDto.setGalContentId((String)imsi.get("galContentId"));
                galleryDto.setGalContentTypeId((String)imsi.get("galContentTypeId"));
                galleryDto.setGalTitle((String)imsi.get("galTitle"));
                galleryDto.setGalWebImageUrl((String)imsi.get("galWebImageUrl"));
                galleryDto.setGalCreatedtime((String)imsi.get("galCreatedtime"));
                galleryDto.setGalModifiedtime((String)imsi.get("galModifiedtime"));
                galleryDto.setGalPhotographyMonth((String)imsi.get("galPhotographyMonth"));
                galleryDto.setGalPhotographyLocation((String)imsi.get("galPhotographyLocation"));
                galleryDto.setGalPhotographer((String)imsi.get("galPhotographer"));
                galleryDto.setGalSearchKeyword((String)imsi.get("galSearchKeyword"));
                galleryDtoList.add(galleryDto);
            }
        }

        return galleryDtoList;
    }

    @Override
    public GalleryDto getGallery(String galContentId) {
        GalleryEntity galleryEntity = galleryDataHandler.getGalleryEntity(galContentId);
        if (null != galleryEntity){
            return new GalleryDto(galleryEntity.getGalContentId(),
                    galleryEntity.getGalContentTypeId(),
                    galleryEntity.getGalTitle(),
                    galleryEntity.getGalWebImageUrl(),
                    galleryEntity.getGalCreatedtime(),
                    galleryEntity.getGalModifiedtime(),
                    galleryEntity.getGalPhotographyMonth(),
                    galleryEntity.getGalPhotographyLocation(),
                    galleryEntity.getGalPhotographer(),
                    galleryEntity.getGalSearchKeyword());
        } else {
            return null;
        }
    }

    @Override
    public void saveGallery(GalleryDto galleryDto) {
        GalleryEntity galleryEntity = galleryDataHandler.saveGalleryEntity(galleryDto);
    }

    @Override
    public void saveAllGallery(ArrayList<GalleryDto> galleryDtoList) {
        galleryDataHandler.saveAllGalleryEntity(galleryDtoList);
    }
}
