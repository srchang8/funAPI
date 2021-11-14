package com.example.funapi;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
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


    public int[][] findClosestPoint(int[][] points, int k){

        int left = 0;
        int right = points.length-1;

        while (left < right){

            int j = quickSort(points, left, right);

            if (j == k) break;
            if (j < k){
                left = j + 1;
            }else{
                right = j - 1;
            }
        }

        return Arrays.copyOfRange(points, 0, k);
    }

    private int quickSort(int[][] points, int start, int end){
        int pivot = findDisToOrigin(points[end]);
        int i = start;

        for (int j=start; j<end; j++){
            if (findDisToOrigin(points[j]) <= pivot){
                swap(points, i, j);
                i++;
            }
        }
        swap(points, i, end);
        return i;
    }

    private int findDisToOrigin(int[] points){
        return points[1]*points[1] + points[0]*points[0];
    }

    private void swap(int[][] nums, int i, int j){
        int[] temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
