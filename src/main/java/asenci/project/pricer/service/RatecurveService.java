package asenci.project.pricer.service;

import asenci.project.pricer.model.RateCurve;

import java.util.Date;
import java.util.List;

public interface RatecurveService {

    List<RateCurve> getAll();

    RateCurve getOne(Date date);
}
