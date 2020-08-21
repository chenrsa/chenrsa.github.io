package com.example.demo.constant;

/**
 * @author chenrunzheng
 * @since 2020/7/13 13:56
 *
 * 懒汉式
 */
 public class Single {
/*    private static final Single s = new Single();
    private Single(){}
    public static Single getInstance()
    {
        return s;
    }*/
 /*   private static Single s = null;
    private Single(){}
    public static Single getInstance()
    {
        if(s==null)
        {
            synchronized(Single.class)
            {
                if(s==null)
                    s = new Single();
            }
        }
        return s;
    }*/
        private static Single instance=null;

        private Single() {};

        public static  Single getInstance(){

            if (instance == null) {
                synchronized (Single.class) {
                    instance=new Single();
                }
            }
            return instance;
        }

}
