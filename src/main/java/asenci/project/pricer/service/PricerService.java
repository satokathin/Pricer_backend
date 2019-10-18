package asenci.project.pricer.service;

import asenci.project.pricer.model.*;

import java.util.Date;
import java.util.List;

public interface PricerService {
    /*List<RateCurve> getAll();
    RateCurve getOne(Date date);
    double interpolation(Date date, double period/*, param = rateCurve ?);*/

    double pricing(Obligation obligation, Date dateToPrice/*, RateCurve rateCurve, InterpolationStrategy interpolationStrategy*/);

    List<PriceCurve> pricingInterval(Obligation obligation, Date initialDate, Date issueDate);

    void setPricerStrategy(PricerStrategy pricerStrategy);

    void setInterpolationStrategy(InterpolationStrategy interpolationStrategy);
}
