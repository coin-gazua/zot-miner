package com.coingazua.zotminer.api.bithumb;

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
}
