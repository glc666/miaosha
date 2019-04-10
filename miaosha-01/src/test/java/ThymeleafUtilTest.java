import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/27/0027 11:29
 * @Version 1.0
 */
public class ThymeleafUtilTest {

    private static String prefix="templates/";
    private static String suffix=".html";

    @Test
    public void test() throws IOException {
        // 构造模板引擎
        ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
        classLoaderTemplateResolver.setPrefix(prefix);
        classLoaderTemplateResolver.setSuffix(suffix);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(classLoaderTemplateResolver);


        // 分装数据
        Context context =  new Context();
        context.setVariable("message","hello thymeleaf");

        // 模板输出
        //FileWriter writer = new FileWriter("D:\\hello.html");
        // templateEngine.process("hello",context,writer);
        String temp = templateEngine.process("hello", context);
        System.out.println(temp);
    }
}
