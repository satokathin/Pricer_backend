package asenci.project.pricer.controller;

import asenci.project.pricer.model.*;
import asenci.project.pricer.service.PricerService;
import asenci.project.pricer.service.RatecurveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/pricer")
@CrossOrigin
public class PricerController {

    private PricerService pricerService;

    @GetMapping("price")
    public double getPricing(@RequestParam double nominal, @RequestParam double periodicity, @RequestParam double coupon, @RequestParam double maturity,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date date){
        Obligation obligation = new Obligation(nominal, periodicity, coupon, maturity);
        return pricerService.pricing(obligation, date);
    }

    @GetMapping("price/{date}")
    public double getPricing2(@RequestParam double nominal, @RequestParam double periodicity, @RequestParam double coupon, @RequestParam double maturity,
                            @DateTimeFormat(pattern = "yyyy-MM-dd"/*"dd-MM-yyyy"*/) @PathVariable Date date) {
        Obligation obligation = new Obligation(nominal, periodicity, coupon, maturity);
        return pricerService.pricing(obligation, date);
    }

    @GetMapping("prices")
    public List<PriceCurve> getIntervalPricing(@RequestParam double nominal, @RequestParam double periodicity, @RequestParam double coupon, @RequestParam double maturity,
                                               @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date initialDate, @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date issueDate){
        Obligation obligation = new Obligation(nominal, periodicity, coupon, maturity);
        return pricerService.pricingInterval(obligation, initialDate,issueDate);
    }

    @Autowired
    public PricerController(PricerService pricerService) {
        this.pricerService = pricerService;
        /*this.pricerService.setPricerStrategy();this.pricerService.setInterpolationStrategy();*/
    }

    /*@GetMapping("ratecurves")
    public List<RateCurve> getAllRateCurve() {
        return ratecurveService.getAll();
    }
    @GetMapping("ratecurve")
    public RateCurve getRateCurveByDate(@RequestParam String day, @RequestParam String month, @RequestParam String year) throws ParseException {
        String date = day + "/" + month + "/" + year;
        return ratecurveService.getOne(formatter.parse(date));
    }
    @GetMapping("ratecurves/{date}")
    public RateCurve getRateCurveByDate3(@DateTimeFormat(pattern = "dd-MM-yyyy") @PathVariable Date date) throws ParseException {
        return ratecurveService.getOne(date);
    }
    @GetMapping("oneratecurve")
    public RateCurve getRateCurveByDate2(@DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam Date date) throws ParseException {
        return ratecurveService.getOne(date);
    }
    @GetMapping("interpolation/{period}")
    public double getRateInterpolation(@RequestParam String day, @RequestParam String month, @RequestParam String year, @PathVariable double period) throws ParseException {
        String date = day + "/" + month + "/" + year;
        RateCurve rateCurve = ratecurveService.getOne(formatter.parse(date));
        return interpolationStrategy.interpolation(formatter.parse(date), period,rateCurve);
    }*/
}
