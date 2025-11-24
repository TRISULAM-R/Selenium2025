package javaPrograms;

import java.util.*;

public class FindLeastValueFromMap
{
    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();
        map.put("apple",25);
        map.put("mango",20);
        map.put("bannana",17);
        map.put("papaya",12);
        map.put("lichie",50);


        int min =Integer.MAX_VALUE;
        String name = "";
        for(Map.Entry<String, Integer> m : map.entrySet())
        {
            if(m.getValue() < min)
            {
                   name = m.getKey();
                   min = m.getValue();
            }
        }
        System.out.println(name+" "+min);
    }
}
