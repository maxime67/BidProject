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
            Offer highestOffer = offerService.getActualMaxOffer(product.getId());
            User seller = product.getSeller();
            User buyer = highestOffer.getUser();
            product.setBuyer(buyer);
            productService.updateBuyer(product, buyer);
            accountService.decrementAccount(buyer, highestOffer.getValue());
            accountService.addToAccount(seller, highestOffer.getValue());
            System.out.println("New Buyer : " + product.getBuyer().getEmail() + " For The Product : " + product.getName());
        }
    }
}
