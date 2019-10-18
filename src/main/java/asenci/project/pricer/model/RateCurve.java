package asenci.project.pricer.model;

import java.util.Date;
import java.util.LinkedHashMap;

public class RateCurve {
    private Date date;
    private LinkedHashMap<Double,Double> data;

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public LinkedHashMap<Double, Double> getData() { return data; }

    public void setData(LinkedHashMap<Double, Double> data) { this.data = data; }

    public RateCurve(Date date, LinkedHashMap<Double, Double> data) {
        this.date = date;
        this.data = data;
    }

    public RateCurve() {
        this.date = null;
        this.data = null;
    }

    @Override
    public String toString() {
        return "RateCurve{" +
                "date=" + date +
                ", data=" + data +
                '}';
    }
}
