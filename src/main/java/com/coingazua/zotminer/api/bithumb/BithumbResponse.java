package com.coingazua.zotminer.api.bithumb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BithumbResponse<T> {
    private String status;
    private List<T> data;

    public BithumbResponse(){}

    public BithumbResponse(Class<T> typeParameterClass) {

    }
}
