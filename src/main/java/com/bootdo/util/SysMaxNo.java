package com.bootdo.util;

/**
 * @ClassName SysMaxNo
 * @Description 生成系统最大号
 * @Author ZQ
 * @Date 2019/3/22 16:25
 */
public class SysMaxNo {
    private String maxNo;

    private static volatile SysMaxNo sysMaxNo = null;

    private SysMaxNo() {
    }

    /**
     * @return com.bootdo.util.SysMaxNo
     * @Author ZQ
     * @Description //得到该实例
     * @Date 2019/3/22 17:07
     * @Param []
     **/
    public static SysMaxNo getInstance() {
        if (null == sysMaxNo) {
            synchronized (SysMaxNo.class) {
                return sysMaxNo = new SysMaxNo();
            }
        }
        return sysMaxNo;
    }


    /**
     * @return java.lang.String
     * @Author ZQ
     * @Description todo //生成系统最大号
     * 思路： 首先判断该号库中是否有，如果有的话
     * @Date 2019/3/22 16:26
     * @Param [k]
     **/
    public SysMaxNo maxNo(String k) {

        return sysMaxNo;
    }

    /**
     * @return java.lang.String
     * @Author ZQ
     * @Description //得到最大号
     * @Date 2019/3/22 17:10
     * @Param []
     **/
    public String getMaxNo() {
        return maxNo;
    }
}
