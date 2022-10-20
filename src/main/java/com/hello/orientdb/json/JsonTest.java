package com.hello.orientdb.json;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    public static void main(String[] args) {
        String str = "{\n" +
                "    \"payload\":{\n" +
                "        \"id\":3,\n" +
                "        \"create_time\":\"2022-10-08 10:45:40.706\",\n" +
                "        \"update_time\":null,\n" +
                "        \"school_id\":3,\n" +
                "        \"name\":\"蓝三班\"\n" +
                "    },\n" +
                "    \"key\":\"c3f1cba24a5844b4831553b838dafc01\",\n" +
                "    \"source\":{\n" +
                "        \"version\":\"1.4.0.Final\",\n" +
                "        \"connector\":\"postgresql\",\n" +
                "        \"name\":\"cim_data\",\n" +
                "        \"ts_ms\":1665197140707,\n" +
                "        \"snapshot\":\"false\",\n" +
                "        \"db\":\"cim_data\",\n" +
                "        \"schema\":\"liupz\",\n" +
                "        \"table\":\"class\",\n" +
                "        \"txId\":48030009,\n" +
                "        \"lsn\":116772524848,\n" +
                "        \"xmin\":null,\n" +
                "        \"type\":\"MSG_DATA_BIZ\",\n" +
                "        \"src_type\":\"POSTGRESQL\",\n" +
                "        \"table_name\":\"liupz.class\"\n" +
                "    },\n" +
                "    \"op\":\"c\",\n" +
                "    \"ts_ms\":1665197140823\n" +
                "}";

        System.out.println(str);
        JsonNode jsonNode = JacksonUtils.toJsonNode(str);
        StreamResource resource = new StreamResource();
        resource.setChildren(child(jsonNode));
        System.out.println(JacksonUtils.toString(resource));
    }

    public static List<StreamResource> child(JsonNode jsonNode){
        List<StreamResource> list = new ArrayList<>();
        jsonNode.fields().forEachRemaining((entry)-> {
            StreamResource tmp = new StreamResource();
            tmp.setKey(entry.getKey());
            tmp.setType(entry.getValue().getNodeType());
            tmp.setDesc("");
            if(entry.getValue().isArray() || entry.getValue().isObject()){
                tmp.setChildren(child(entry.getValue()));
            }
            list.add(tmp);
        });
        return list;
    }
}
