package asenci.project.pricer.model;

import asenci.project.pricer.repository.RateCurveCsv;
import asenci.project.pricer.repository.RateCurveRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

@Component("ILinear")
public class InterpolationStrategyLinear implements InterpolationStrategy {

    /*private RateCurveRepository rateCurveRepository;

    public InterpolationStrategyLinear(@Qualifier("csvRepository") RateCurveRepository rateCurveRepository) {
        this.rateCurveRepository = rateCurveRepository;
        ((RateCurveCsv) this.rateCurveRepository).readCsv("src\\main\\resources\\files\\taux2.csv");
    }*/

    @Override
    public double interpolation(Date date, double period, RateCurve rateCurve) {
        double rate = 0.0;
        double periodMax = 0.0, periodMin = 0.0, rateMin = 0.0, rateMax = 0.0;
        //RateCurve rateCurve = (RateCurve)rateCurveRepository.getOne(date);
        /*if (rateCurve == null) {
            System.out.println("Wrong date : does not exist in RateCurveCsv !");
            return rate;
        }else */
        if (rateCurve.getData().containsKey(period))
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
    }

    private double numberMax(LinkedHashMap<Double, Double> numberValues, double number) {
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
}
