package asenci.project.pricer.repository;

import asenci.project.pricer.model.RateCurve;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository("csvRepository")
public class RateCurveCsv implements RateCurveRepository<Date, RateCurve> {

    private List<Double> periods = new ArrayList<>(Arrays.asList(0.25, 0.5, 0.75, 1., 1.25, 1.5, 1.75, 2., 2.25, 2.5, 2.75, 3., 3.25, 3.5, 3.75, 4., 4.25, 4.5, 4.75, 5.,
            5.25, 5.5, 5.75, 6., 6.25, 6.5, 6.75, 7., 7.25, 7.5, 7.75, 8., 8.25, 8.5, 8.75, 9., 9.25, 9.5, 9.75, 10., 10.25, 10.5, 10.75, 11., 11.25, 11.5, 11.75, 12.,
            12.25, 12.5, 12.75, 13., 13.25, 13.5, 13.75, 14., 14.25, 14.5, 14.75, 15., 15.25, 15.5, 15.75, 16., 16.25, 16.5, 16.75, 17., 17.25, 17.5, 17.75, 18., 18.25, 18.5, 18.75, 19.,
            19.25, 19.5, 19.75, 20., 20.25, 20.5, 20.75, 21., 21.25, 21.5, 21.75, 22., 22.25, 22.5, 22.75, 23., 23.25, 23.5, 23.75, 24., 24.25, 24.5, 24.75, 25., 25.25, 25.5, 25.75, 26.,
            26.25, 26.5, 26.75, 27., 27.25, 27.5, 27.75, 28., 28.25, 28.5, 28.75, 29., 29.25, 29.5, 29.75, 30.));
    private HashMap<Date, RateCurve> data = new HashMap<>();

    public List<RateCurve> getAll() {
        List<RateCurve> list = new ArrayList<>(data.values());
        return list;
    }

    public RateCurve getOne(Date date) {
        return data.get(date);
    }

    private HashMap<Date, RateCurve> deleteEmptyValuesInRateCurve(HashMap<Date, RateCurve> ratecurveMap){
        ratecurveMap.keySet().removeIf(key -> ratecurveMap.get(key).getData().isEmpty());
        return ratecurveMap;
    }

    public HashMap<Date, RateCurve> readCsv(String file) {
        BufferedReader br;
        FileReader fileReader;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                RateCurve rateCurve = new RateCurve();
                Date date = null;
                try {
                    date = formatter.parse(values[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                rateCurve.setDate(date);
                LinkedHashMap<Double, Double> map = new LinkedHashMap<>();
                for (int i = 1; i < values.length-1; i++) {
                    if (values[i].length() > 3) {
                        map.put(periods.get(i-1), Double.parseDouble(values[i]));
                    }
                }
                rateCurve.setData(map);
                data.put(date, rateCurve);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deleteEmptyValuesInRateCurve(data);
    }

    public RateCurveCsv() {}

}
