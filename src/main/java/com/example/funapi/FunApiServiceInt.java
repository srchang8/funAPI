package com.example.funapi;

import java.util.List;

public interface FunApiServiceInt {

    public List<List<String>> groupShiftString(String[] strings);
    public int[][] findClosestPoint(int[][] points, int k);
    public int[] calcTimeFunc(int numOfProcess, List<String> logs);
    public FunApiServiceImpl.Node copyRandomList(FunApiServiceImpl.Node head);
    public int calcString(String s);

}
