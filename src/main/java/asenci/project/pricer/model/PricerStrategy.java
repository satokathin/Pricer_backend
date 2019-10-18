package asenci.project.pricer.model;

import java.util.Date;
import java.util.List;

public interface PricerStrategy {

    double pricing(Obligation obligation, Date dateToPrice, RateCurve rateCurve, InterpolationStrategy interpolationStrategy);

    List<PriceCurve> intervalPricing(Obligation obligation, Date initialDate, Date issueDate, RateCurve rateCurve, InterpolationStrategy interpolationStrategy);
}
