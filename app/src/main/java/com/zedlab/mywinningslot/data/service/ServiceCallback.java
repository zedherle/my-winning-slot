package com.zedlab.mywinningslot.data.service;

public interface ServiceCallback<T> {
     void onSuccess(T response);
     void onFailure(T response);
}
