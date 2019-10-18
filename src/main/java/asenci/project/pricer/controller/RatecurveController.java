package asenci.project.pricer.controller;

import asenci.project.pricer.model.RateCurve;
import asenci.project.pricer.service.RatecurveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/pricer")
@CrossOrigin
public class RatecurveController {

    private RatecurveService ratecurveService;

    @GetMapping("ratecurves")
    public List<RateCurve> getAllRateCurve() {
        return ratecurveService.getAll();
    }

    @GetMapping("ratecurves/{date}")
    public RateCurve getRateCurveByDate3(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable Date date) {
        return ratecurveService.getOne(date);
    }

    @GetMapping("ratecurve")
    public RateCurve getRateCurveByDate2(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date date)  {
        return ratecurveService.getOne(date);
    }

    @Autowired
    public RatecurveController(RatecurveService ratecurveService) {
        this.ratecurveService = ratecurveService;
    }
}
