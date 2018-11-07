package com.tsystems.services.payment.paymentservice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value="/cards")
@Api(value = "cards", description = "Data service operations on cards", tags = "cards")
public class CardController {
    @Autowired
    private CardRepository cardRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get all cards", notes = "Get all cards in the system", nickname = "getCards")
    public List<Card> findAll(@RequestParam(name="cardNumber", required = false)Long cardNumber){
        if(cardNumber!=null){
            return Collections.singletonList(this.cardRepository.findByCardNumber(cardNumber));
        }
        return (List<Card>) this.cardRepository.findAll();
    }

    @RequestMapping(value = "/payment",method = RequestMethod.POST)
    public ResponseEntity<String> subtract(@RequestParam("number") String number,
                                           @RequestParam("cvv") String cvv,
                                           @RequestParam("userName") String userName,
                                           @RequestParam("total")BigDecimal total
//            HttpServletRequest request
    ) throws IOException, ServletException {
//        Card card = (Card) request.getPart("card");
//        Card card1=new Card();
//        card1.setCardNumber(((Card) request.getPart("credit")).getCardNumber());
        Card cardFromDB=this.cardRepository.findByCardNumberAndCvvAndUserName(Long.valueOf(number),cvv,userName);
        BigDecimal balance= cardFromDB.getBalance().subtract( total);
        if (balance.signum()!=-1){
            cardFromDB.setBalance(balance);
            this.cardRepository.save(cardFromDB);
            return (new ResponseEntity<>("Successful",null,HttpStatus.OK));
        }
        else return (new ResponseEntity<>("Failure",null,HttpStatus.FORBIDDEN));
    }

//    @RequestMapping(value = "postformdata", method=RequestMethod.POST, headers="Content-Type=multipart/form-data")
//    public @ResponseBody String handleFormUpload(@RequestParam("description") String description, @RequestParam("file") MultipartFile file) {
//
//        if (!file.isEmpty()) {
//            byte[] bytes = null;
//            try {
//                bytes = file.getBytes();
//            } catch (IOException e) {
//                logger.info("error processing uploaded file", e);
//            }
//            return "file upload received! Name:[" + description + "] Size:[" + bytes.length + "]";
//        } else {
//            return "file upload failed!";
//        }
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = { "application/json" })
    @ResponseBody
    public Card findById(@PathVariable final long id) {
        final Card foo = this.cardRepository.findByCardNumber(id);
        if (foo == null) {
            throw new RuntimeException();
        }
        return foo;
    }

}
