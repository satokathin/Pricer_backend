package asenci.project.pricer.model;

import java.util.Date;

public class Obligation {
    private double nominal;
    private double periodicity;
    private double coupon;
    private double maturity;
    private Date issueDate;

    public double getNominal() { return nominal; }

    public double getPeriodicity() { return periodicity; }

    public double getCoupon() { return coupon; }

    public double getMaturity() { return maturity; }

    public Date getIssueDate() { return issueDate; }

    public Obligation(double nominal, double periodicity, double coupon, double maturity, Date issueDate) {
        this.nominal = nominal;
        this.periodicity = periodicity;
        this.coupon = coupon;
        this.maturity = maturity;
        this.issueDate = issueDate;
    }

    public Obligation(double nominal, double periodicity, double coupon, double maturity) {
        this.nominal = nominal;
        this.periodicity = periodicity;
        this.coupon = coupon;
        this.maturity = maturity;
    }

    @Override
    public String toString() {
        return "Obligation{" +
                "nominal=" + nominal +
                ", periodicity=" + periodicity +
                ", coupon=" + coupon +
                ", maturity=" + maturity +
                ", issueDate=" + issueDate +
                '}';
    }
}
