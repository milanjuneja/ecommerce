package com.ecommerce.service;

import com.ecommerce.modal.Seller;
import com.ecommerce.modal.SellerReport;

public interface SellerReportService {

    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);

}
