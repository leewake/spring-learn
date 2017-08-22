package com.pangpang;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
* @SpringApplicationConfiguration(PoiDownloadApplication.class)
* 已经被弃用
* 改用@SpringBootTest可以达到相同效果
*
* 最开始没有加这些注解时，一直报错，是因为没有启动Springboot项目
 *
 * 另外，进行此项测试，需要将ShiroConfig中Configuration注掉
*
**/

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class PoiDownloadApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void helloControllerTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/hello")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("name", "pangpang")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

}
