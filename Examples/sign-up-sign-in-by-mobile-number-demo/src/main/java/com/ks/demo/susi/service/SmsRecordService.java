package com.ks.demo.susi.service;

import com.ks.demo.susi.entity.SmsRecordEntity;
import com.ks.demo.susi.mapper.SmsRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsRecordService {
    @Autowired
    private SmsRecordMapper smsRecordMapper;

    public void record(SmsRecordEntity smsRecordEntity) {
        smsRecordMapper.insert(smsRecordEntity);
    }

    public void updateStatus(SmsRecordEntity smsRecordEntity) {
        smsRecordMapper.updateStatus(smsRecordEntity);
    }
}
