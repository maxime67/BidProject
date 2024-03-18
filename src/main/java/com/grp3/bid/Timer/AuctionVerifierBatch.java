package com.grp3.bid.Timer;

import com.grp3.bid.entities.Offer;
import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;
import com.grp3.bid.repositories.ProductDAOInterface;
import com.grp3.bid.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.TimerTask;

@Configuration
@EnableScheduling
public class AuctionVerifierBatch {
    @Autowired
    ProductServiceInterface productService;
    @Autowired
    OfferServiceInterface offerService;
    @Autowired
    UserServiceInterface userService;
    @Autowired
    AccountServiceInterface accountService;

    @Scheduled(fixedDelay = 1000)
    public void run() {
        List<Product> productList = productService.getEndedAuctionWithoutBuyer();
        for (Product product : productList) {
            if(null != offerService.getActualMaxOffer(product.getId())){
                Offer highestOffer = offerService.getActualMaxOffer(product.getId());
                User seller = product.getSeller();
                User buyer = highestOffer.getUser();

                accountService.decrementAccount(userService.getUserByid(buyer.getId()), highestOffer.getValue());
                accountService.addToAccount(userService.getUserByid(seller.getId()), highestOffer.getValue());
                seller.setNbSales(seller.getNbSales()+1);
                productService.updateBuyer(product, buyer);
            }
        }
    }
}
