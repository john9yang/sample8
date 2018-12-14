package com.sample.csv;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressParser {

    public static void main(String[] args) {
        Map<String,String> row=new HashMap<String,String>();
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            File file = new File("D:\\WorkDoc\\Salesforce_Crm\\ct.csv");
            InputStream inputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            reader = new BufferedReader(new InputStreamReader(dataInputStream,"GBK"));

            writer = new BufferedWriter(
                    new FileWriter(new File("D:\\WorkDoc\\Salesforce_Crm\\ct_result.csv"),true));

            writer.write('\ufeff');

            String header=reader.readLine();
            String newHeader = header+",Province_N"+",City_N";
            writer.write(new String(newHeader.getBytes(),"UTF-8"));
            writer.newLine();

            String line = null;
            StringBuilder newLine ;
            while (( line = reader.readLine()) != null ){
                String[] item = line.split(",");
                String address = item[item.length-1];
                System.out.println("address:"+address);
                Map<String,String> resultMap = addressResolution(address,row);
                System.out.println("resultMap:"+resultMap);

                newLine = new StringBuilder();
                if ( resultMap.get("province") == null  )
                    newLine.append(line).append(",").append("");
                else
                    newLine.append(line).append(",").append(resultMap.get("province"));

                if ( resultMap.get("city") == null)
                    newLine.append(",").append("");
                else
                    newLine.append(",").append(resultMap.get("city"));

                System.out.println("newLine:"+newLine.toString());

                writer.write(new String(newLine.toString().getBytes(),"UTF-8"));
                writer.newLine();
            }

            reader.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {e.printStackTrace();

        }
        finally {

        }
    }

    public static Map<String,String> addressResolution(String address,Map<String,String> rowMap){
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m= Pattern.compile(regex).matcher(address);
        String province=null,city=null,county=null,town=null,village=null;
        Map<String,String> row=new LinkedHashMap<String,String>();
        while(m.find()){
            province=m.group("province");
            row.put("province", province==null?"":province.trim());
            city=m.group("city");
            row.put("city", city==null?"":city.trim());
            county=m.group("county");
            row.put("county", county==null?"":county.trim());
            town=m.group("town");
            row.put("town", town==null?"":town.trim());
            village=m.group("village");
            row.put("village", village==null?"":village.trim());
        }
        return row;
    }


}
