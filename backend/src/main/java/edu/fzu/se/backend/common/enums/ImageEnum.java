package edu.fzu.se.backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ImageEnum {
    USER("用户", 1),
    GOOD("商品", 2),
    PLATFORM("平台", 3),
    OTHER("其他", 4);

    private static Map<Integer,ImageEnum > searchMap = new HashMap<>();
    static {
        for (ImageEnum item : ImageEnum.values()) {
            searchMap.put(item.getType(), item);
        }
    }
    public static ImageEnum getByType(Integer type){
        return searchMap.get(type);
    }
    public static String getNameByType(Integer type){
        return searchMap.get(type).getMessage();
    }

    String message;
    int type;

}
