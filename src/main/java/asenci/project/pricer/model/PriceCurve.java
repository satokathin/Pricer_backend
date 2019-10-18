package asenci.project.pricer.model;

import java.util.Date;

public class PriceCurve {

    private Date date;
    private double price;

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public PriceCurve(Date date, double price) {
        this.date = date;
        this.price = price;
    }

    public PriceCurve() {
        this.date = null;
        this.price = 0.0;
    }

    @Override
    public String toString() {
        return "PriceCurve{" +
                "date=" + date +
                ", price=" + price +
                '}';
    }
}
