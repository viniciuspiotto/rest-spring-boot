package com.piotto.apigateway;

import com.piotto.apigateway.exceptions.UnsupportedMathOperationException;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(
            value ="/sum/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double sum(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo) throws Exception {
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Invalid numbers");
        }
        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        String number = str.replace(",", ".");
        return str.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private Double convertToDouble(String number) {
        if (number == null) {
            return null;
        }
        number = number.replace(",", ".");
        return Double.parseDouble(number);
    }
}
