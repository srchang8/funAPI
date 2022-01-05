package com.example.funapi;

import org.springframework.stereotype.Component;

import java.util.*;

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

    /*

    Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
    Output: [3,4]

    [0,1,  2,3,4,5,  6]
    [0,0  [1,1,1,1]  0]
    s      S     E   E

    3,4

    prevTime

     */
    public int[] calcTimeFunc(int numOfProcess, List<String> logs){

        int[] result = new int[numOfProcess];

        Stack<Integer> stack = new Stack();
        int prevTime = 0;
        for (String log : logs){

            String[] l = log.split(":");
            int id = Integer.valueOf(l[0]);
            int currLogTime = Integer.valueOf(l[2]);
            boolean start = l[1].equals("start");

            if (start){

                if (!stack.isEmpty()){
                    int prevId = stack.peek();
                    result[prevId] += currLogTime - prevTime - 1;
                }
                stack.push(id);
            }else{
                stack.pop();
                result[id] += currLogTime - prevTime + 1;
            }
            prevTime = currLogTime;
        }

        return result;
    }


    //"3+2*2"
    // space
    //end
    public int calcString(String s){
        int result = 0;

        int lastNum = 0;

        int num = 0;
        char lastSign = '+';

        for (int i=0; i<s.length(); i++){

            char c = s.charAt(i);

            if (Character.isDigit(c)){
                num = num * 10 + c - '0';
            }

            if (!Character.isDigit(c) && c != ' ' || i == s.length()-1){

                if (lastSign == '+'){
                    result += lastNum;
                    lastNum = num;
                }else if (lastSign == '-'){
                    result += lastNum;
                    lastNum = -num;
                }else if (lastSign == '/'){
                    lastNum = lastNum / num;
                }else if (lastSign == '*'){
                    lastNum = lastNum * num;
                }
                lastSign = c;
                num = 0;
            }
        }

        result += lastNum;

        return result;
    }

}
