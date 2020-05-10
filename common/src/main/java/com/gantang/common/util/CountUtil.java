package com.gantang.common.util;

import com.gantang.common.sysimport.FileImportUtil;
import com.gantang.common.sysimport.MvcConfig;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 *
 * @ProjectName(项目名称): parent
 * @Package(包名称): com.gantang.common.util
 * @ClassName(类名称): countUtil
 * @Title(标题):
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期): 2020/4/23 9:56
 * @version(版本): V1.0
 * @Copyright(版权): www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述): 计算日期等其他等时间相关的函数
 * 注意：本内容仅限于甘棠公司内部传阅，禁止外泄以及用于其他的商业目的的
 * ————————————————————————————————————
 * 修改记录
 * 修改者：
 * 修改时间：
 * 复审人:
 * 修改原因：
 * <p>
 * ——————————————————————————————————————
 */
public class CountUtil {
    /**
     *
     * @Description: 过去的某一时间,计算距离当前的时间
     * @param @param time
     * @param @return
     * @return String
     * @author sl.qiu
     * @date 2018年6月25日上午9:46:52
     */
    public static  String calculateTime(String time,String year) {
        // 获取当前时间的毫秒数
        long nowTime = System.currentTimeMillis();
        String msg = null;
        // 指定时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 指定时间
        Date setTime = null;
        // 将字符串转换为指定的时间格式
        try {
            setTime = sdf.parse(time);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        // 获取指定时间的毫秒数
        long reset = setTime.getTime();
        long dateDiff = nowTime - reset;

        if (dateDiff < 0) {
            msg = "租赁日期未开始，不能进行租金计算！";
        } else {
            // 秒
            long dateTemp1 = dateDiff / 1000;
            // 分钟
            long dateTemp2 = dateTemp1 / 60;
            // 小时
            long dateTemp3 = dateTemp2 / 60;
            // 天数
            long dateTemp4 = dateTemp3 / 24;
            // 月数
            long dateTemp5 = dateTemp4 / 30;
            // 年数
            long dateTemp6 = dateTemp5 / 12;

            if (dateTemp6 > Long.parseLong(year)) {
                msg=String.valueOf(dateTemp6-Long.parseLong(year));
            }else{
                msg = "0";
            }
//            else if (dateTemp5 > 0) {
//                msg = dateTemp5 + "个月前";
//
//            } else if (dateTemp4 > 0) {
//                msg = dateTemp4 + "天前";
//
//            } else if (dateTemp3 > 0) {
//                msg = dateTemp3 + "小时前";
//
//            } else if (dateTemp2 > 0) {
//                msg = dateTemp2 + "分钟前";
//
//            } else if (dateTemp1 > 0) {
//                msg = "刚刚";
//
//            }
        }
        return msg;

    }
    /**
     *
     * @Description: 拼接查询条件字符串
     * @param @param time
     * @param @param year
     * @param @return
     * @return String
     * @author sl.qiu
     * @date 2018年6月29日下午3:54:32
     */
    public static  String splicingQueryCondition(Map<String, String> map){
        StringBuilder queryCondition = new StringBuilder();
        String parameteFields=map.get("parameteFields");
        String parameteFieldsCodes=map.get("parameteFieldsCodes");
        String[] parameteField=parameteFields.split(",");
        String[] parameteFieldsCode=parameteFieldsCodes.split(",");
        for(int i=0;i<parameteField.length;i++){
            queryCondition.append("and").append(" ").append(parameteField[i]).append("=").append("\'").append(parameteFieldsCode[i]).append("\'")
                    .append(" ");

        }
        return queryCondition.toString();
    }
    /**
     * 将map转成有序的list
     * @param map
     * @return
     */
    public static List<Object> mapToList(Map<String,String> map){
        List<Object> list = new ArrayList<Object>();
        Iterator<String> it = map.keySet().iterator();
        int i = 0;
        while(it.hasNext()){
            String key = it.next();
            String value = map.get(key);
            Map<String, String> item = new HashMap<String, String>(100);
            item.put("key", key);
            item.put("value", value);
            list.add(i, item);
            i++;
        }
        return list;
    }
    /**
     *
     * @Title: CalculateYearAndMonth
     * @Description: TODO(计算两个时间段相差的年数/月数)
     * @param endTime
     * @return
     * @return: Map<String,String>
     * @throws
     * @author: sl.qiu
     */
    public static  Map<String,Object>  calculateYearAndMonth(String startTime,String endTime) {
        Map<String,Object> map=new HashMap<String,Object>(100);
        // 指定时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 指定时间
        Date fromDate = null;
        // 将字符串转换为指定的时间格式
        try {
            fromDate = sdf.parse(startTime);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        // 指定时间
        Date toDate = null;
        // 将字符串转换为指定的时间格式
        try {
            toDate = sdf.parse(endTime);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        Map<String,String>  result = dayCompare(fromDate, toDate);
        double month = Double.parseDouble(result.get("month"));

        if(startTime.substring(9,10).equals("1")) {
            month=month+1;
        }
        if(Math.round(month)-month==0){
            map.put("month", String.valueOf((long)month));
        }
        double year = Double.parseDouble(result.get("year"));
        //返回2位小数，并且四舍五入
        DecimalFormat df = new DecimalFormat("######0.0");
        map.put("year", year);

        return map;
    }
    /**
     * @Title: spacedCalculationOfRent
     * @Description: TODO(间隔性租金计算公式)
     * @param totalMonth
     * @param freeYear
     * @param firstUnitRent
     * @param intervalYear
     * @param intervalRentIncring
     * @return
     * @return: Map<String,String>
     * @throws
     * @author: sl.qiu
     */
    public static Map<String, String> spacedCalculationOfRent(int totalMonth, int freeYear, Double firstUnitRent,
                                                              String intervalYear, String intervalRentIncring) {
        DecimalFormat df=new DecimalFormat(".##");
        Map<String,String> map=new HashMap<String,String>(totalMonth);
        for(int i=1;i<=totalMonth;i++) {
            int number=Integer.parseInt(intervalYear)*12;
            if(i<=freeYear) {
                map.put(i+"", "0");
            }else if(i<=number){
                map.put(i+"", firstUnitRent.toString());

            }else{
                int n=(i-1)/number;
                Double firstUnitRentResult=firstUnitRent*Math.pow(1+Double.parseDouble(intervalRentIncring)/100,  n);
                map.put(i+"", df.format(firstUnitRentResult));
            }
        }
        return map;
    }
    /**
     * @Title: continuousComputingRents
     * @Description: TODO(连续性租金计算公式)
     * @param totalMonth
     * @param freeYear
     * @param firstUnitRent
     * @param yearIncring
     * @param rentIncring
     * @return: void
     * @throws
     * @author: sl.qiu
     */
    public  static Map<String,String> continuousComputingRents(int totalMonth, int freeYear, Double firstUnitRent,
                                                               String[] yearIncring, String[] rentIncring) {
        DecimalFormat df=new DecimalFormat(".##");
        Map<String,String> map=new HashMap<String,String>(totalMonth);
        for(int i=1;i<=totalMonth;i++) {
            int result=-1;
            for(int j=0;j<yearIncring.length;j++) {
                if(i<=Integer.parseInt(yearIncring[j])*12) {
                    result=j-1;
                    break;
                }else if(j==yearIncring.length-1) {
                    result=j;
                    break;
                }
            }
            if(result==-1) {
                if(i<=freeYear) {
                    map.put(i+"", "0");
                }else {
                    map.put(i+"", firstUnitRent.toString());
                }
            }else {

                for(int j=0;j<yearIncring.length;j++) {
                    Double firstUnitRentResult=0.00;
                    if(j==result) {
                        Double firstUnitRentResultLast=0.00;
                        if(j==0) {
                            firstUnitRentResultLast=firstUnitRent;

                        }else {
                            firstUnitRentResultLast=Double.parseDouble(map.get(Integer.parseInt(yearIncring[j-1])*12+1+""));
                        }
                        if(j<=0) {

                            int n=0;
                            if(i-Integer.parseInt(yearIncring[j])*12>0) {
                                n=(i-Integer.parseInt(yearIncring[j])*12-1)/12;
                            }
                            firstUnitRentResult=firstUnitRentResultLast*Math.pow(1+Double.parseDouble(rentIncring[j])/100,  n+1);
                        }else {
                            int n=0;
                            if(i-Integer.parseInt(yearIncring[j])*12>0) {
                                n=(i-Integer.parseInt(yearIncring[j])*12-1)/12;
                            }
                            firstUnitRentResult=firstUnitRentResultLast*Math.pow(1+Double.parseDouble(rentIncring[j])/100,  n+1)*Math.pow(1+Double.parseDouble(rentIncring[j-1])/100, Integer.parseInt(yearIncring[j])-Integer.parseInt(yearIncring[j-1])-1);
                        }


                        map.put(i+"", df.format(firstUnitRentResult));

                    }
                }
            }
        }
        return map;
    }
    /**
     * @Title: dayCompare
     * @Description: TODO(计算2个日期之间相差的  以年、月、日为单位，各自计算结果是多少)
     * 比如：2011-02-02 到  2017-03-02
     *      以年为单位相差为：6年
     *      以月为单位相差为：73个月
     *      以日为单位相差为：2220天
     * @param fromDate
     * @param toDate
     * @return
     * @return: DayCompare
     * @throws
     * @author: sl.qiu
     */
    public static Map<String,String>   dayCompare(Date fromDate,Date toDate){
        Map<String,String> map=new HashMap<String,String>(100);
        Calendar  from  =  Calendar.getInstance();
        from.setTime(fromDate);
        Calendar  to  =  Calendar.getInstance();
        to.setTime(toDate);
        //只要年月
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);

        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);

        int year = toYear  -  fromYear;
        int month = toYear *  12  + toMonth  -  (fromYear  *  12  +  fromMonth);
        int day = (int) ((to.getTimeInMillis()  -  from.getTimeInMillis())  /  (24  *  3600  *  1000));
        map.put("year", String.valueOf(year));
        map.put("month", String.valueOf(month));
        map.put("day", String.valueOf(day));
        return map;
    }
    /**
     *
     * @Title: DateAdd
     * @Description: TODO(当前日期加上一个月的时间)
     * @return: void
     * @throws
     * @author: sl.qiu
     */
    public static  Map<String,Object>  dateAdd(String date) {
        Map<String,Object> map=new HashMap<String,Object>(100);
        Calendar calendar = Calendar.getInstance();
        // 指定时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 指定时间
        Date fromDate = null;
        // 将字符串转换为指定的时间格式
        try {
            fromDate = sdf.parse(date);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        calendar.setTime(fromDate);
        String curruntMonth=calendar.get(Calendar.YEAR) + "-"+appendzero(calendar.get(Calendar.MONTH)+1);
        String curruntDay=appendzero(calendar.get(Calendar.DAY_OF_MONTH));
        if("01".equals(curruntDay)) {
            Calendar cal = Calendar.getInstance();
            //设置年份
            cal.set(Calendar.YEAR,calendar.get(Calendar.YEAR));
            //设置月份
            cal.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
            //获取某月最大天数
            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            //设置日历中月份的最大天数
            cal.set(Calendar.DAY_OF_MONTH, lastDay);
            //格式化日期
            SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");

            String lastDayOfMonth = sdfs.format(cal.getTime());
            map.put("endMonthDate", lastDayOfMonth);
            map.put("curruntMonth", curruntMonth);
            calendar.add(Calendar.MONTH, 1);
            //获取年
            int year = calendar.get(Calendar.YEAR);
            //获取月份，0表示1月份
            int month = calendar.get(Calendar.MONTH) + 1;
            String nextStartMonthDate=year + "-"+appendzero(month) + "-" + appendzero(1);
            map.put("nextStartMonthDate", nextStartMonthDate);
        }else {
            //增加一个月
            calendar.add(Calendar.MONTH, 1);
            //获取年
            int year = calendar.get(Calendar.YEAR);
            //获取月份，0表示1月份
            int month = calendar.get(Calendar.MONTH) + 1;
            //获取当前天数
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String endMonthDate=year + "-"+appendzero(month) + "-" + appendzero(day-1);
            String nextStartMonthDate=year + "-"+appendzero(month) + "-" + appendzero(day);
            String lastday = sdf.format(calendar.getTime());
            String zero="01";
            if(zero.equals(curruntDay)) {
                map.put("endMonthDate", lastday);
                map.put("nextStartMonthDate", nextStartMonthDate);
                map.put("curruntMonth", curruntMonth);
            }else {
                map.put("endMonthDate", endMonthDate);
                map.put("nextStartMonthDate", nextStartMonthDate);
                map.put("curruntMonth", curruntMonth);
            }
        }


        return map;


    }

    /**
     * @Title: Appendzero
     * @Description: TODO(自动补零)
     * @return: void
     * @throws
     * @author: sl.qiu
     */
    private static String appendzero(int obj) {
        int day=10;
        if(obj<day) {
            return "0" +""+ obj;
        }else {
            return obj+"";
        }
    }
    public  static String getUserReportPath() {
        StringBuffer sb = new StringBuffer();
        sb.append(MvcConfig.REPORT_PATH_IMPORT_TEMPLATE);
//		if(MvcConfig.REPORT_PATH.lastIndexOf(File.separatorChar)!=MvcConfig.REPORT_PATH.length()-1) {
//			sb.append(File.separatorChar);
//		}
//		sb.append(File.separatorChar);
        String filePatch = sb.toString();
        FileImportUtil.newFolder(filePatch);
        return filePatch;

    }

//    public JSONObject organizedMenuList(List<OrganizedMenu> rootMenu) {
//        // 最后的结果
//        List<OrganizedMenu> menuList = new ArrayList<OrganizedMenu>();
//        // 先找到所有的一级菜单
//        for (int i = 0; i < rootMenu.size(); i++) {
//            // 一级菜单没有parentId
//            if (StringUtils.isBlank(rootMenu.get(i).getParentId())) {
//                menuList.add(rootMenu.get(i));
//            }
//        }
//        // 为一级菜单设置子菜单，getChild是递归调用的
//        for (OrganizedMenu menu : menuList) {
//            menu.setChildMenus(getChild(menu.getId(), rootMenu));
//        }
//        Map<String,Object> jsonMap = new HashMap<>();
//        jsonMap.put("menu", menuList);
//        return JsonUtil.toJSon(jsonMap);
//
//    }
//    /**
//     * 递归查找子菜单
//     *
//     * @param id
//     *            当前菜单id
//     * @param rootMenu
//     *            要查找的列表
//     * @return
//     */
//    private List<OrganizedMenu> getChild(String id, List<OrganizedMenu> rootMenu) {
//        // 子菜单
//        List<OrganizedMenu> childList = new ArrayList<>();
//        for (OrganizedMenu menu : rootMenu) {
//            // 遍历所有节点，将父菜单id与传过来的id比较
//            if (StringUtils.isNotBlank(menu.getParentId())) {
//                if (menu.getParentId().equals(id)) {
//                    childList.add(menu);
//                }
//            }
//        }
//        // 把子菜单的子菜单再循环一遍
//        for (OrganizedMenu menu : childList) {// 没有url子菜单还有子菜单
//            if (StringUtils.isBlank(menu.getUrl())) {
//                // 递归
//                menu.setChildMenus(getChild(menu.getId(), rootMenu));
//            }
//        } // 递归退出条件
//        if (childList.size() == 0) {
//            return null;
//        }
//        return childList;
//    }
    /**
     * 获取租金季度开始日期
     * @Title: entQuarterStartDate
     * @return
     * @return: String
     * @throws
     * @author: sl.qiu
     * @date(创建日期):   2018年10月22日 下午5:06:33
     */
    public static String entQuarterStartDate(String year,int quatier) {

        String date="";
        switch (quatier) {
            case 1:
                date = "01-01";
                break;
            case 2:
                date = "04-01";
                break;
            case 3:
                date = "07-01";
                break;
            case 4:
                date = "10-01";
                break;
        }
        return year+"-"+date;
    }
    /**
     * 获取租金季度结束日期
     * @Title: entQuarterStartDate
     * @return
     * @return: String
     * @throws
     * @author: sl.qiu
     * @date(创建日期):   2018年10月22日 下午5:06:33
     */
    public static String entQuarterEndDate(String year,int quatier) {

        String date="";
        switch (quatier) {
            case 1:
                date = "03-31";
                break;
            case 2:
                date = "06-30";
                break;
            case 3:
                date = "09-30";
                break;
            case 4:
                date = "12-31";
                break;
        }
        return year+"-"+date;
    }
    //字符串去重
    public static String distinctStringWithDot(String str) {
        String[]array=str.split(",");
        List<String> list = new ArrayList<String>();
        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[i].equals(array[j])){
                    j = ++i;
                }
            }
            list.add(array[i]);
        }
        String newStr="";
        for(String s:list){
            newStr=newStr+s+",";
        }
        return newStr;
    }

}

