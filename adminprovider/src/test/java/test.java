import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.mysql.cj.MysqlType.JSON;

public class test {
    public static void main(String[] args) {
        List<String> col=new ArrayList<>();
        for (int i=0;i<3;i++){
            col.add("a");
        }
        for (int i=0;i<5;i++){
            col.add("b");
        }
        for (int i=0;i<6;i++){
            col.add("c");
        }
        Integer start=1;
        List<Integer> newcol1=getDifferentCol(col,start);
        Integer all= col.size();
        List<ArrayList> list1=placeArray(all,newcol1);

        List<String> col1=new ArrayList<>();
        for (int i=0;i<2;i++){
            col1.add("A");
        }
        for (int i=0;i<9;i++){
            col1.add("B");
        }
        for (int i=0;i<3;i++){
            col1.add("C");
        }
        List<Integer> newcol2=getDifferentCol(col1,start);
        List<ArrayList> list2=placeArray(all,newcol2);

        System.out.println(list1);
        System.out.println(list2);

//        List<ArrayList> list3=twoArray(list1,list2,all);
//        List<Integer> list4=changearray(list1,list2,all);
//        System.out.println(list3);
//        System.out.println(list4);
//        List<Integer> re=getadd(list3);
//        System.out.println(re);
        List<List> temp=new ArrayList();
        for (int i=0;i<2;i++){
            if(i == 0){
                temp.add(changearray(list1,all));
            }else {
                temp.add(changearray(list1,list2,all));
            }
        }
        System.out.println(temp);
    }
    public static List<ArrayList> placeArray(Integer allrow,List<Integer> col){
        List<ArrayList> newcol=new ArrayList<>();
        for (int i=0;i<col.size();i++){
            ArrayList<Integer> one=new ArrayList<>();
            if(i+1== col.size()){
                one.add(col.get(i));
                one.add(allrow-1);
            }else {
                one.add(col.get(i));
                one.add(col.get(i+1)-1);
            }
            newcol.add(one);
        }
        return newcol;
    }
    public static List<Integer> getDifferentCol(List<String> col,Integer start){
        List<Integer> newcol=new ArrayList<>();
        int allrow= col.size();
        String lastcontent="";
        for (int i=0;i< col.size();i++){
            if (i<start){
                newcol.add(i);
                continue;
            }else {
                if ("".equals(lastcontent)){
                    lastcontent=col.get(i);
                    newcol.add(i);
                }else if(!lastcontent.equals(col.get(i))){
                    newcol.add(i);
                    lastcontent=col.get(i);
                }
            }
        }
        return newcol;
    }

    public static List<ArrayList> twoArray(List<ArrayList> lastcol,List<ArrayList> col,Integer allrow){
        List<ArrayList> newcol=new ArrayList<>();
        int tempstart=0;
        int tempend=0;
        int indexlast=0;
        int indexnow=0;
        for (int i=0;i<allrow;i++){
            Integer lasta= (Integer) lastcol.get(indexlast).get(0);
            Integer lastb=(Integer) lastcol.get(indexlast).get(1);
            Integer nowa= (Integer) col.get(indexnow).get(0);
            Integer nowb= (Integer) col.get(indexnow).get(1);
            tempend++;
            if(tempend>lastb||tempend>nowb){
                ArrayList one=new ArrayList();
                one.add(tempstart);
                one.add(tempend-1);
                newcol.add(one);
                tempstart=tempend;
                if(tempend>lastb){
                    indexlast++;
                }
                if (tempend>nowb){
                    indexnow++;
                }
            }
        }
        return newcol;
    }

    public static List<Integer> changearray(List<ArrayList> lastcol,List<ArrayList> col,Integer allrow){
        List<Integer> change=new ArrayList<Integer>();
        int tempstart=0;
        int tempend=0;
        int indexlast=0;
        int indexnow=0;
        for (int i=0;i<allrow;i++){
            Integer lasta= (Integer) lastcol.get(indexlast).get(0);
            Integer lastb=(Integer) lastcol.get(indexlast).get(1);
            Integer nowa= (Integer) col.get(indexnow).get(0);
            Integer nowb= (Integer) col.get(indexnow).get(1);
            tempend++;
            if(tempend>lastb||tempend>nowb){
                change.add(tempend-tempstart);
                tempstart=tempend;
                if(tempend>lastb){
                    indexlast++;
                }
                if (tempend>nowb){
                    indexnow++;
                }
            }
        }
        return change;
    }

    public static List<Integer> changearray(List<ArrayList> col,Integer allrow){
        List<Integer> result=new ArrayList<>();
        for (int i=0;i<col.size();i++){
            int a= (int) col.get(i).get(0);
            int b= (int) col.get(i).get(1);
            result.add(b-a+1);
        }
        return result;
    }

    public static List<Integer> getadd(List<ArrayList> temp){
        List<Integer> add=new ArrayList<>();
        for (int i=0;i<temp.size();i++){
            Integer add1=(Integer)temp.get(i).get(1)-(Integer)temp.get(i).get(0);
            add.add(add1);
        }
        return add;
    }
}
