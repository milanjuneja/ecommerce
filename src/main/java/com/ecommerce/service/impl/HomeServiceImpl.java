package com.ecommerce.service.impl;

import com.ecommerce.domain.HomeCategorySection;
import com.ecommerce.modal.Deal;
import com.ecommerce.modal.Home;
import com.ecommerce.modal.HomeCategory;
import com.ecommerce.repository.DealRepository;
import com.ecommerce.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private DealRepository dealRepository;
    @Override
    public Home createHomePageData(List<HomeCategory> allCategories) {
        List<HomeCategory> gridCategories = allCategories.stream()
                .filter(e -> e.getSection().equals(HomeCategorySection.GRID)).toList();

        List<HomeCategory> shopByCategories = allCategories.stream()
                .filter(e -> e.getSection().equals(HomeCategorySection.SHOP_BY_CATEGORIES)).toList();

        List<HomeCategory> electricCategories = allCategories.stream()
                .filter(e -> e.getSection().equals(HomeCategorySection.ELECTRIC_CATEGORIES)).toList();

        List<HomeCategory> dealCategories = allCategories.stream()
                .filter(e -> e.getSection().equals(HomeCategorySection.DEAL)).toList();

        List<Deal> createdDeals;

        if(dealRepository.findAll().isEmpty()){
            List<Deal> deals = dealCategories.stream()
                    .map(c -> new Deal(null, 10, c))
                    .toList();
            createdDeals = dealRepository.saveAll(deals);
        }else createdDeals = dealRepository.findAll();

        Home home = new Home();
        home.setGrid(gridCategories);
        home.setShopByCategories(shopByCategories);
        home.setElectricCategories(electricCategories);
        home.setDeals(createdDeals);
        home.setDealCategories(dealCategories);

        return home;

    }
}
