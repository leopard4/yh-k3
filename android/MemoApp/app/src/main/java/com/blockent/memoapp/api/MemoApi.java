package com.blockent.memoapp.api;

import com.blockent.memoapp.model.Memo;
import com.blockent.memoapp.model.MemoList;
import com.blockent.memoapp.model.Res;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MemoApi {

    // 내 메모 가져오는 API
    @GET("/memo")
    Call<MemoList> getMemoList(@Header("Authorization") String token,
                               @Query("offset") int offset,
                               @Query("limit") int limit);

    // 메모 생성 API
    @POST("/memo")
    Call<Res> addMemo(@Header("Authorization") String token,
                      @Body Memo memo);

}






