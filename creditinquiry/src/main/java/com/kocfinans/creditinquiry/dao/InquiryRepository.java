package com.kocfinans.creditinquiry.dao;

import com.kocfinans.creditinquiry.entity.Inquiry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InquiryRepository extends CrudRepository<Inquiry, Long> {

    List<Inquiry> findAll();

    Inquiry getInquiryById(Long inquiryId);

    List<Inquiry> getInquiryByInquirerId(Long inquirerId);

}
