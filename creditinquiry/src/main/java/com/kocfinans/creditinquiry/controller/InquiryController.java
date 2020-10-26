package com.kocfinans.creditinquiry.controller;

import com.kocfinans.creditinquiry.entity.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.kocfinans.creditinquiry.service.InquiryService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Random;

@CrossOrigin
@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    private static final long CREDIT_LIMIT_COEF = 4;

    @Autowired
    private InquiryService inquiryService;

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllInquiries(){
        List<Inquiry> inquiries = inquiryService.getInquiries();
        if(inquiries.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        } else {
            return ResponseEntity.ok(inquiries);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInquiryById(@PathVariable Long id){
        Inquiry inquiry = inquiryService.getInquiryById(id);

        if(inquiry == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        } else {
            return ResponseEntity.ok(inquiry);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/inquirer/{inquirerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInquiryByInquirerId(@PathVariable Long inquirerId){
        List<Inquiry> inquiries = inquiryService.getInquiryByInquirerId(inquirerId);

        if(inquiries.size() == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        } else {
            return ResponseEntity.ok(inquiries);
        }
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveInquiry(@RequestBody Inquiry inquiry){

        Random creditScoreRandomizer = new Random();
        int upperRange = 1900;

        int creditScore =  creditScoreRandomizer.nextInt(upperRange);

        System.out.println(creditScore);

        if(creditScore <= 500){
            inquiry.setInquiryResult("Ret");
            inquiry.setCreditLimit(0L);
        } else if(creditScore <= 1000){
            if(inquiry.getInquirerIncome() < 5000){
                inquiry.setInquiryResult("Ret");
                inquiry.setCreditLimit(0L);
            } else {
                inquiry.setInquiryResult("Onay");
                inquiry.setCreditLimit(10000L);
            }
        } else {
            inquiry.setInquiryResult("Onay");

            long creditLimit = inquiry.getInquirerIncome() * CREDIT_LIMIT_COEF;

            inquiry.setCreditLimit(creditLimit);
        }

        try{
            inquiryService.saveInquiry(inquiry);
            sendSMSMessage(inquiry);
            return ResponseEntity.status(HttpStatus.CREATED).body(inquiry);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Sorgulama yapılamadı.");
        }
    }

    public void sendSMSMessage(Inquiry inquiry){
        String message;
        if(inquiry.getInquiryResult().equals("Onay")) {
            message = String.format("Sayın %s %s, %d TL değerindeki kredi başvurunuz onaylanmıştır.",
                    inquiry.getInquirerName(), inquiry.getInquirerLastName(), inquiry.getCreditLimit());
        } else {
            message = String.format("Sayın %s %s, kredi başvurunuz reddedilmiştir.",
                    inquiry.getInquirerName(), inquiry.getInquirerLastName());
        }

        try {
            URL url = new URL("http://panel.vatansms.com/panel/smsgonder1Npost.php");
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = (HttpURLConnection) urlConnection;
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            OutputStream out = connection.getOutputStream();
            OutputStreamWriter wout = new OutputStreamWriter(out, "UTF-8");
            wout.write("data=<sms>"+
                    "<kno>40014</kno>"+
                    "<kulad>905384420442</kulad>"+
                    "<sifre>42cHRY04</sifre>"+
                    "<gonderen>AlpT96</gonderen>"+ // Bu kısımda sorun var. APIye doğru request gitmiyor.
                    "<mesaj>" + message + "</mesaj>"+
                    "<numaralar>" + inquiry.getInquirerPhoneNumber() + "</numaralar>"+
                    "<tur>Normal</tur>"+
                    "</sms>");

            wout.flush();
            out.close();
            InputStream in = connection.getInputStream();
            int c;
            while ((c = in.read()) != -1) System.out.write(c);
            System.out.println();
            in.close();
            out.close();
            connection.disconnect();
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
