package com.grp3.bid.Timer;

import com.grp3.bid.entities.Offer;
import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;
import com.grp3.bid.repositories.ProductDAOInterface;
import com.grp3.bid.services.OfferService;
import com.grp3.bid.services.OfferServiceInterface;
import com.grp3.bid.services.ProductServiceInterface;
import com.grp3.bid.services.UserServiceInterface;
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

    @Scheduled(fixedDelay = 10000)
    public void run() {
        List<Product> productList = productService.getEndedAuctionWithoutBuyer();
        for (Product product : productList) {
            Offer highestOffer = offerService.getActualMaxOffer(product.getId());
            User seller = product.getSeller();
            User buyer = highestOffer.getUser();

            productService.updateBuyer(product, buyer);
            seller.setAccountWallet(seller.getAccountWallet() + highestOffer.getValue());
            userService.updateAccountWallet(seller);
        }
    }
}
