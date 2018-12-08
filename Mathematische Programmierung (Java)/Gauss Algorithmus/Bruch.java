/*�bung 7: Gauss Algorithmus
 * Version 1.2
 * Gruppenmitglieder: Adrian Schuh, Dominik Benning, Christopher H�bner
 *
 */
import java.math.BigInteger;

public class Bruch {
    private BigInteger dividend;
    private BigInteger divisor;
    private BigInteger ggt;

    public Bruch(double d) {
        this.dividend = BigInteger.valueOf((long)(d * 10000L));
        this.divisor = BigInteger.valueOf(10000L);
        this.ggt();
    }

    public Bruch() {
    }

    public Bruch add(Bruch b) {
        this.dividend = this.dividend.multiply(b.getDivisor());
        this.dividend = this.dividend.add(b.getDividend().multiply(this.divisor));
        this.divisor = this.divisor.multiply(b.getDivisor());
        ggt();
        return this;
    }

    public Bruch multiply(Bruch b) {
        this.dividend = this.dividend.multiply(b.getDividend());
        this.divisor = this.divisor.multiply(b.getDivisor());
        return this;
    }

    public Bruch multiply(long t) {
        this.dividend = this.dividend.multiply(BigInteger.valueOf(t));
        ggt();
        return this;
    }

    public Bruch subtract(Bruch b) {
        this.dividend = this.dividend.multiply(b.getDivisor());
        this.dividend = this.dividend.add(b.getDividend().multiply(this.divisor));
        ggt();
        return this;
    }

    public Bruch divide(Bruch b) {
        Bruch reziproke = new Bruch();
        reziproke.setDividend(b.getDivisor());
        reziproke.setDivisor(b.getDividend());
        this.multiply(reziproke);
        return this;
    }

    public double toDouble() {
        ggt();
        return this.dividend.doubleValue() != 0.0D && this.divisor.doubleValue() != 0.0D ? this.dividend.doubleValue() / this.divisor.doubleValue() : 0.0D;
    }

    public String toBruch() {
        if((dividend.doubleValue()<100&&divisor.doubleValue()<100)&&(dividend.doubleValue()>-100&&divisor.doubleValue()>-100)) {
            ggt();
        }
        if (this.dividend.doubleValue() != 0.0D && this.divisor.doubleValue() != 0.0D) {
            return this.divisor.doubleValue() < 0.0D ? this.dividend.doubleValue() * -1.0D + " / " + this.divisor.doubleValue() * -1.0D : this.dividend + " / " + this.divisor;
        } else {
            return "0 / 1";
        }
    }

    public BigInteger getDividend() {
        return this.dividend;
    }

    public void setDividend(BigInteger dividend) {
        this.dividend = dividend;
    }

    public BigInteger getDivisor() {
        return this.divisor;
    }

    public void setDivisor(BigInteger divisor) {
        this.divisor = divisor;
    }

    private void ggt() {
        // h�llt die zahlen so kompakt wie m�glich um rechenaufwand zu verringern und ansehnlichkeit zu verbessern
        if (this.dividend.doubleValue() < 0.0D && this.divisor.doubleValue() < 0.0D) {
            this.dividend = this.dividend.multiply(BigInteger.valueOf(-1L));
            this.divisor = this.divisor.multiply(BigInteger.valueOf(-1L));
        }

        BigInteger zahl1 = this.dividend;
        BigInteger zahl2 = this.divisor;
        if (zahl1.doubleValue() < 0.0D) {
            zahl1 = zahl1.multiply(BigInteger.valueOf(-1L));
        }

        if (zahl2.doubleValue() < 0.0D) {
            zahl2 = zahl2.multiply(BigInteger.valueOf(-1L));
        }

        if (zahl1.doubleValue() != 0.0D) {
            while(zahl2.doubleValue() != 0.0D) {
                if (zahl1.doubleValue() > zahl2.doubleValue()) {
                    zahl1 = zahl1.subtract(zahl2);
                } else {
                    zahl2 = zahl2.subtract(zahl1);
                }
            }
        }

        this.ggt = zahl1;
        if (this.ggt.doubleValue() != 0.0D) {
            this.dividend = this.dividend.divide(this.ggt);
            this.divisor = this.divisor.divide(this.ggt);
        }

    }
}
