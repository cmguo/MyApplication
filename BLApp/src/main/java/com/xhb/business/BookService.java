package com.xhb.business;

import com.xhb.prism.http.ResultConverterFactory;
import com.xhb.prism.http.annotation.*;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

@BaseUri("https://56.2.23.2/")
public interface BookService {

    static class LeftTop
    {
        public double x;
        public double y;
    };

    static class Location
    {
        public LeftTop rightBottom;
        public LeftTop leftTop;
    };

    static class ItemInfo
    {
        public Location itemLocation;
    };

    static class QuestionInfo
    {
        public String questionId;
        public String correctionType;
        public String pagingInfo;
        public List<QuestionInfo> subQuestions;
        public long index;
        public List<ItemInfo> itemInfo;
        public long questionType;
        public Location questionLocation;
    };

    static class AreaInfo
    {
        public Location areaLocation;
        public String numOfQuestions;
        public List<QuestionInfo> questionInfo;
        public String pagingInfo;
        public String areaType;
        public long areaId;
        public String questionType;
    };

    static class DataElement
    {
        public String pageId;
        public String paperUrl;
        public List<AreaInfo> areaInfo;
        public String getPaperUrl() { return paperUrl; }
    }
    static class PaperTypesetInfo
    {
        public String paperId;
        public List<DataElement> datas;

        public List<DataElement> getDatas() {
            return datas;
        }
    }
    static class PaperData
    {
        public PaperTypesetInfo paperTypesetInfo;

        public PaperTypesetInfo getPaperTypesetInfo() {
            return paperTypesetInfo;
        }
    }

    static class ElectronicTeachingMaterial
    {
        public long pageId;
        public String chapterName;
        public String tagName;
        public String thumbImgUrl;
        public String materialUrl;
        public String chapterId;

        public String getMaterialUrl() {
            return materialUrl;
        }
    }

    static class EBookData
    {
        public List<ElectronicTeachingMaterial> electronicTeachingMaterialList;

        public List<ElectronicTeachingMaterial> getElectronicTeachingMaterialList() {
            return electronicTeachingMaterialList;
        }
    }

    @GET("preparation/app/v2/teaching/paper/typeset/info")
    Observable<PaperData> getPaperInfo(@Query("typesetId") String typesetId);

    @GET("preparation/app/v2/teaching/material/electronic")
    Observable<EBookData> getEBookInfo(@Query("bookId") String bookId, @Query("chapterId") String chapterId);
}
