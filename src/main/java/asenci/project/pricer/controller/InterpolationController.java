package asenci.project.pricer.controller;

import asenci.project.pricer.model.InterpolationStrategy;
import asenci.project.pricer.model.RateCurve;
import asenci.project.pricer.service.RatecurveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("api/pricer")
@CrossOrigin
public class InterpolationController {
    private RatecurveService ratecurveService;
    private InterpolationStrategy interpolationStrategy;

    @GetMapping("interpolation/{period}")
    public double getRateInterpolation(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Date date, @PathVariable double period) {
        RateCurve rateCurve = ratecurveService.getOne(date);
        return interpolationStrategy.interpolation(date, period, rateCurve);
    }

    @Autowired
    public InterpolationController(RatecurveService ratecurveService, InterpolationStrategy interpolationStrategy) {
        this.ratecurveService = ratecurveService;
        this.interpolationStrategy = interpolationStrategy;
    }
}
