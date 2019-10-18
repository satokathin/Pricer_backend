package asenci.project.pricer.model;

import asenci.project.pricer.service.RatecurveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("SFixe")
public class PricerStrategyFixe implements PricerStrategy {

    private RatecurveService ratecurveService;

    @Override
    public double pricing(Obligation obligation, Date dateToPrice, RateCurve rateCurve, InterpolationStrategy interpolationStrategy) {
        double result = 0.0, alpha = calculatAlpha();
        //RateCurve rateCurve = (RateCurve)rateCurveRepository.getOne(dateToPrice);
        /*if(rateCurve == null)
            return result;*/
        double period = obligation.getPeriodicity(), coupon = obligation.getCoupon(), nominal = obligation.getNominal();
        double maturity = obligation.getMaturity();
        //RateInterpolation rateInterpolation = new RateInterpolation();
        double first = coupon / Math.pow(1 + alpha, alpha);
        double second = 0.0;
        for (double i = period + alpha; i <= maturity-period; i=i+period) {
            if (rateCurve == null)
                return result;
            second += coupon/Math.pow(1 + interpolationStrategy.interpolation(dateToPrice,i, rateCurve), i);
        }
        double third = (nominal+coupon)/Math.pow(1 + interpolationStrategy.interpolation(dateToPrice,maturity, rateCurve), maturity);
        result = first + second + third;

        return result;
    }

    @Override
    public List<PriceCurve> intervalPricing(Obligation obligation, Date initialDate, Date issueDate, RateCurve rateCurve, InterpolationStrategy interpolationStrategy) {
        List<PriceCurve> result = new ArrayList<>();
        List<Date> dates = getDatesBetweenUsingJava7(initialDate, issueDate);
        for (Date date : dates) {
            PriceCurve priceCurve = new PriceCurve();
            rateCurve = ratecurveService.getOne(date);
            if (rateCurve != null) {
                priceCurve.setDate(date);
                priceCurve.setPrice(pricing(obligation, date, rateCurve, interpolationStrategy));
                result.add(priceCurve);
            }
        }
        return result;
    }

    private double calculatAlpha(/*Date dateToPrice*/) {
        double result = 0.4301369863;
        //dateToPrice.getTime() -
        return result;
    }

    private List<Date> getDatesBetweenUsingJava7(Date startDate, Date endDate) {
        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            Date result = calendar.getTime();
            datesInRange.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return datesInRange;
    }

    @Autowired
    public PricerStrategyFixe(RatecurveService ratecurveService) {
        this.ratecurveService = ratecurveService;
    }
}
