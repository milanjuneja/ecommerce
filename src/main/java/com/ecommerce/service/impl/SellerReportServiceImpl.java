package com.ecommerce.service.impl;

import com.ecommerce.modal.Seller;
import com.ecommerce.modal.SellerReport;
import com.ecommerce.repository.SellerReportRepository;
import com.ecommerce.service.SellerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerReportServiceImpl implements SellerReportService {

    @Autowired
    private SellerReportRepository sellerReportRepository;
    @Override
    public SellerReport getSellerReport(Seller seller) {
        SellerReport sellerReport = sellerReportRepository.findBySellerId(seller.getId());
        if(sellerReport == null){
            SellerReport newReport = new SellerReport();
            newReport.setSeller(seller);
            return sellerReportRepository.save(newReport);
        }
        return sellerReport;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {
        return sellerReportRepository.save(sellerReport);
    }
}
