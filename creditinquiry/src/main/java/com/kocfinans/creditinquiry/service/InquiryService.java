package com.kocfinans.creditinquiry.service;

import com.kocfinans.creditinquiry.dao.InquiryRepository;
import com.kocfinans.creditinquiry.entity.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    public List<Inquiry> getInquiries() {
        return inquiryRepository.findAll();
    }

    public void saveInquiry(Inquiry inquiry) {
        inquiryRepository.save(inquiry);
    }

    public Inquiry getInquiryById(Long inquiryId) {
        return inquiryRepository.getInquiryById(inquiryId);
    }

    public List<Inquiry> getInquiryByInquirerId(Long inquirerId) {
        return inquiryRepository.getInquiryByInquirerId(inquirerId);
    }

    public void deleteInquiry(Long inquiryId) {
        inquiryRepository.deleteById(inquiryId);
    }
}
