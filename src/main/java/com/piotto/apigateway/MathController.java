package com.piotto.apigateway;

import com.piotto.apigateway.exceptions.UnsupportedMathOperationException;
import com.piotto.apigateway.utils.MathUtils;
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
                      @PathVariable(value = "numberTwo") String numberTwo) {
        if (!MathUtils.isNumeric(numberOne) || !MathUtils.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Invalid numbers");
        }
        return MathUtils.convertToDouble(numberOne) + MathUtils.convertToDouble(numberTwo);
    }

    @RequestMapping(
            value = "/sub/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double subtract(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
            ) {
        if (!MathUtils.isNumeric(numberOne) || !MathUtils.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Invalid numbers");
        }

        return MathUtils.convertToDouble(numberOne) - MathUtils.convertToDouble(numberTwo);
    }

    @RequestMapping(
            value = "/div/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double division(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
            ) {
        if (!MathUtils.isNumeric(numberOne) || !MathUtils.isNumeric(numberTwo) || !MathUtils.isZero(numberTwo)) {
            throw new UnsupportedMathOperationException("Invalid numbers");
        }

        return MathUtils.convertToDouble(numberOne) / MathUtils.convertToDouble(numberTwo);
    }

    @RequestMapping(
            value = "/mul/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double multiply(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
            ) {
        if (!MathUtils.isNumeric(numberOne) || !MathUtils.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Invalid numbers");
        }

        return MathUtils.convertToDouble(numberOne) * MathUtils.convertToDouble(numberTwo);
    }
}
