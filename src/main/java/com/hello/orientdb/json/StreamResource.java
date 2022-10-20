package com.hello.orientdb.json;

import com.fasterxml.jackson.databind.node.JsonNodeType;
import lombok.Data;

import java.util.List;

@Data
public class StreamResource {
    private String key;
    private JsonNodeType type;
    private String desc;
    private List<StreamResource> children;
}
