package asenci.project.pricer.model;

import java.util.Date;

public interface InterpolationStrategy {
    double interpolation(Date date, double period, RateCurve rateCurve);
}
