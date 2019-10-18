package asenci.project.pricer.service;

import asenci.project.pricer.model.RateCurve;
import asenci.project.pricer.repository.RateCurveCsv;
import asenci.project.pricer.repository.RateCurveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("getRepo")
public class RatecurveServiceImpl implements RatecurveService {

    private RateCurveRepository rateCurveRepository;

    @Override
    public List<RateCurve> getAll() {
        return rateCurveRepository.getAll();
    }

    @Override
    public RateCurve getOne(Date date) {
        return (RateCurve)rateCurveRepository.getOne(date);
    }

    @Autowired
    public RatecurveServiceImpl(@Qualifier("csvRepository") RateCurveRepository rateCurveRepository) {
        this.rateCurveRepository = rateCurveRepository;
        ((RateCurveCsv) this.rateCurveRepository).readCsv("src\\main\\resources\\files\\taux2.csv");
    }
}
