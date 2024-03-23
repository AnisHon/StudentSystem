package com.anishan;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class StudentSystemBackendApplicationTests {

    @Resource
    DataSource dataSource;

    @Test
    void contextLoads() {
        FastAutoGenerator
                .create(new DataSourceConfig.Builder(dataSource))
                .globalConfig(builder -> {
                    builder.author("anishan");              //作者信息，一会会变成注释
                    builder.commentDate("2024-03-23");  //日期信息，一会会变成注释
                    builder.outputDir("src/main/java"); //输出目录设置为当前项目的目录
                })
                //打包设置，这里设置一下包名就行，注意跟我们项目包名设置为一致的
                .packageConfig(builder -> builder.parent("com.anishan"))
                .strategyConfig(builder -> {
                    //设置为所有Mapper添加@Mapper注解
                    builder
                            .mapperBuilder()
                            .enableFileOverride()
                            .mapperAnnotation(Mapper.class)
                            .build();
                })
                .execute();
    }
}
