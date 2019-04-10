import com.ayuantalking.redis.MiaoshaUserKeyPrefix;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 10:42
 * @Version 1.0
 */
public class Test {

    @org.junit.Test
    public void test(){
        List<Person> list = new ArrayList<Person>();
        People p = new People();
        list.add(p);
    }

    @org.junit.Test
    public void test1(){
        People people = new People();

        if(people instanceof Person){
            System.out.println("11111111");
        }else{
            System.out.println("22222222");
        }

    }
}
