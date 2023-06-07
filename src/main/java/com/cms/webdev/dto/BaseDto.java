package com.cms.webdev.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@ToString
@Accessors(chain = true)
public class BaseDto<T extends Serializable> {
    private T id;
    private String status;
    private OffsetDateTime createdOn;
    private OffsetDateTime updatedOn;
    private String createdBy;
    private String updatedBy;
    private Boolean isDeleted=false;
}
