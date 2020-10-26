package com.kocfinans.creditinquiry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kocfinans.creditinquiry.entity.Inquiry;
import com.kocfinans.creditinquiry.service.InquiryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InquiryServiceTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private InquiryService inquiryService;

    //List test cases here
    @Before
    public void setUp(){

        for(int i = 1; i < 7; i++){
            Inquiry inquiry = new Inquiry();
            inquiry.setInquiryId((long) i);
            if(i < 3){
                inquiry.setInquiryResult("Onay");
            } else {
                inquiry.setInquiryResult("Ret");
            }

            inquiry.setInquirerId((long) i);
            System.out.println(inquiry.toString());
        }

        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void saveInquiryResultTest() throws Exception{
        Inquiry inquiry = new Inquiry();
        inquiry.setInquirerId(30043886934L);
        inquiry.setInquirerIncome(5000L);
        inquiry.setInquirerName("Alp");
        inquiry.setInquirerLastName("Tunçay");
        inquiry.setInquirerPhoneNumber("5384420442");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(inquiry);

        this.mockMvc.perform(post("/inquiry")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.inquirerId").value(30043886934L))
                .andExpect(jsonPath("$.inquirerIncome").value(5000L))
                .andExpect(jsonPath("$.inquirerName").value("Alp"))
                .andExpect(jsonPath("$.inquirerLastName").value("Tunçay"))
                .andExpect(jsonPath("$.inquirerPhoneNumber").value("5384420442"));;
    }

    @Test
    public void getAllInquiriesTest() throws Exception{
        this.mockMvc.perform(get("/inquiry/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void getInquiryByIdTest() throws Exception{
        this.mockMvc.perform(get("/inquiry/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.inquirerId").value(30043886934L))
                .andExpect(jsonPath("$.inquiryResult").value("Onay"));
    }

}
