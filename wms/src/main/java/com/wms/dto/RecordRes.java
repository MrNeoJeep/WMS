package com.wms.dto;

import com.wms.entity.Record;
import lombok.Data;

@Data
public class RecordRes extends Record {

    private String username;
    private String adminname;
    private String goodsname;
    private String storagename;
    private String goodstypename;
}
