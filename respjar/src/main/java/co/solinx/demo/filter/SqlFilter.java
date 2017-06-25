package co.solinx.demo.filter;

/**
 * Created by xin on 2017-06-24.
 */
public class SqlFilter {
    public boolean filter(Object sql){
        boolean ret=false;
        System.out.println(String.format("sql filter : %s ",sql));
        if(sql.toString().contains("1=1")){
            ret=true;
        }
        return ret;
    }
}
