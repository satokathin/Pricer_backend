package asenci.project.pricer.service;

import asenci.project.pricer.model.*;
import asenci.project.pricer.repository.RateCurveCsv;
import asenci.project.pricer.repository.RateCurveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PricerServiceImpl implements PricerService {

    private RatecurveService/*<Date, RateCurve>*/ ratecurveService;
    private InterpolationStrategy interpolationStrategy;
    private PricerStrategy pricerStrategy;

    public void setPricerStrategy(PricerStrategy pricerStrategy) {
        this.pricerStrategy = pricerStrategy;
    }
    public void setInterpolationStrategy(InterpolationStrategy interpolationStrategy) { this.interpolationStrategy = interpolationStrategy; }

    @Override
    public double pricing(Obligation obligation, Date dateToPrice) {
        return this.pricerStrategy.pricing(obligation, dateToPrice, this.ratecurveService.getOne(dateToPrice), this.interpolationStrategy);
        /*double result = 0.0, alpha = calculatAlpha();
        RateCurve rateCurve = (RateCurve)rateCurveRepository.getOne(dateToPrice);
        if(rateCurve == null)
            return result;
        double period = obligation.getPeriodicity(), coupon = obligation.getCoupon(), nominal = obligation.getNominal();
        double maturity = obligation.getMaturity();
        //RateInterpolation rateInterpolation = new RateInterpolation();
        double first = coupon / Math.pow(1 + alpha, alpha);
        double second = 0.0;
        for (double i = period + alpha; i <= maturity-period; i=i+period) {
            second += coupon/Math.pow(1 + .interpolation(dateToPrice,i), i);
        }
        double third = (nominal+coupon)/Math.pow(1 + interpolationService.interpolation(dateToPrice,maturity), maturity);
        result = first + second + third;
        return result;*/
    }

    @Override
    public List<PriceCurve> pricingInterval(Obligation obligation, Date initialDate, Date issueDate) {
        //Date date
        return this.pricerStrategy.intervalPricing(obligation, initialDate, issueDate, this.ratecurveService.getOne(initialDate), this.interpolationStrategy);
    }

    @Autowired
    public PricerServiceImpl(@Qualifier("getRepo") RatecurveService/*<Date, RateCurve>*/ ratecurveService, @Qualifier("ILinear") InterpolationStrategy interpolationStrategy,
                             @Qualifier("SFixe") PricerStrategy pricerStrategy) {
        this.ratecurveService = ratecurveService;
        //this.ratecurveService.readCsv("src\\main\\resources\\files\\taux2.csv");
        this.interpolationStrategy = interpolationStrategy;
        this.pricerStrategy = pricerStrategy;
    }

    public PricerServiceImpl() {}
    /*@Override
    public List<RateCurve> getAll() {
        return rateCurveRepository.getAll();
    }
    @Override
    public RateCurve getOne(Date date) {
        return (RateCurve)rateCurveRepository.getOne(date);
    }*/
    /*private double numberMax(LinkedHashMap<Double, Double> numberValues, double number) {
        Iterator<Double> it = numberValues.keySet().iterator();
        double result = 0.0;
        while (it.hasNext()){
            Double key = it.next();
            if (key > number){
                result = key;
                break;
            }
        }
        return result;
    }
    private double numberMaxValue(LinkedHashMap<Double, Double> numberValues, double number) {
        Iterator<Double> it = numberValues.keySet().iterator();
        double result = 0.0;
        while (it.hasNext()){
            Double key = it.next();
            if (key > number){
                result = numberValues.get(key);
                break;
            }
        }
        return result;
    }
    private double numberMin(LinkedHashMap<Double, Double> numberValues, double number) {
        Iterator<Double> it = numberValues.keySet().iterator();
        double result = 0.0, tmp = 0.0, key = 0.0;
        while (it.hasNext()){
            tmp = key;
            key = it.next();
            if (key > number){
                result = tmp;
                break;
            }
        }
        return result;
    }
    private double numberMinValue(LinkedHashMap<Double, Double> numberValues, double number) {
        Iterator<Double> it = numberValues.keySet().iterator();
        double result = 0.0, tmp = 0.0, key = 0.0;
        while (it.hasNext()){
            tmp = key;
            key = it.next();
            if (key > number){
                result = numberValues.get(tmp);
                break;
            }
        }
        return result;
    }
    @Override
    public double interpolation(Date date, double period/*, param = rateCurve ?) {
        double rate = 0.0;
        double periodMax = 0.0, periodMin = 0.0, rateMin = 0.0, rateMax = 0.0;
        RateCurve rateCurve = (RateCurve)rateCurveRepository.getOne(date);
        if (rateCurve == null) {
            System.out.println("Wrong date : does not exist in RateCurveCsv !");
            return rate;
        }
        else if (rateCurve.getData().containsKey(period))
            rate = rateCurve.getData().get(period);
        else if (period < rateCurve.getData().keySet().iterator().next()) {
            rateMax = numberMaxValue(rateCurve.getData(), period);
            periodMax = numberMax(rateCurve.getData(), period);
            rate =rateMax*period/periodMax;
        }
        else {
            periodMax = numberMax(rateCurve.getData(), period);
            periodMin = numberMin(rateCurve.getData(), period);
            rateMin = numberMinValue(rateCurve.getData(), period);
            rateMax = numberMaxValue(rateCurve.getData(), period);

            rate = rateMin + (period - periodMin)*(rateMax - rateMin)/(periodMax - periodMin);
        }
        return rate;
    }*/
}
