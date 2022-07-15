import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.mysql.cj.MysqlType.JSON;

public class test {
    public static void main(String[] args) {
//        Integer[] ints = {98, 243, 35, 13, 57, 243};
//        List<Integer> list = Arrays.asList(ints);
//
//        //之前的排序
//        list.sort(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2-o1;
//            }
//        });
//        System.out.println(list);
//        //[243, 243, 98, 57, 35, 13]
//
//        //使用Lambda表达式
//        list.sort((o1,o2)->(o1-o2));
//
//        System.out.println(list);
//        //[13, 35, 57, 98, 243, 243]
        String str = "Y,N|OLD_USER_RECALL_ACTIVITY,A|IDCARD,MOBILE";
        String[] value=str.split("\\|");
        String[] botton=value[0].split("\\,");
        String[] activity=value[1].split("\\,");
        String[] type=value[2].split("\\,");
        for (int i=0;i<botton.length;i++
             ) {
            System.out.println(i);
            System.out.println(botton[i]+"&"+activity[i]+"##"+type[i]);
        }


    }
}
