package com.ayuantalking;

import org.junit.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: ayuantalking
 * @Date: 2019/3/26/0026 23:35
 * @Version 1.0
 */
public class ThymeleafUtil {

    @Test
    public void test() throws IOException {
        ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
        classLoaderTemplateResolver.setPrefix("static/");
        classLoaderTemplateResolver.setSuffix(".html");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(classLoaderTemplateResolver);

        FileWriter writer = new FileWriter("d://demo.html");
        Context content = new Context();
        content.setVariable("name","jack");
        String demo = templateEngine.process("demo", content);
        System.out.println(demo);

    }
}
