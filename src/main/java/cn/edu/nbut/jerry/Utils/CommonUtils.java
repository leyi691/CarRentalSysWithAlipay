package cn.edu.nbut.jerry.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommonUtils {
    /**
     * 创建一个基于当前时间（精确到秒）+ 3位随机数的随机数
     *
     * @return 当前时间（精确到秒）+ 3位随机数的随机数
     */
    public static String getRandomNumberString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        int b = (int) (Math.random() * 1000);
        return b + simpleDateFormat.format(date);
    }

    /**
     * 例子：
     * Date now = new Date();
     * <p>
     * addAndSubtractDaysByGetTime(now,-5);
     * addAndSubtractDaysByGetTime(now,5);
     *
     * @param dateTime  待处理的时间
     * @param n         加减天数
     * @return          加减后的时间
     */
    public static Date addAndSubtractDaysByGetTime(Date dateTime/*待处理的日期*/, int n/*加减天数*/) {

        //日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(df.format(new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L)));
        //System.out.println(dd.format(new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L)));
        //注意这里一定要转换成Long类型，要不n超过25时会出现范围溢出，从而得不到想要的日期值
        return new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L);
    }

    /**
     * 例子
     * Date now = new Date();
     * <p>
     * addAndSubtractDaysByCalendar(now,-5);
     * addAndSubtractDaysByCalendar(now,5);
     * 加减天数
     * @param dateTime 待处理的日期
     * @param n        加减的天数
     * @return         加减后的日期
     */
    public static Date addAndSubtractDaysByCalendar(Date dateTime/*待处理的日期*/, int n/*加减天数*/) {

        //日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        java.util.Calendar calstart = java.util.Calendar.getInstance();
        calstart.setTime(dateTime);

        calstart.add(java.util.Calendar.DAY_OF_WEEK, n);

        System.out.println(df.format(calstart.getTime()));
        //System.out.println(dd.format(calstart.getTime()));
        return calstart.getTime();
    }

    /**
     * 加减对应时间后的日期
     *
     * @param date   需要加减时间的日期
     * @param amount 加减的时间(毫秒)
     * @return       加减对应时间后的日期
     */
    public static Date subtractTime(Date date/*需要加减时间的日期*/, int amount/*加减的时间(毫秒)*/) {
        try {
            return new Date(date.getTime() + amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回 相对应的页面
     *
     * @param pageNumber 第几页
     * @param list       传入所有记录
     * @param pageSize   页面容量（几页）
     * @return 返回页面相对应的记录
     */
    public static List<?> getSpecificPage(int pageNumber/*第几页*/, List<?> list/*传入所有记录*/, int pageSize/*页面容量（几页）*/) {
        List<?> returnList = null;
        float pagesCountFloat = (float) (list.size() / (pageSize * 1.0));
        int pagesCount = list.size() / pageSize;
        boolean flag = false;
        // 如果 circleCount == isInteger，说明记录数量刚好是10的倍数。
        if (pagesCountFloat > pagesCount && pagesCount != 0) {
            // 如果记录数量不是 10 的倍数，说明总页面数量需要 + 1;
            pagesCount++;
            flag = true;
        }
        if (pagesCount == 0) {
            // 如果记录数量只有一页
            returnList = list;
//            System.out.println("circleCountInt == 0 时候 " + returnList);
        } else {
            for (int i = 0; i < (list.size() / pageSize); i++) {
                // 循环将相应的页面数量分给orders
                if (i == pageNumber) {
                    // 关键代码：输出页面需要的相应的记录
                    returnList = list.subList(i * pageSize, i * pageSize + pageSize);
//                    System.out.println("i == pageNumber 时候 " + returnList);
                    break;
                }
            }
            if (flag && pagesCount - 1 == pageNumber) { // pagesCount - 1 是因为前面已经加过 1 了，要回归
                // 如果记录数不是 10 的倍数，就需要将最后几个遗漏的记录放在里面。
                returnList = list.subList((pagesCount - 1) * pageSize, list.size());
            }
        }
        return returnList;
    }

//    public static List<?> getSpecificPageTest(int pageNumber, List<?> list) {
//        List<?> returnList = null;
//        float circleCountFloat = (float) (list.size() / (10 * 1.0));
//        int circleCountInt = list.size() / 10;
//        boolean flag = false;
//        // 如果 circleCount == isInteger，说明记录数量刚好是10的倍数。
//        if (circleCountFloat > circleCountInt) {
//            // 如果记录数量不是 10 的倍数，说明总页面数量需要 + 1;
//            circleCountInt++;
//            flag = true;
//        }
//        if (circleCountInt == 0) {
//            // 如果记录数量只有一页
//            returnList = list;
//            System.out.println("circleCountInt == 0 时候 ");
//        } else {
//            for (int i = 0; i < (list.size() / 10); i++) {
//                // 循环将相应的页面数量分给orders
//                if (i == pageNumber) {
//                    // 关键代码：输出页面需要的相应的记录
//                    returnList = list.subList(i * 10, i * 10 + 10);
//                    System.out.println("i == pageNumber 时候 " + i);
//                    break;
//                }
//            }
//            if (flag && circleCountInt - 1 == pageNumber) {
//                // 如果记录数不是 10 的倍数，就需要将最后几个遗漏的记录放在里面。
//                System.out.println("记录数不是 10 的倍数 时候 ");
//                returnList = list.subList((circleCountInt - 1) * 10, list.size());
//            }
//        }
//        return returnList;
//    }

}
