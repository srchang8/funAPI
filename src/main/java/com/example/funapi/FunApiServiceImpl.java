package com.example.funapi;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class FunApiServiceImpl implements FunApiServiceInt{
    @Override
    public List<List<String>> groupShiftString(String[] strings) {

        HashMap<String, List<String>> map = new HashMap();

        for (int i=0; i<strings.length; i++){
            String hash = findHash(strings[i]);

            if (!map.containsKey(hash)){
                map.put(hash, new ArrayList());
            }
            map.get(hash).add(strings[i]);
        }

        return new ArrayList(map.values());
    }

    private String findHash(String s){
        StringBuilder sb = new StringBuilder();

        for (int i=1; i<s.length(); i++){

            int diff = s.charAt(i) - s.charAt(i-1);
            if (diff < 0){
                diff += 26;
            }
            sb.append(diff);
            sb.append(",");
        }
        return sb.toString();
    }
}
