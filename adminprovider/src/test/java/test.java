import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
        String str = "/abc";

        boolean status = str.contains("/");

        if(status){
            System.out.println("包含");

        }else{
            System.out.println("不包含");
        }

    }
}
