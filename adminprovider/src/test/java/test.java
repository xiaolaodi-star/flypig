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
        String str = "{\"bodyContent\":\"增仓锁定期间活动制定产品的日均资产较活动开始前一日日终资产新增部分，按照增仓不同档位给与相应奖励如下:\",\"bottomContent\":\"温馨提示：<br/>理财非存款、产品有风险、投资须谨慎。业绩比较基准不构成理财产品的任何收益承诺。产品过往业绩不代表其未来表现，不等于理财产品实际收益，投资须谨慎。本资料仅为产品及活动宣传使用，不作为任何法律文件，产品详情请以理财产品说明书为准。<br/>如需进一步了解与咨询，可联系我行客4000-156-999\",\"headerbgimg\":\"https://fmtest.yillionbank.com:58100/images/imageMsc/1656401519427.png\",\"backgroungcolor\":\"\",\"remainfull\":\"*持续购买达到“已满额”档位，所得奖励将自动调至下一档。\",\"shareheadtopic\":\"理财值得买专场\",\"sharebodytopic\":\"增仓乐享最高奖励1288\",\"wechatsharepicture\":\"https://fmtest.yillionbank.com:58100/images/imageMsc/1656402153515.png\",\"partnertopic\":\"已合作银行理财子公司\",\"ranklistopic\":\"排行榜将于xx日24点前放榜，敬请期待。\",\"partner\":[\"https://fmtest.yillionbank.com:58100/images/imageMsc/1656401192173.png\",\"https://fmtest.yillionbank.com:58100/images/imageMsc/1656401239266.png\",\"https://fmtest.yillionbank.com:58100/images/imageMsc/1656401277688.png\",\"https://fmtest.yillionbank.com:58100/images/imageMsc/1656401322460.png\",\"https://fmtest.yillionbank.com:58100/images/imageMsc/1656401360261.png\",\"https://fmtest.yillionbank.com:58100/images/imageMsc/1656401389019.png\",\"https://fmtest.yillionbank.com:58100/images/imageMsc/1656401417474.png\",\"https://fmtest.yillionbank.com:58100/images/imageMsc/1656401450801.png\"],\"rule\":\"一、活动时间：2022年XX月XX日-2022年XX月XX日 <br>产品购买截止时间为2022年XX月XX日15点<br>产品增仓锁定时间为2022年XX月XX日-2022年<br>XX月XX日二、活动对象：亿联银行注册用户均可参与本次活动，未<br>注册用户注册后即可参与活动。<br>三、参与方式<br>1.活动期间，用户访问活动页面即为参与活动。<br>2.活动期间，用户购买活动指定产品，不限次数，增仓锁定<br>期为180天。<br>3.增仓锁定期间活动指定产品的日均资产较2022年XX月XX<br>日资产新增部分，按照增仓不同档位给与相应奖励，奖励<br>规则如下：<br>4.增仓锁定期间的日均资产=2022年<br>XX月XX日至2022年XX月XX日的每日资产之和/锁定天数（180天）<br>5.日均资产新增部分=增仓锁定期间的日均资产-2022年XX<br>月XX日资产<br>6.活动适用产品：杭银添益90天、杭银周添益7天<br>7.该活动按照用户在某奖励档位达标时间的先后顺序动态展<br>示“达标名额”，该档位奖励“达标名额”等于“奖励名<br>额”后，该档位状态变更为“已满额”。如用户持续购买<br>达到“已满额”档位，所得奖励自动将至下一档。奖励金<br>额将在增仓锁定期结束后的七个工作日内发放至用户绑定<br>卡。<br>8.用户可在亿联银行微信公众号或者亿联银行APP-我的-我<br>的资产处查看产品持仓情况。<br>四、其他规则<br>参与活动过程中，如果用户出现违规行为（如作弊领取、刷账号<br>ID、恶意操作等），亿联银行将取消违规用户的奖励收益，并<br>有权撤销违规交易和奖励，并保留追究法律责任的权利。\"}";

//        System.out.println(jsonObject);


    }
}
