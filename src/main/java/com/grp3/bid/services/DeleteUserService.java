package com.grp3.bid.services;

import com.grp3.bid.entities.Offer;
import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;
import com.grp3.bid.repositories.OfferDAOInterface;
import com.grp3.bid.repositories.ProductDAO;
import com.grp3.bid.repositories.ProductDAOInterface;
import com.grp3.bid.repositories.UserDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;

@Service
public class DeleteUserService implements DeleteUserInterface {
    @Autowired
    OfferDAOInterface offerDAO;
    @Autowired
    ProductDAOInterface productDAO;
    @Autowired
    UserDAOInterface userDAO;
    @Autowired
    AccountServiceInterface accountService;

    @Override
    public boolean deleteUser(User user) {
//        Check if target user contain products
//        if (!productDAO.getProductListByIdSeller(user).isEmpty()) {
////          Credit users the amount of placed bid
//            for (Product product : productDAO.getProductListByIdSeller(user)) {
//                if (offerDAO.isOfferExistOnProduct(product.getId())) {
//                    Offer currentOffer = offerDAO.getActualMaxOffer(product.getId());
//                    User currentUser = currentOffer.getUser();
////                    Final update
//                    accountService.addToAccount(currentUser, currentOffer.getValue());
//                }
//            }
//        }
////        Check if target user have offers on products
//        if (!offerDAO.getOfferByUser(user).isEmpty()) {
//            for (Offer offer : offerDAO.getOfferByUser(user)) {
////                Check if user offers are on the top
//                if (offerDAO.getActualMaxOffer(offer.getProduct().getId()).equals(offer)) {
////                    Get precedent user, if exist
//                        User secondUser = offerDAO.getAllOffersByproduct(offer.getProduct().getId()).getUser();
////                      Final update
//                        accountService.decrementAccount(secondUser, offer.getValue());
//                }
//            }
//        }
        if(userDAO.deleteUser(user)){
            return true;
        }
        return false;
    }
}
