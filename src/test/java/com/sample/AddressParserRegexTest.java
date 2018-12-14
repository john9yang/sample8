package com.sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressParserRegexTest {

    public static void main(String[] args) {
//        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)" +
//                "(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";

        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)";

        String content="广东省深圳市福田区";
        Matcher m= Pattern.compile(regex).matcher(content);
        System.out.println("groupCount:"+m.groupCount());
        String province=null,city=null,county=null,town=null,village=null;
        while(m.find()){
            province=m.group("province");
            //city=m.group("city");
            System.out.println("province:"+province);
//          System.out.println("province:"+province+" city:"+city);
            break;
        }
    }
}