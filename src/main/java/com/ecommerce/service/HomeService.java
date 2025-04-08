package com.ecommerce.service;

import com.ecommerce.modal.Home;
import com.ecommerce.modal.HomeCategory;

import java.util.List;

public interface HomeService {

    Home createHomePageData(List<HomeCategory> allCategories);

}
